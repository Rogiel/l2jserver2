package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public interface ServerPacket extends Packet {
	void write(ChannelBuffer buffer);
	int getOpcode();
}
