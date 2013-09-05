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

/**
 * This class tests the filters of DataTransform class.
 */

public class TestFilter{
	
	private DataWrapper dw;

	@Before
	public void before(){
		
		dw = new DataWrapper("Testdata");
		ArrayList<IDataFieldEnum> fields = new ArrayList<IDataFieldEnum>();
		fields.add(ETestdata.x);
		fields.add(ETestdata.y);
		fields.add(ETestdata.z);
		dw.createEmptyDataFrame(fields);
		dw.addObservation(new String[]{"192.168.0.1","3.0","3.0"});
		dw.addObservation(new String[]{"192.168.0.1","2.0","5.0"});
		dw.addObservation(new String[]{"192.168.0.200","2.0","2.0"});
		dw.addObservation(new String[]{"192.168.0.1","2.0","2.0"});
		dw.addObservation(new String[]{"192.168.0.100","2.0","5.0"});
	}
	
	@Test
	/**
	 * We have only 2 rows in column "Test1" that have value = 3. 
	 */
	public void testFilterEqualsDouble() {
		DataTransform t = new DataTransform(dw);
		t.filterEquals(ETestdata.y,2);
		int nobs = t.getObservationCount();
		assertEquals(4, nobs, 0);
	}

	@Test
	/**
	 * We have only 5 rows in column "Test2" that have value != 4. 
	 */
	public void testFilterNotEqualsDouble() {
		DataTransform t = new DataTransform(dw);
		t.filterNotEquals(ETestdata.y,2);
		int nobs = t.getObservationCount();
		assertEquals(1, nobs, 0);
	}

	@Test
	/**
	 * We have only 2 rows in column "Test1" that have value = 3. 
	 */
	public void testFilterEqualsString() {
		DataTransform t = new DataTransform(dw);
		t.filterEquals(ETestdata.x,"192.168.0.1");
		int nobs = t.getObservationCount();
		assertEquals(3, nobs, 0);
	}

	@Test
	/**
	 * We have only 5 rows in column "Test2" that have value != 4. 
	 */
	public void testFilterNotEqualsString() {
		DataTransform t = new DataTransform(dw);
		t.filterNotEquals(ETestdata.x, "192.168.0.100");
		int nobs = t.getObservationCount();
		assertEquals(4, nobs, 0);
	}

	
	@Test
	/**
	 * We have 1 row in our column "Test2" that have value > 2. 
	 */
	public void testFilterGreaterThan() {
		DataTransform t = new DataTransform(dw);
		t.filterGreaterThan(ETestdata.y, 2);
		int nobs = t.getObservationCount();
		assertEquals(1, nobs, 0);
	}

	@Test
	/**
	 * We have 3 rows in column Test1 that has value < 3. 
	 */
	public void testFilterLessThan() {
		DataTransform t = new DataTransform(dw);
		t.filterLessThan(ETestdata.y, 3);
		int nobs = t.getObservationCount();
		assertEquals(4, nobs, 0);
	}
	
	
}
