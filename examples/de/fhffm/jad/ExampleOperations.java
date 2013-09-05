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
package de.fhffm.jad;

import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.EInputFields;
import de.fhffm.jad.general.Constants;
import de.fhffm.jad.mathematics.SimpleOperations;

/**
 * This class demonstrates the use of R-Operations
 */
public class ExampleOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Read test2.csv
		DataWrapper original = new DataWrapper("input");
		original.createDataFramefromCSV(Constants.EXAMPLEFILEPATH);
				
	    //calculate the mean frame.len	
		SimpleOperations so = new SimpleOperations(original);
	    double meanPacketSize = so.mean(EInputFields.framelen);
	    
	    System.out.println("Mean Packet Size: " + meanPacketSize);

	}

}
