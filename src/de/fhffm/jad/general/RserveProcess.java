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

import org.rosuda.REngine.Rserve.RConnection;


/**
 * This Class can lookup the process RServe in the operating system
 * Have a look at:
 * https://r-forge.r-project.org/scm/viewvc.php/Seurat/RConnection/StartRserve.java?view=markup&root=seurat
 */
public class RserveProcess {

	/**
	 * @return true if Rserve is running
	 */
	public boolean isRunning(){
		try{
		RConnection c = new RConnection();
		c.close();
		return true;
			} catch (Exception e) {
				System.out.println("Could not connect to Rserve. "+ e.getMessage());
				}
	return false;
	}
	
	/** 
	 * This stuff is bull .. maybe we can use parts of it to automatically start
	 * Rserve .. 
	 
	
	
	 * @return true if Rserve process/processes are running in unix
	 * First checks if current OS is unix. Then checks if Rserve is running or not.
	 * If Rserve is running then it creates a csv file named (rserve-processes-list.csv)
	 * in user's directory with all Rserve processes that are currently running
	 
	private boolean isRunningUnix(){
		// Process p = Runtime.getRuntime().exec("ps ax|grep Rserve")
		//returns all processes containing Rserve ..
		String os = getOperatingSystem();
		if(os.contains("unix"))
		{
			BufferedReader input =null;
			BufferedWriter writer =null;
			try{
			String filesep=System.getProperty("file.separator");
			String csvpath=System.getProperty("user.home")+filesep+"rserve-processes-list.csv";
			Process p = Runtime.getRuntime().exec("ps ax|grep Rserve");
			input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			int line,check=0;
			writer = new BufferedWriter(new FileWriter(csvpath));
			while((line=input.read()) != -1){
				writer.write((char)line);
				check=1;
			}
			if(check==1){
				return true;
			}
			}catch(Exception e){
					System.out.println(e.getMessage());
				}finally
						{
						try
							{
							writer.close();
							input.close();
							}catch(Exception e){
								System.out.println(e.getMessage());
								}
						}
		}
		return false;
	}
	
	
	 * @return true if Rserve process/processes are running in windows OS
	 * First checks if current OS is windows. Then checks if Rserve is running or not.
	 * If Rserve is running then it creates a csv file named (rserve-processes-list.csv)
	 * in user's directory with all Rserve processes that are currently running
	 
	private boolean isRunningWindows(){
		String os = getOperatingSystem();
		if(os.contains("windows"))
		{
			BufferedReader input =null;
			BufferedWriter writer =null;
			try{
			String csvpath=System.getenv("USERPROFILE")+"\\rserve-processes-list.csv";
			Process p= Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\tasklist.exe /fo csv /fi "+"\"IMAGENAME eq Rserve.exe\"");
			input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			int line,check=0;
			writer = new BufferedWriter(new FileWriter(csvpath));
			while((line=input.read()) != -1){
				writer.write((char)line);
				check=1;
			}
			if(check==1){
				return true;
			}
			}catch(Exception e){
					System.out.println(e.getMessage());
					System.out.println("Other possible reasons could be User's rights  or tasklist.exe is missing in %windir%system32");
			}finally
					{
					try
					{
						writer.close();
						input.close();
					}catch(Exception e){
						System.out.println(e.getMessage());
						}
					}
		}
		return false;
	}
	
	
	 * @return String that contains current OS's name in lower case.
	 * 
	 
	private String getOperatingSystem(){
		
		try{
			String osname=System.getenv("OS").toLowerCase();
			return osname;
			}catch(Exception e){
				System.out.println(e.getMessage());
				}
		return null;
		
		//Possible Answers:
		
		 * 	AIX
			Digital UNIX
			FreeBSD
			HP UX
			Irix
			Linux
			Mac OS
			Mac OS X
			MPE/iX
			Netware 4.11
			OS/2
			Solaris
			Windows 95
			Windows 98
			Windows NT
			Windows Me
			Windows 2000
			Windows XP
			Windows 2003
			Windows CE
			Windows Vista
			Windows 7
		 *
		 
		
}
	*/

	

}
