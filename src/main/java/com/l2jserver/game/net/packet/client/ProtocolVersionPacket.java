package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Injector;
import com.l2jserver.L2JConstants;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.service.logging.Logger;
import com.l2jserver.service.logging.guice.InjectLogger;

public class ProtocolVersionPacket extends AbstractClientPacket {
	public static final int OPCODE = 0x0e;

	// services
	@InjectLogger
	private final Logger logger = null;

	// packet
	private int version;

	@Override
	public void read(ChannelBuffer buffer) {
		this.version = buffer.readInt();
	}

	@Override
	public void process(Lineage2Connection conn, Injector injector) {
		if (L2JConstants.SUPPORTED_PROTOCOL != version) {
			logger.info(
					"Incorrect protocol version: {0}. Only {1} is supported.",
					version, L2JConstants.SUPPORTED_PROTOCOL);
			conn.close();
		}

	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
