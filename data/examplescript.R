#-----
#Number of Times a Field was found
#Parameter: x = dataframe, field = column
NumberOfField <- function(x, field){
   values <- c()
   for ( i in 1:length(unique(x[,field])) ){
      values <- append(values,nrow(x[x[,field] == unique(x[,field])[i],]))
   }
   return(values)
}

#-----
#Number of unique values for a field
#Parameter: x = dataframe, field = column
UniqueFieldValues <- function(x){
   result <- c()
   for ( field in 1:length(x) ){
      result <- append(result, length(unique(x[,field])))
   }
   return(result)
}

#-----
#Last occurance of this field in seconds
#Parameter: x = dataframe, field = column
getTimeDiffToLastOccurance <- function(x, field, timefield, index){
   indexlist <- which(x[,field] == x[index,field])
   if (min(indexlist) >= index) { 
      return(0) 
   }
   else {
      pos <- which(indexlist == index)
      indexprev <- indexlist[pos-1]
      result <- difftime(x[index,timefield], x[indexprev,timefield], units='secs')
      return(result)
   }
}

prepare <- function(neu) {
neu <- neu[,-1]
neu <- neu[,-1]
neu <- neu[,-7]
neu <- neu[,-7]
neu <- neu[,-8]

neu$frame.time <-as.POSIXct(paste("2013-07-21", substr(neu[,4],start=14, stop=30)))
return(neu)
}

calc <- function(neu, data) {
#Calculate the Training Data:
#Number of unique values for each field
r <- UniqueFieldValues(data)

#Calculate score for each field
# index "4" == frame.time
# field == Field_index
# j == Packet_index

for(field in 1:length(data)) {
   #Number of ocurrences for each value
   z <- NumberOfField(data,field)
   vec_fieldscores <- c()
   for(j in 1:nrow(neu)) {
      #Seach Number of Occurances in Training Data:
      index <- which(unique(data[,field]) == neu[j,field])
      #If we can't find this value, we have 0 Occurences ..
      n <- 0
      #n <- 1
      #Little Hack: R's function is.null returns true if the value is empty ...
      if (length(index) > 0) { n <- z[index] }
      #Calculate Score for this Field:
      field_score <- ((getTimeDiffToLastOccurance(neu, field, 4, j))*n)/r[field]
      #field_score <- n/r[field]
      vec_fieldscores <- append(vec_fieldscores, field_score)
      #print(paste("Iteration:",j, " length:",length(vec_fieldscores)))
   }
   neu[[paste(field)]] <- vec_fieldscores
}

score = neu[["1"]] + neu[["2"]] + neu[["3"]] #+ neu[["4"]] + neu[["5"]]
score <- -(-0.35 + 0.1 * log10(score))
score[is.infinite(score)] <- 1
score[score > 1] <- 1
score[score < 0] <- 0
return(score)
}

compare1 <- prepare(http)

calc(http, http)