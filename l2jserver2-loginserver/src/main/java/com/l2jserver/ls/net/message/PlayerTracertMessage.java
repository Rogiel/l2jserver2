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

/**
 * @author mrTJO
 * 
 */
public class PlayerTracertMessage implements Message {
	private static final long serialVersionUID = 5148890222662873608L;
	
	private String account;
	private String pcIp;
	private String hop1;
	private String hop2;
	private String hop3;
	private String hop4;

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the pcIp
	 */
	public String getPcIp() {
		return pcIp;
	}

	/**
	 * @param pcIp
	 *            the pcIp to set
	 */
	public void setPcIp(String pcIp) {
		this.pcIp = pcIp;
	}

	/**
	 * @return the hop1
	 */
	public String getHop1() {
		return hop1;
	}

	/**
	 * @param hop1
	 *            the hop1 to set
	 */
	public void setHop1(String hop1) {
		this.hop1 = hop1;
	}

	/**
	 * @return the hop2
	 */
	public String getHop2() {
		return hop2;
	}

	/**
	 * @param hop2
	 *            the hop2 to set
	 */
	public void setHop2(String hop2) {
		this.hop2 = hop2;
	}

	/**
	 * @return the hop3
	 */
	public String getHop3() {
		return hop3;
	}

	/**
	 * @param hop3
	 *            the hop3 to set
	 */
	public void setHop3(String hop3) {
		this.hop3 = hop3;
	}

	/**
	 * @return the hop4
	 */
	public String getHop4() {
		return hop4;
	}

	/**
	 * @param hop4
	 *            the hop4 to set
	 */
	public void setHop4(String hop4) {
		this.hop4 = hop4;
	}
}