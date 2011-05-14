package com.l2jserver.game.net.packet;

/**
 * An abstract {@link ServerPacket}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see ServerPacket
 */
public abstract class AbstractServerPacket implements ServerPacket {
	private final int opcode;

	public AbstractServerPacket(int opcode) {
		this.opcode = opcode;
	}

	@Override
	public int getOpcode() {
		return opcode;
	}
}
