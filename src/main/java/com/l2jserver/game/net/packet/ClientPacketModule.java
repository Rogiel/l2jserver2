package com.l2jserver.game.net.packet;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.l2jserver.game.net.packet.client.ProtocolVersionPacket;

/**
 * Google Guice {@link Module} for client packets
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClientPacketModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ProtocolVersionPacket.class);
	}
}
