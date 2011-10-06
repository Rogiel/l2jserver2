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
 * @author -Wooden-
 * 
 */
public class PlayerAuthRequestMessage implements Message {
	private static final long serialVersionUID = 4583147564841961379L;
	
	private String account;
	private int playOkId1;
	private int playOkId2;
	private int loginOkId1;
	private int loginOkId2;

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
	 * @return the playOkId1
	 */
	public int getPlayOkId1() {
		return playOkId1;
	}

	/**
	 * @param playOkId1
	 *            the playOkId1 to set
	 */
	public void setPlayOkId1(int playOkId1) {
		this.playOkId1 = playOkId1;
	}

	/**
	 * @return the playOkId2
	 */
	public int getPlayOkId2() {
		return playOkId2;
	}

	/**
	 * @param playOkId2
	 *            the playOkId2 to set
	 */
	public void setPlayOkId2(int playOkId2) {
		this.playOkId2 = playOkId2;
	}

	/**
	 * @return the loginOkId1
	 */
	public int getLoginOkId1() {
		return loginOkId1;
	}

	/**
	 * @param loginOkId1
	 *            the loginOkId1 to set
	 */
	public void setLoginOkId1(int loginOkId1) {
		this.loginOkId1 = loginOkId1;
	}

	/**
	 * @return the loginOkId2
	 */
	public int getLoginOkId2() {
		return loginOkId2;
	}

	/**
	 * @param loginOkId2
	 *            the loginOkId2 to set
	 */
	public void setLoginOkId2(int loginOkId2) {
		this.loginOkId2 = loginOkId2;
	}
}