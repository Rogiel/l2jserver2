package com.l2jserver.game.net.packet;

import com.google.inject.AbstractModule;

public class PacketModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new ClientPacketModule());
		install(new ServerPacketModule());
	}
}
