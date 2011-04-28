package com.l2jserver.game.net.packet;

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
