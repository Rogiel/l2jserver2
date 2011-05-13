package com.l2jserver;

/**
 * Represents the protocol version used by the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ProtocolVersion {
	RELEASE(0), //UNK ID
	FREYA(216);

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
		if(this == version)
			return true;
		if(this.parent == null)
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
