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
package de.fhffm.jad.general;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;


/**
 * This class implements general R-Operations
 *
 */
public class GeneralOperations {

	private RConnection c;
	
	public GeneralOperations(RConnection c){
		this.c = c;
	}
	
	/**
	 * @return R Version
	 */
	public String getVersion(){
		try {
			return c.eval("R.version.string").asString();
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isNumeric(String value){
		try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
	}
	
	/**
	 * loads a library into R (required for ie for LoF Outlier Detection)
	 * @param lib to load
	 */
	public void loadRLibrary(String lib){
		//TODO: IMPLEMENT THIS FUNKTION
	}
	
}
