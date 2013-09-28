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
import de.fhffm.jad.general.Constants;
import de.fhffm.jad.network.EipProtocol;

/**
 * This example demonstrates the use of filters
 *
 */
public class ExampleFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String ip = "135.8.60.182";
		
		//Read test2.csv
		DataWrapper original = new DataWrapper("input");
		original.createDataFramefromCSV(Constants.EXAMPLEFILEPATH);
		
		//Calculate some value's with help of filter
		
		//Packets where ip.proto==tcp
		DataTransform tcp = new DataTransform(original);
		tcp.filterEquals(EInputFields.ipproto, String.valueOf(EipProtocol.tcp.getNumber()));
		int tcpCount = tcp.getObservationCount();
		
		//Packets where ip.proto==tcp and ip==ip.src
		DataTransform tcp_out = new DataTransform(tcp);
		tcp_out.filterEquals(EInputFields.ipsrc, ip);
		int tcpOutgoingCount = tcp_out.getObservationCount();
		
		//Packets ip.proto==tcp and ip!=ip.src
		DataTransform tcp_in = new DataTransform(tcp);
		tcp_in.filterNotEquals(EInputFields.ipsrc, ip);
		int tcpIncomingCount = tcp_in.getObservationCount();
		
		//Create a new data.frame for the output
		DataWrapper out = new DataWrapper("output");

		ArrayList<IDataFieldEnum> fields = new ArrayList<IDataFieldEnum>();
		fields.add(EOutputPerIpFields.TcpCount);
		fields.add(EOutputPerIpFields.TcpIncomingCount);
		fields.add(EOutputPerIpFields.TcpOutgoingCount);
		out.createEmptyDataFrame(fields);
		
		//Add the calculated values to the new data.frame
	    ArrayList<String> values = new ArrayList<String>();
	    values.add(String.valueOf(tcpCount));
	    values.add(String.valueOf(tcpIncomingCount));
	    values.add(String.valueOf(tcpOutgoingCount));
	    out.addObservation(values.toArray(new String[values.size()]));
	    
	    //print the data.frame
	    out.print();

	}

}
