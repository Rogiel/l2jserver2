package com.l2jserver.game.net.packet;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * Google Guice {@link Module} for packets
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PacketModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new ClientPacketModule());
		install(new ServerPacketModule());
	}
}
