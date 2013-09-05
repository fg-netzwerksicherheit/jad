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

package de.fhffm.jad.mathematics;

import java.util.ArrayList;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import de.fhffm.jad.data.DataWrapper;
import de.fhffm.jad.general.GlobalRConnection;

/**
 * This class implements the Local Outlier Factor
 * @author Denis Hock, Manuel Grob
 *
 */
public class OutlierDetection {
	
	private DataWrapper dw;
	private RConnection c;
	
	private static final String lof = "DMwR";

	public OutlierDetection(DataWrapper dw) {
		this.dw = dw;
		this.c = GlobalRConnection.getInstance();
		
		initialiseLof();
	}

	/**
	 * Load R-library
	 */
	private void initialiseLof(){
		try {
			String command="library(" + lof + ")";
			c.voidEval(command);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return Local Outlier Factors for each observation
	 */
	public ArrayList<Double> lof() {
		try {
			String command="lofactor("+ dw.getVariableName() + ", 5)";
			double[] res = c.eval(command).asDoubles();
			ArrayList<Double> result = new ArrayList<Double>();
			for (double item : res) {
				result.add(item);
			}
			return result;
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
