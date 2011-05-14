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
	 * High5(217)
	 */
	HIGH5(217, FREYA);

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
