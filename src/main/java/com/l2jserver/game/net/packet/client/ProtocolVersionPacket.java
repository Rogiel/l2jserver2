package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Injector;
import com.l2jserver.game.net.packet.AbstractClientPacket;

public class ProtocolVersionPacket extends AbstractClientPacket {
	public static final int OPCODE = 0x0e;

	private int version;

	@Override
	public void read(ChannelBuffer buffer) {
		this.version = buffer.readInt();
	}

	@Override
	public void process(Injector injector) {

	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
