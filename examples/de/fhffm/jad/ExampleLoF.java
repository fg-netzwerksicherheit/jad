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

import java.util.ArrayList;

import de.fhffm.jad.data.DataTransform;
import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.EInputFields;
import de.fhffm.jad.data.EOutputPerIpFields;
import de.fhffm.jad.data.IDataFieldEnum;
import de.fhffm.jad.mathematics.OutlierDetection;
import de.fhffm.jad.mathematics.SimpleOperations;

public class ExampleLoF {
	
	private static double sd;
	private static double mean;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//File contains 5 minutes of normal network traffic
		normalize(System.getProperty("user.dir") + "/data/5min_no_attacks.csv");
		//File contains 5 minutes Ping of Death traffic
		ArrayList<Double> result = outlierAnalysis(System.getProperty("user.dir") + "/data/5min_include_pod.csv");
				
		//Print
		System.out.println("Local Outlier Factors:");
		if (result != null) {
			System.out.println(result.toString());
		}
	}
	
	/**
	 * Calculate standard deviation and mean of the normal traffic
	 * @param filename
	 */
	private static void normalize(String filename){
		DataWrapper dataframe = new DataWrapper("input");
		//Read csv-file
		dataframe.createDataFramefromCSV(filename);
		DataTransform t = new DataTransform(dataframe);
		t.replaceNaN("0");
		
		SimpleOperations so = new SimpleOperations(t);
		
		sd = so.sd(EInputFields.framelen);
		mean = so.mean(EInputFields.framelen);
	}

	/**
	 * Calculate LoF for each Observation
	 * @param filename
	 */
	private static ArrayList<Double> outlierAnalysis(String filename) {
		DataWrapper dataframe = new DataWrapper("input");
		//Read csv-file
		dataframe.createDataFramefromCSV(filename);
		DataTransform t = new DataTransform(dataframe);
		t.replaceNaN("0");
		
		//normalize with mean-value of the normal traffic
		t.standardise(EInputFields.framelen, sd, mean);
				
		DataWrapper out = new DataWrapper("output");
		
		ArrayList<IDataFieldEnum> fields = new ArrayList<IDataFieldEnum>();
		fields.add(EOutputPerIpFields.IcmpCount);
		fields.add(EOutputPerIpFields.MeanIcmpSize);
		out.createEmptyDataFrame(fields);
		
		SimpleOperations so1 = new SimpleOperations(t);
		ArrayList<String> uniqueIps = so1.unique(EInputFields.ipsrc);
		
		for (String elem : uniqueIps) {
			DataTransform ipScr = new DataTransform(t);
			ipScr.filterEquals(EInputFields.ipsrc, elem);
						
			SimpleOperations so = new SimpleOperations(ipScr);
			
			String mean = String.valueOf(so.mean(EInputFields.framelen));
			String count = String.valueOf(ipScr.getObservationCount());
			
			String[] output = {count, mean};
			
			out.addObservation(output);
		}
		
		OutlierDetection outlier = new OutlierDetection(out);
		return outlier.lof();
				
	}
}
