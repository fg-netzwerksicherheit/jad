
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

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;

import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.general.RScript;

/**
 * This example reads and execute an R Script
 * @author Denis Hock
 */
public class ExampleScript {

	public static void main(String[] args) {
		
		//The name http was used in the R script to further process this data.frame ...
		DataWrapper original = new DataWrapper("http");
		original.createDataFramefromCSV(System.getProperty("user.dir") + "\\data\\http.csv");
		
		//Path to the R Script (JAD-Project-Directory) + Script
		//This R-Script implements PHAD (Packet Header Anomaly Detection)
		String path = System.getProperty("user.dir") + "\\data\\examplescript.R";		
		//This Class implements the function to execute R-Scripts
		RScript script = new RScript(path);
		//REXP is a generic type to store the results provided by RServe
		REXP result = script.executeScript();
		double[] x = null;
		try {
			//Convert to Array of Doubles
			x = result.asDoubles();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Print the results
		System.out.println("PHAD Anomaly Score:");
		for (double i : x){
			System.out.println(i);
		}
		
	}

}
