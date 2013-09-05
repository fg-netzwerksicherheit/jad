/**
 * Copyright (c) 2013 Jad
 * 
 * This file is part of Jad.
 * Jad is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jad is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jad. If not, see <http://www.gnu.org/licenses/>.
 */

package de.fhffm.jad.data;

import java.util.ArrayList;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import de.fhffm.jad.general.GeneralOperations;
import de.fhffm.jad.general.GlobalRConnection;

/**
 * This class is represents a data.frame in R
 * TODO: Johannes suggested to split this class in two classes.
 * DataFrame to hold the data and a second class for functions like loadfromCsv 
 */
public class DataWrapper {
	
	protected String name;
	protected RConnection c;
	private int size = 0;
	
	/**
	 * @param name is the name of the internal variable to hold this data in R. 
	 * Using names twice means overriding the old dataframe.
	 */
	public DataWrapper(String name){
		this.name = name;
		c = GlobalRConnection.getInstance();
	}
	
	/**
	 * @return name of this instance
	 */
	public String getName(){
		
		int spaceIndex = name.indexOf("_");
		if (spaceIndex != -1)
		{
			return name.substring(0, spaceIndex);
		}
		
		return name;
	}

	/**
	 * @return returns the name of the data.frame used in R.
	 * This name changes when we create some copies for datatransformations.
	 */
	public String getVariableName(){
		return name;
	}
	
	/**
	 * This function reads a csv file to R
	 * @param filename of the csv
	 */
	public void createDataFramefromCSV(String filename){
				
		try {
			//Check for Escape-Sequences
			filename = filename.replace("\\", "/");
			String command = name+ " <- read.table(file='"+filename+"',head=TRUE,sep=';')";
			c.voidEval(command);
			String command_size = "length("+name+")";
			this.size = c.eval(command_size).asInteger();

		} catch (RserveException e) {		
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	/**
	 * This function create an empty data.frame of type double
	 * @param fields are the names of the columns. You can set your own names creating an enum derived from IDataFieldEnum.
	 */
	public void createEmptyDataFrame(ArrayList<IDataFieldEnum> fields){
		
		//TODO: Check for duplicates
		//Check if ArrayList is empty or null
		if (fields == null)
			return;
		if (fields.size() == 0)
			return;
		this.size = fields.size();
		
		//Append all fieldnames together 
		StringBuffer sb = new StringBuffer();
		for (IDataFieldEnum field : fields){
			//this command specifies data.frame with col-names and typ
			sb.append(field.getName()+"="+field.getTyp()+"(0),");
		}
		//remove last ',' and build the complete command
		String command_ini = name+" <- data.frame("+sb.subSequence(0, sb.length()-1).toString()+")";
		//execute
		try {
			c.voidEval(command_ini);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @return number of rows in this dataframe
	 */
	public int getObservationCount(){
		String command = "nrow("+name+")";
		int result = 0;
		//execute
		try {
			result = c.eval(command).asInteger();
		} catch (RserveException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * This function will override or add the specified column with the given values
	 * @param column to set
	 * @param values to write
	 */
	public void setColumnData(IDataFieldEnum column, String[] values){
		
		//TODO: Check if Column is part of dataframe
		//	throw new IllegalArgumentException();
		
		StringBuffer sb = new StringBuffer();
		for (String s : values){
			sb.append(s+",");
		}
		
		String command = name+"$"+column.getName()+" <- c("+sb.subSequence(0, sb.length()-1).toString()+")";
		//execute
		try {
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function adds a new row
	 * @param values
	 */
	public void addObservation(String[] values){
		if(values.length != this.size)
			throw new IllegalArgumentException();
		
		StringBuffer sb = new StringBuffer();
		for (String s : values){
			//Add as.numeric(s) if double .. else as string
			if (GeneralOperations.isNumeric(s)){
				sb.append("as.numeric("+s+"),");
			}
			else if (s.equalsIgnoreCase("NA")){
				sb.append(""+s+",");
			}
			else {
				sb.append("as.character('"+s+"'),");
			}
		}
		String command = name+"[nrow("+name+")+1,] <- c("+sb.subSequence(0, sb.length()-1).toString()+")";
		//execute
		try {
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function returns the hole data.frame with access to rows, columns and names
	 * @return RList
	 */
	public RList getDataFrame(){
		try {
			return c.eval(name).asList();
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	/**
	 * Compares just the names.
	 */
	public boolean equals(Object o){
		DataWrapper other;
		if (DataWrapper.class.isAssignableFrom(o.getClass()))
			other = (DataWrapper)o;
		else
			return false;
			
		if (other.getName().equals(getName()))
			return true;
		else
			return false;
	}
	
	@Override
	/**
	 * containsKey is using this function before the equals function ..
	 */
    public int hashCode()
    {
        if (name == null) return 0;
        return getName().hashCode();
    }
	
	
	/**
	 * Prints the data.frame
	 */
	public void print(){
		
		//Check if data.frame is initialized
		RList l = getDataFrame();
		if(l==null)
			return;
		
		//Convert to String[][]
		try 
		{
			int cols = l.size();
			int rows = l.at(0).length();
			String[][] s = new String[cols][];
			String format = "";
			for (int i=0; i<cols; i++)
			{
					//Convert RList to Array
					s[i]=l.at(i).asStrings();
					
					//Set the column-size for println
					if (s[i][0].length() > 15){
						format += "%"+s[i][0].length()+"s ";
					}
					else
						format += "%15s ";
			}
			
			//Print names
			Object[] names = l.names.toArray();
			System.out.println(String.format(format,names));
			//Print each row.
			for (int i=0; i<rows; i++)
			{
				ArrayList<String> r = new ArrayList<String>();
				for (int j=0; j<cols; j++)
				{
					r.add(s[j][i]);
				}
				Object[] out = r.toArray();
				
				System.out.println(String.format(format,out));
			}
		
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
