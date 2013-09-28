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

package de.fhffm.jad.mathematics;

import java.util.ArrayList;
import java.util.Arrays;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.IDataFieldEnum;
import de.fhffm.jad.general.GlobalRConnection;

/**
 * This class implement the most basic R-Operations.
 */
public class SimpleOperations {

	private RConnection c;
	private DataWrapper dw;
	
	/**
	 * @param dw dataset
	 */
	public SimpleOperations(DataWrapper dw){
		this.dw = dw;
		this.c = GlobalRConnection.getInstance();
	}
	
	/**
	 * @param field vector
	 * @return mean value of vector
	 */
	public double mean(IDataFieldEnum field){
		try {
			String command = "mean("+dw.getVariableName()+"$"+field.getName()+")";			
			double d= c.eval(command).asDouble();
			return d;
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @param field vector
	 * @return min value of vector
	 */
	public double min(IDataFieldEnum field){
		try {
			String command = "min("+dw.getVariableName()+"$"+field.getName()+")";
			double d= c.eval(command).asDouble();
			return d;
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @param field vector
	 * @return max value of vector
	 */
	public double max(IDataFieldEnum field){
		
		try {
			String command = "max("+dw.getVariableName()+"$"+field.getName()+")";
			double d= c.eval(command).asDouble();
			return d;
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * @param field vector
	 * @return length of vector
	 */
	public int length(IDataFieldEnum field){
		
		try {
			String command = "length("+dw.getVariableName()+"$"+field.getName()+")";
			double d= c.eval(command).asDouble();
			return (int)d;
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/**
	 * @param field vector
	 * @return sum of vector
	 */
	public double sum(IDataFieldEnum field){
		
		try {
			String command = "sum("+dw.getVariableName()+"$"+field.getName()+")";
			double d= c.eval(command).asDouble();
			return d;
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	
	}

	/**
	 * @param field vector
	 * @return standard deviation of vector
	 */
	public double sd(IDataFieldEnum field){
		
		try {
			String command = "sd("+dw.getVariableName()+"$"+field.getName()+")";
			double d= c.eval(command).asDouble();
			return d;
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	
	}
	
	
	public ArrayList<String> unique(IDataFieldEnum field) {
		try {
			String command = "unique(" + dw.getVariableName() + "$" + field.getName() + ")";
			String[] s = c.eval(command).asStrings();
			ArrayList<String> myStrings = new ArrayList<String>(Arrays.asList(s));
			return myStrings;
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * This function will count the missing values(NAN) in the whole data.frame
	 * @return Number of missing values(NAN)
	 */
	public int countMissingValues(){
		int sum = -1; //Number of missing values will be only positive or 0.
		try {
			String command="sum(is.na("+dw.getVariableName()+"))";
			sum =c.eval(command).asInteger();
			} catch (RserveException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			}
		return sum;
	}
	
	/**
	 * This function will count missing values in a single column of the
	 * data.frame
	 * @param field = column to process
	 * @return Number of missing values(NAN)
	 */
	public int countMissingValues(IDataFieldEnum field){
		int sum = -1;
		try {
			String command = "sum(is.na("+dw.getVariableName()+"$"+field.getName()+"))";
			 sum = c.eval(command).asInteger();
			} catch (RserveException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	
	/**
	 * @param field
	 * @return the Probability for the distribution of this field
	 */
	public double entrophy(IDataFieldEnum field){
		double result = 0;
		
		//This R-Command may look a bit crazy - The formular is
		//
		// P_total = Sum 0-n ( (packet_with_valueX_count / packet_count) * log(unique_value_count)
		//
		//Here a detailed describtion of the command:
		//table(x$ip.src)[] returns the observationcount for each unique value
		//nrow returns the number of observations
		//length unique returns the number of distinct values
		//table / nrow = probability for this value
		//the probability is normalized with the log-value (Result between 0 and 1)
		//we can finally return the sum of each probability
		//sum((table(x$ip.src)[] / nrow(x)) * log(length (unique(x$ip.src) ) ) * (table(x$ip.src)[] / nrow(x)) )

		try {
			String command = "sum((table("+dw.getVariableName()+"$"+field.getName()+")[] / nrow("+dw.getVariableName()+")) * log(length(unique("+dw.getVariableName()+"$"+field.getName()+")))* (table("+dw.getVariableName()+"$"+field.getName()+")[] / nrow("+dw.getVariableName()+")) )";
			result = c.eval(command).asDouble();
			
			if( result < 0 ) {
				result = 0;
			}
			if( result > 1 ) {
				result = 1;
			}
			
			} catch (RserveException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
