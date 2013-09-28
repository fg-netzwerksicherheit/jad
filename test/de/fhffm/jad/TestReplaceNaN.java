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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.fhffm.jad.data.DataTransform;
import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.IDataFieldEnum;
import de.fhffm.jad.mathematics.SimpleOperations;

/**
 * This class tests the filters of DataTransform class.
 */

public class TestReplaceNaN {
	
	private DataWrapper dw;
	
	@Before
	public void before(){
		
		dw = new DataWrapper("Testdata");
		ArrayList<IDataFieldEnum> fields = new ArrayList<IDataFieldEnum>();
		fields.add(ETestdata.x);
		fields.add(ETestdata.y);
		fields.add(ETestdata.z);
		dw.createEmptyDataFrame(fields);
		dw.addObservation(new String[]{"3.0","3.0","3.0"});
		dw.addObservation(new String[]{"3.0","2.0","5.0"});
		dw.addObservation(new String[]{"2.0","2.0","NA"});
		dw.addObservation(new String[]{"NA","2.0","NA"});
		dw.addObservation(new String[]{"NA","2.0","5.0"});
	}

	@Test
	/**
	 * We have total 5 NA(missing values) in our data frame.
	 * All NA(missing values) will be replaced with "10" and finally
	 * there will be no NA(missing) values left.
	 */
		
	public void testReplaceNaNDataFrame() {
		DataTransform t = new DataTransform(dw);
		SimpleOperations s = new SimpleOperations(t);
		int missing_before = s.countMissingValues();
		assertEquals(4,missing_before,0);
		t.replaceNaN("10");
		int missing_after = s.countMissingValues();
		assertEquals(0, missing_after, 0);
		double sum = s.sum(ETestdata.x);
		assertEquals(28, sum, 0);
	}

	@Test
	/**
	 * We have total 2 NA(missing values) in our column "Test1".
	 * All NA(missing values) will be replaced with "10" and finally
	 * there will be no NA(missing) values left in column "Test1".
	 */
	public void testReplaceNaNColumn() {
		DataTransform t = new DataTransform(dw);
		SimpleOperations s = new SimpleOperations(t);
		int missing_before = s.countMissingValues(ETestdata.x);
		assertEquals(2,missing_before,0);
		t.replaceNaN("10",ETestdata.x);
		int missing_after = s.countMissingValues(ETestdata.x);
		assertEquals(0, missing_after, 0);
		int missing_total = s.countMissingValues();
		assertEquals(2, missing_total, 0);
	}

}
