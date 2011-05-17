/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.game;

/**
 * Represents the protocol version used by the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ProtocolVersion {
	/**
	 * Release version
	 */
	RELEASE(0),
	/**
	 * Freya(216)
	 */
	FREYA(216, RELEASE),
	/**
	 * High5(268)
	 */
	HIGH5(268, FREYA);

	public final ProtocolVersion parent;
	public final int version;

	ProtocolVersion(int version) {
		this(version, null);
	}

	ProtocolVersion(int version, ProtocolVersion parent) {
		this.version = version;
		this.parent = parent;
	}

	public boolean supports(ProtocolVersion version) {
		if (this == version)
			return true;
		if (this.parent == null)
			return false;
		return this.parent.supports(version);
	}

	public static ProtocolVersion fromVersion(int version) {
		for (ProtocolVersion v : values()) {
			if (v.version == version)
				return v;
		}
		return null;
	}
}
