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

import de.fhffm.jad.data.DataTransform;
import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.EInputFields;
import de.fhffm.jad.general.Constants;
import de.fhffm.jad.mathematics.SimpleOperations;

public class ExampleStandadise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		DataWrapper dataframe = new DataWrapper("input");
		// Read csv-file
		dataframe.createDataFramefromCSV(Constants.EXAMPLEFILEPATH);
		DataTransform t = new DataTransform(dataframe);
		t.replaceNaN("0");
		
		SimpleOperations so = new SimpleOperations(t);
		
		t.standardise(EInputFields.framelen, so.sd(EInputFields.framelen), so.mean(EInputFields.framelen));
		// print the complete data.frame
		t.print();
	}
}
