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
package com.l2jserver.service.network.model;

/**
 * Represents the protocol version used by the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ProtocolVersion {
	/**
	 * The Release version, this is acctually a pseudo version. It never really
	 * existed.
	 */
	RELEASE(0),
	/**
	 * The Interlude(746) version
	 */
	INTERLUDE(746, RELEASE),
	/**
	 * The Freya(216) version
	 */
	FREYA(216, RELEASE),
	/**
	 * The High5(268) version
	 */
	HIGH5(268, FREYA);
	
	/**
	 * The parent version
	 */
	public final ProtocolVersion parent;
	/**
	 * This version numeric ID
	 */
	public final int version;

	/**
	 * Creates a new instance
	 * 
	 * @param version
	 *            the version integer id
	 */
	ProtocolVersion(int version) {
		this(version, null);
	}

	/**
	 * Creates a new instance with a parent version
	 * 
	 * @param version
	 *            the version integer id
	 * @param parent
	 *            the parent version
	 */
	ProtocolVersion(int version, ProtocolVersion parent) {
		this.version = version;
		this.parent = parent;
	}

	/**
	 * Checks if an given version is compatible with this
	 * 
	 * @param version
	 *            the target version to be tested
	 * @return true if version is compatible
	 */
	public boolean supports(ProtocolVersion version) {
		if (this == version)
			return true;
		if (this.parent == null)
			return false;
		return this.parent.supports(version);
	}

	/**
	 * @param version
	 *            the version integer id
	 * @return the detected version from the numeric id. Can be <tt>null</tt>
	 */
	public static ProtocolVersion fromVersion(int version) {
		for (ProtocolVersion v : values()) {
			if (v.version == version)
				return v;
		}
		return null;
	}
}
