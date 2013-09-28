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
 * This enum is generated from the following xml-file:
 * http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xml
 */
public enum EipProtocol {
	hopopt(0),
	icmp(1),
	igmp(2),
	ggp(3),
	ipv4(4),
	st(5),
	tcp(6),
	cbt(7),
	egp(8),
	igp(9),
	bbnrccmon(10),
	nvpii(11),
	pup(12),
	argus(13),
	emcon(14),
	xnet(15),
	chaos(16),
	udp(17),
	mux(18),
	dcnmeas(19),
	hmp(20),
	prm(21),
	xnsidp(22),
	trunk1(23),
	trunk2(24),
	leaf1(25),
	leaf2(26),
	rdp(27),
	irtp(28),
	isotp4(29),
	netblt(30),
	mfensp(31),
	meritinp(32),
	dccp(33),
	threepc(34), //3pc is not allowed ..
	idpr(35),
	xtp(36),
	ddp(37),
	idprcmtp(38),
	tp(39),
	il(40),
	ipv6(41),
	sdrp(42),
	ipv6route(43),
	ipv6frag(44),
	idrp(45),
	rsvp(46),
	gre(47),
	dsr(48),
	bna(49),
	esp(50),
	ah(51),
	inlsp(52),
	swipe(53),
	narp(54),
	mobile(55),
	tlsp(56),
	skip(57),
	ipv6icmp(58),
	ipv6nonxt(59),
	ipv6opts(60),
	cftp(62),
	satexpak(64),
	kryptolan(65),
	rvd(66),
	ippc(67),
	satmon(69),
	visa(70),
	ipcv(71),
	cpnx(72),
	cphb(73),
	wsn(74),
	pvp(75),
	brsatmon(76),
	sunnd(77),
	wbmon(78),
	wbexpak(79),
	isoip(80),
	vmtp(81),
	securevmtp(82),
	vines(83),
	ttp(84),
	iptm(84),
	nsfnetigp(85),
	dgp(86),
	tcf(87),
	eigrp(88),
	ospfigp(89),
	spriterpc(90),
	larp(91),
	mtp(92),
	ax25(93),
	ipip(94),
	micp(95),
	sccsp(96),
	etherip(97),
	encap(98),
	gmtp(100),
	ifmp(101),
	pnni(102),
	pim(103),
	aris(104),
	scps(105),
	qnx(106),
	an(107),
	ipcomp(108),
	snp(109),
	compaqpeer(110),
	ipxinip(111),
	vrrp(112),
	pgm(113),
	l2tp(115),
	ddx(116),
	iatp(117),
	stp(118),
	srp(119),
	uti(120),
	smp(121),
	sm(122),
	ptp(123),
	isisoveripv4(124),
	fire(125),
	crtp(126),
	crudp(127),
	sscopmce(128),
	iplt(129),
	sps(130),
	pipe(131),
	sctp(132),
	fc(133),
	rsvpe2eignore(134),
	mobilityheader(135),
	udplite(136),
	mplsinip(137),
	manet(138),
	hip(139),
	shim6(140),
	wesp(141),
	rohc(142),
	reserved(255);
	
	private int number;
	
	EipProtocol(int number){
		this.number = number;
	}
	
	public int getNumber(){
		return number;
	}
}
