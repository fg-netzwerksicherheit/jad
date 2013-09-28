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

import java.util.HashMap;
import java.util.Map;
import org.rosuda.REngine.Rserve.RserveException;

/**
 * This class is used for additional operations and preparations on the data 
 * i.e. centering, transforming with log, add missing values...
 * It automatically creates a new copy of the data in R. This will make it easy
 * to extend this with a command-pattern and undo and redo support if anyone needs.
 *
 */
public class DataTransform extends DataWrapper{
	
	private static Map<DataWrapper, Integer> globalTransforms = new HashMap<DataWrapper,Integer>();
	
	/**
	 * @param dw Data to apply transformations
	 */
	public DataTransform(DataWrapper dw){
		super(dw.getName()+"_"+getDataCount(dw));
		
		//Create a copy of this data
		try {
			c.voidEval(name+" <- "+dw.getVariableName());
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This function is used for the automatic naming of the variable-copy in R
	 * @param dw
	 * @return number of copies with this name
	 */
	private static int getDataCount(DataWrapper dw){
		int count = -1;
		
		if (!globalTransforms.containsKey(dw)){
			globalTransforms.put(dw, 1);
			count = 1;
		}
		else
		{
			count = globalTransforms.get(dw)+1;
			globalTransforms.put(dw, count);
		}
		
		return count;
	}
	
	/**
	 * This function will replace missing values in the hole data.frame
	 * @param value = new value for all missing cells
	 */
	public void replaceNaN(String value){
		try {
			String command=name+"[is.na("+name+")] <- "+value;
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function will replace missing values in a single column of the
	 * data.frame
	 * @param value = new value for missing cells
	 * @param field = column to process
	 */
	public void replaceNaN(String value, IDataFieldEnum field){
		try {
			String command = name+"$"+field.getName()+"[is.na("+name+"$"+field.getName()+")] <- "+value;
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	/**
	 * This function filters where field==value
	 * @param field
	 * @param value
	 */
	public void filterEquals(IDataFieldEnum field, String value){
		try {
			String command = name+" <- "+name+"["+name+"$"+field.getName()+"=='"+value+"',]";
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function filters where field!=value
	 * @param field
	 * @param value
	 */
	public void filterNotEquals(IDataFieldEnum field, String value){
		try {
			String command = name+" <- "+name+"["+name+"$"+field.getName()+"!='"+value+"',]";
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function filters where field==value
	 * @param field
	 * @param value
	 */
	public void filterEquals(IDataFieldEnum field, double value){
		try {
			String command = name+" <- "+name+"["+name+"$"+field.getName()+"=="+value+",]";
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function filters where field!=value
	 * @param field
	 * @param value
	 */
	public void filterNotEquals(IDataFieldEnum field, double value){
		try {
			String command = name+" <- "+name+"["+name+"$"+field.getName()+"!="+value+",]";
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function filters where field>value
	 * @param field
	 * @param value
	 */
	public void filterGreaterThan(IDataFieldEnum field, double value){
		try {
			String command = name+" <- "+name+"["+name+"$"+field.getName()+">"+value+",]";
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * This function filters where field<value
	 * @param field
	 * @param value
	 */
	public void filterLessThan(IDataFieldEnum field, double value){
		try {
			String command = name+" <- "+name+"["+name+"$"+field.getName()+"<"+value+",]";
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * This function scales and centers the hole dataframe with the mean and sd values
	 * of the dataframe dw
	 * @param dw dataframe
	 */
	public void standardise(DataWrapper dw) {

	}
	
	/**
	 * scales and centers this dataframe
	 */
	public void standardise(){
		
	}
	
	/**
	 * scales and centers this column of the dataframe
	 * @param field to center and scale
	 */
	public void standardise(IDataFieldEnum field){
		
	}
	
	/**
	 * This function scales and centers a column with the given mean and sd
	 * @param field to center
	 * @param sd 
	 * @param mean
	 */
	public void standardise(IDataFieldEnum field, double sd, double mean) {
		String column = name + "$" + field.getName();
		String command = column + " <- (" + column + "-" + mean + ")/" + sd;
		
		try {
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
