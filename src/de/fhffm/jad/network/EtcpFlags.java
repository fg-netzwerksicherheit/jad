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
package de.fhffm.jad.network;

/**
 * This enum contains all tcp-flags with Hex-Values
 */
public enum EtcpFlags {
	FIN(0x1),
	SYN(0x2),
	RST(0x4),
	PSH(0x8),
	ACK(0x10),
	URG(0x20);
	
	int code;
	EtcpFlags(int code){
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}

}
