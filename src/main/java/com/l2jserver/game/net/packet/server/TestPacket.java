package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.packet.AbstractServerPacket;

public class TestPacket extends AbstractServerPacket {
	public static final int OPCODE = 0x00;

	@Override
	public void write(ChannelBuffer buffer) {

	}

	public TestPacket() {
		super(OPCODE);
	}
}
