/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.ls.net.message;

import com.l2jserver.ls.net.Message;

public class AuthRequestMessage implements Message {
	private static final long serialVersionUID = -1983769628118961790L;

	private int id;
	private boolean acceptAlternate;
	private byte[] hexid;
	private int port;
	private boolean reserveHost;
	private int maxplayer;
	private String[] subnets;
	private String[] hosts;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the acceptAlternate
	 */
	public boolean isAcceptAlternate() {
		return acceptAlternate;
	}

	/**
	 * @param acceptAlternate
	 *            the acceptAlternate to set
	 */
	public void setAcceptAlternate(boolean acceptAlternate) {
		this.acceptAlternate = acceptAlternate;
	}

	/**
	 * @return the hexid
	 */
	public byte[] getHexid() {
		return hexid;
	}

	/**
	 * @param hexid
	 *            the hexid to set
	 */
	public void setHexid(byte[] hexid) {
		this.hexid = hexid;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the reserveHost
	 */
	public boolean isReserveHost() {
		return reserveHost;
	}

	/**
	 * @param reserveHost
	 *            the reserveHost to set
	 */
	public void setReserveHost(boolean reserveHost) {
		this.reserveHost = reserveHost;
	}

	/**
	 * @return the maxplayer
	 */
	public int getMaxplayer() {
		return maxplayer;
	}

	/**
	 * @param maxplayer
	 *            the maxplayer to set
	 */
	public void setMaxplayer(int maxplayer) {
		this.maxplayer = maxplayer;
	}

	/**
	 * @return the subnets
	 */
	public String[] getSubnets() {
		return subnets;
	}

	/**
	 * @param subnets
	 *            the subnets to set
	 */
	public void setSubnets(String[] subnets) {
		this.subnets = subnets;
	}

	/**
	 * @return the hosts
	 */
	public String[] getHosts() {
		return hosts;
	}

	/**
	 * @param hosts
	 *            the hosts to set
	 */
	public void setHosts(String[] hosts) {
		this.hosts = hosts;
	}
}