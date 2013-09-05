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

import de.fhffm.jad.data.IDataFieldEnum;

/**
 * This enum defines the columns of the data.frame for the testcases
 * @author Denis
 *
 */
public enum ETestdata implements IDataFieldEnum{
	chars("chars", "character"),
	x("Test1","numeric"),
	y("Test2","numeric"),
	z("Test3","numeric");
	
	private String name;
	private String typ;
	
	ETestdata(String name, String typ){
		this.name = name;
		this.typ = typ;
	}
	
	@Override
	public String getName(){
		return name;
	}
	@Override
	public String getTyp() {
		return typ;
	}
}
