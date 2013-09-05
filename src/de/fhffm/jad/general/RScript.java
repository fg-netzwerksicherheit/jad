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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RScript {
	
	private String script;
	private RConnection c;
	
	public RScript(String path){
		c = GlobalRConnection.getInstance();
		script = readTextFile(path);
	}
	
	/**
	 * Executes the R Script on a given path
	 * @param path
	 */
	public REXP executeScript(){
		
		try {			
			return c.eval(script);
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String readTextFile(String path){		
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(path));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append('\n');
	            line = br.readLine();
	        }
	        br.close();
	        return sb.toString();	        
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
		return null;
	}


}
