package com.l2jserver;

/**
 * Represents the protocol version used by the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ProtocolVersion {
	FREYA(216);

	public final int version;

	ProtocolVersion(int version) {
		this.version = version;
	}

	public static ProtocolVersion fromVersion(int version) {
		for (ProtocolVersion v : values()) {
			if (v.version == version)
				return v;
		}
		return null;
	}
}
