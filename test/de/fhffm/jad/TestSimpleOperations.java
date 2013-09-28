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

import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.data.EInputFields;
import de.fhffm.jad.data.IDataFieldEnum;
import de.fhffm.jad.mathematics.SimpleOperations;

/**
 * 
 * @author Prabhjot Singh 2013
 *
 */
public class TestSimpleOperations {

	private DataWrapper dw;
	private DataWrapper dwInputFields;
	
	@Before
	public void before(){
		dw = new DataWrapper("testdata");
		ArrayList<IDataFieldEnum> fields = new ArrayList<IDataFieldEnum>();
		fields.add(ETestdata.x);
		dw.createEmptyDataFrame(fields);
		dw.addObservation(new String[]{"1"});
		dw.addObservation(new String[]{"3"});
		dw.addObservation(new String[]{"5"});
		
		/*
		 * Test a more complex Data.Frame with several numerical and
		 * character fields
		 */
		dwInputFields = new DataWrapper("testdata2");
		ArrayList<IDataFieldEnum> inputfields = new ArrayList<IDataFieldEnum>();
		inputfields.add(EInputFields.ipsrc);
		inputfields.add(EInputFields.framelen);
		dwInputFields.createEmptyDataFrame(inputfields);
		dwInputFields.addObservation(new String[]{"192.168.0.1","100"});
		dwInputFields.addObservation(new String[]{"192.168.0.1","300"});
		dwInputFields.addObservation(new String[]{"192.168.0.1","500"});
	}
	
	@Test
	/**
	 * mean(1,3,5) should return (1+3+5)/3
	 */
	public void testMean() {
		SimpleOperations so = new SimpleOperations(dw);
		double mean = so.mean(ETestdata.x);
		//(expected, actual, allowed deviation)
		assertEquals((1+3+5)/3, mean, 0);
	}
	
	@Test
	/**
	 * mean(1,3,5) should return (1+3+5)/3
	 */
	public void testInputFieldsMean() {
		SimpleOperations so = new SimpleOperations(dwInputFields);
		double mean = so.mean(EInputFields.framelen);
		//(expected, actual, allowed deviation)
		assertEquals((100+300+500)/3, mean, 0);
	}
	
	@Test
	/**
	 * min(1,3,5) should return 1
	 */
	public void testMin() {
		SimpleOperations so = new SimpleOperations(dw);
		double min = so.min(ETestdata.x);
		//(expected, actual, allowed deviation)
		assertEquals(1, min, 0);
	}
	
	@Test
	/**
	 * max(1,3,5) should return 5
	 */
	public void testMax() {
		SimpleOperations so = new SimpleOperations(dw);
		double max = so.max(ETestdata.x);
		//(expected, actual, allowed deviation)
		assertEquals(5, max, 0);
	}
	
	
	@Test
	/**
	 * length(1,3,5) should return 3
	 */
	public void testLength() {
		SimpleOperations so = new SimpleOperations(dw);
		double length = so.length(ETestdata.x);
		//(expected, actual, allowed deviation)
		assertEquals(3, length, 0);
	}
	
	@Test
	/**
	 * sum(1,3,5) should return 9
	 */
	public void testSum() {
		SimpleOperations so = new SimpleOperations(dw);
		double sum = so.sum(ETestdata.x);
		//(expected, actual, allowed deviation)
		assertEquals(9, sum, 0);
	}
	
	@Test
	/**
	 * sd(1,3,5) should return 2
	 */
	public void testSd() {
		SimpleOperations so = new SimpleOperations(dw);
		double sd = so.sd(ETestdata.x);
		//(expected, actual, allowed deviation)
		assertEquals(2, sd, 0);
	}
}
