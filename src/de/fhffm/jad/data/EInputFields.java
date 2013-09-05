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

package de.fhffm.jad.data;

/**
 * We want to avoid typos and increase the maintainability
 * in case of a change of naming conventions.
 * This enum defines all fieldnames for supported input.
 * You should use these constants instead of writing string-fieldnames 
 * somewhere.
 */
public enum EInputFields implements IDataFieldEnum{
	
	ipsrc("ip.src","character"),	
	ipdst("ip.dst","character"),	
	ipproto("ip.proto","numeric"),
	ethtyp("eth.type","numeric"),
	framelen("frame.len","numeric"),	
	frametime("frame.time","numeric"),	
	tcpflags("tcp.flags","numeric"),	
	tcpdstport("tcp.dstport","numeric"),
	tcpsrcport("tcp.srcport","numeric"),	
	udpdstport("udp.dstport","numeric"),	
	udpsrcport("udp.srcport","numeric"),
	tcplostsegment("tcp.analysis.lost_segment","numeric");
	
	EInputFields(String fieldname, String typ){
		this.fieldname = fieldname;
		this.typ = typ;
	}
	
	private String fieldname;
	private String typ;
	
	public String getName(){
		return fieldname;
	}
	
	public String getTyp(){
		return typ;
	}
}
