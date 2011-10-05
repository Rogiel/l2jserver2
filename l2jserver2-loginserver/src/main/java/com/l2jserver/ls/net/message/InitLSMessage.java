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

public class InitLSMessage implements Message {
	private static final long serialVersionUID = 6277036140453399066L;

	private int rev;
	private byte[] key;

	/**
	 * @return the rev
	 */
	public int getRev() {
		return rev;
	}

	/**
	 * @param rev
	 *            the rev to set
	 */
	public void setRev(int rev) {
		this.rev = rev;
	}

	/**
	 * @return the key
	 */
	public byte[] getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(byte[] key) {
		this.key = key;
	}
}