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

import java.util.ArrayList;

import com.l2jserver.ls.net.Message;

/**
 * @author -Wooden-
 * 
 */
public class ServerStatusMessage implements Message {
	private static final long serialVersionUID = 251392846109402522L;

	private ArrayList<Attribute> attributes;

	public static final String[] STATUS_STRING = { "Auto", "Good", "Normal",
			"Full", "Down", "Gm Only" };

	public static final int SERVER_LIST_STATUS = 0x01;
	public static final int SERVER_TYPE = 0x02;
	public static final int SERVER_LIST_SQUARE_BRACKET = 0x03;
	public static final int MAX_PLAYERS = 0x04;
	public static final int SERVER_AGE = 0x05;

	// Server Status
	public static final int STATUS_AUTO = 0x00;
	public static final int STATUS_GOOD = 0x01;
	public static final int STATUS_NORMAL = 0x02;
	public static final int STATUS_FULL = 0x03;
	public static final int STATUS_DOWN = 0x04;
	public static final int STATUS_GM_ONLY = 0x05;

	// Server Types
	public static final int SERVER_NORMAL = 0x01;
	public static final int SERVER_RELAX = 0x02;
	public static final int SERVER_TEST = 0x04;
	public static final int SERVER_NOLABEL = 0x08;
	public static final int SERVER_CREATION_RESTRICTED = 0x10;
	public static final int SERVER_EVENT = 0x20;
	public static final int SERVER_FREE = 0x40;

	// Server Ages
	public static final int SERVER_AGE_ALL = 0x00;
	public static final int SERVER_AGE_15 = 0x0F;
	public static final int SERVER_AGE_18 = 0x12;

	public static final int ON = 0x01;
	public static final int OFF = 0x00;

	public static class Attribute {
		public int id;
		public int value;

		Attribute(int pId, int pValue) {
			id = pId;
			value = pValue;
		}
	}

	/**
	 * @return the attributes
	 */
	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}
}