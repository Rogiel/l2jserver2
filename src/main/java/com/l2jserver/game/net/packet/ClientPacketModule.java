package com.l2jserver.game.net.packet;

import com.google.inject.AbstractModule;
import com.l2jserver.game.net.packet.client.ProtocolVersionPacket;

public class ClientPacketModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ProtocolVersionPacket.class);
	}
}
