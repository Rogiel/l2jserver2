package com.l2jserver.game.net.packet;

import org.jboss.netty.buffer.ChannelBuffer;

public interface ClientPacket extends Packet {
	void read(ChannelBuffer buffer);
}
