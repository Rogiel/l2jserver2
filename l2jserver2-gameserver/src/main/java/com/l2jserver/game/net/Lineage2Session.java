/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.game.net;

import com.l2jserver.model.id.AccountID;

/**
 * Lineage 2 session with the username and loginserver keys
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2Session {
	/**
	 * The account ID
	 */
	private final AccountID accountID;

	/**
	 * The play key, part 1
	 */
	private final int playKey1;
	/**
	 * The play key, part 2
	 */
	private final int playKey2;

	/**
	 * The login key, part 1
	 */
	private final int loginKey1;
	/**
	 * The login key, part 2
	 */
	private final int loginKey2;

	/**
	 * Creates a new instance
	 * 
	 * @param accountID
	 *            the account ID
	 * @param playOK1
	 *            the play key, part 1
	 * @param playOK2
	 *            the play key, part 2
	 * @param loginOK1
	 *            the login key, part 1
	 * @param loginOK2
	 *            the login key, part 2
	 */
	public Lineage2Session(AccountID accountID, int playOK1, int playOK2,
			int loginOK1, int loginOK2) {
		this.accountID = accountID;
		this.playKey1 = playOK1;
		this.playKey2 = playOK2;
		this.loginKey1 = loginOK1;
		this.loginKey2 = loginOK2;
	}

	/**
	 * @return the account ID
	 */
	public AccountID getAccountID() {
		return accountID;
	}

	/**
	 * @return the playKey1
	 */
	public int getPlayKey1() {
		return playKey1;
	}

	/**
	 * @return the playKey2
	 */
	public int getPlayKey2() {
		return playKey2;
	}

	/**
	 * @return the loginKey1
	 */
	public int getLoginKey1() {
		return loginKey1;
	}

	/**
	 * @return the loginKey2
	 */
	public int getLoginKey2() {
		return loginKey2;
	}

	@Override
	public String toString() {
		return "Lineage2Session [accountID=" + accountID + ", playKey1="
				+ playKey1 + ", playKey2=" + playKey2 + ", loginKey1="
				+ loginKey1 + ", loginKey2=" + loginKey2 + "]";
	}
}
