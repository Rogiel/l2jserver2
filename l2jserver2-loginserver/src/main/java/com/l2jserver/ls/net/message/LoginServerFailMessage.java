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

public class LoginServerFailMessage implements Message {
	private static final long serialVersionUID = -4031976482500361984L;
	
	private static final String[] REASONS = { "None", "Reason: ip banned",
			"Reason: ip reserved", "Reason: wrong hexid",
			"Reason: id reserved", "Reason: no free ID", "Not authed",
			"Reason: already logged in" };
	private int reason;

	/**
	 * @return the reason
	 */
	public int getReason() {
		return reason;
	}
	
	/**
	 * @return the reason
	 */
	public String getReasonAsString() {
		return REASONS[reason];
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(int reason) {
		this.reason = reason;
	}
}