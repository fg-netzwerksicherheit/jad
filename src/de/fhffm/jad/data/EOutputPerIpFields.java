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
 * These Fields are intended to use with a record
 * a record filtered to contain a specified IP in ip.src or ip.dst 
 */
public enum EOutputPerIpFields implements IDataFieldEnum{

	//TODO: Add the String-names 
	//TODO: Maybe we sort these variables in a better way and derive them from other Interfaces
	//      to have a better hierarchy  
	
	PacketCount("PacketCount","numeric"),  			//Number of Packets
	
	IpSrcCount("IpSrcCount","numeric"),				//Number of different IP addresses in ip.src
	IpDstCount("IpDstCount","numeric"),				//Number of different IP addresses in ip.dst
	
	TcpCount("TcpCount","numeric"),					//TCP packets
    TcpOutgoingCount("TcpOutgoingCount","numeric"),	//outgoing TCP packets
    TcpIncomingCount("TcpIncomingCount","numeric"),	//incoming TCP packets
    TcpLanCount("TcpLanCount","numeric"),				//TCP packets send or received in LAN
    TcpInternetCount("TcpInternetCount","numeric"),	//TCP packets send or received in Internet
    
    UdpCount("UdpCount","numeric"),
    UdpOutgoingCount("UdpOutgoingCount","numeric"),
    UdpIncomingCount("UdpIncomingCount","numeric"),
    UdpLanCount("UdpLanCount","numeric"),
    UdpInternetCount("UdpInternetCount","numeric"),
    
    IcmpCount("IcmpCount","numeric"),
    IcmpOutgoingCount("IcmpOutgoingCount","numeric"),
    IcmpIncomingCount("IcmpIncomingCount","numeric"),
    IcmpLanCount("IcmpLanCount","numeric"),
    IcmpInternetCount("IcmpInternetCount","numeric"),
    
    SYNCount("SYNCount","numeric"),					//TCP packets with SYN-Flag set
    ACKCount("ACKCount","numeric"),					//TCP packets with ACK-Flag set
    
    WwwOutgoingCount("WwwOutgoingCount","numeric"),	//outgoing TCP packets with port 80
    WwwIncomingCount("WwwIncomingCount","numeric"),	//incoming TCP packets with port 80
    
    DnsOutgoingCount("DnsOutgoingCount","numeric"),	//outgoing UDP packets with port 53
    DnsIncomingCount("DnsIncomingCount","numeric"),	//incoming UDP packets with port 53
    
    ArpRequestCount("ArpRequestCount","numeric"),
    ArpReplyCount("ArpReplyCount","numeric"),
    
    OtherProtocolCount("OtherProtocolCount","numeric"),	//All packets with unclassified port-number
    
    MeanPacketSize("MeanPacketSize","numeric"),			//Average size of packet content
    MeanTcpOutgoingSize("MeanTcpOutgoingSize","numeric"),
    MeanTcpIncomingSize("MeanTcpIncomingSize","numeric"),
    MeanWwwOutgoingSize("MeanWwwOutgoingSize","numeric"),
    MeanWwwIncomingSize("MeanWwwIncomingSize","numeric"),
    MeanUdpOutgoingSize("MeanUdpOutgoingSize","numeric"),
    MeanUdpIncomingSize("MeanUdpIncomingSize","numeric"),
    MeanDnsOutgoingSize("MeanDnsOutgoingSize","numeric"),
    MeanDnsIncomingSize("MeanDnsIncomingSize","numeric"),
    MeanIcmpSize("MeanIcmpSize","numeric"),
    
    MinPacketSize("MinPacketSize","numeric"),
    MinTcpOutgoingSize("MinTcpOutgoingSize","numeric"),
    MinTcpIncomingSize("MinTcpIncomingSize","numeric"),
    MinWwwOutgoingSize("MinWwwOutgoingSize","numeric"),
    MinWwwIncomingSize("MinWwwIncomingSize","numeric"),
    MinUdpOutgoingSize("MinUdpOutgoingSize","numeric"),
    MinUdpIncomingSize("MinUdpIncomingSize","numeric"),
    MinDnsOutgoingSize("MinDnsOutgoingSize","numeric"),
    MinDnsIncomingSize("MinDnsIncomingSize","numeric"),
    
    MaxPacketSize("MaxPacketSize","numeric"),
    MaxTcpOutgoingSize("MaxTcpOutgoingSize","numeric"),
    MaxTcpIncomingSize("MaxTcpIncomingSize","numeric"),
    MaxWwwOutgoingSize("MaxWwwOutgoingSize","numeric"),
    MaxWwwIncomingSize("MaxWwwIncomingSize","numeric"),
    MaxUdpOutgoingSize("MaxUdpOutgoingSize","numeric"),
    MaxUdpIncomingSize("MaxUdpIncomingSize","numeric"),
    MaxDnsOutgoingSize("MaxDnsOutgoingSize","numeric"),
    MaxDnsIncomingSize("MaxDnsIncomingSize","numeric"),
    
    TotalSize("TotalSize","numeric"),						//Sum of all packet sizes
    
    Delay("Delay","numeric"),								//Mean of all minimum times between a Syn to a ip in ip.dst and Ack from the same ip in ip.src
    Symmetry("Symmetry","numeric"),						//ratio between outgoing and incoming packets
    LostSegments("LostSegments","numeric"),				//Number of lost segments
    Throughput("Throughput","numeric");					//packetcount over time		
	
	private String name;
	private String typ;
	EOutputPerIpFields(String name, String typ){
		this.name = name;
		this.typ = typ;
	}
	
	public String getName(){
		return name;
	}
	
	public String getTyp(){
		return typ;
	}
	
}
