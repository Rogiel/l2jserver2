package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.L2JConstants;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.service.logging.Logger;
import com.l2jserver.service.logging.LoggingService;

public class ProtocolVersionPacket extends AbstractClientPacket {
	public static final int OPCODE = 0x0e;

	// services
	private final Logger logger;
	
	// packet
	private int version;
	
	@Inject
	protected ProtocolVersionPacket(LoggingService logging) {
		logger = logging.getLogger(ProtocolVersionPacket.class);
	}

	@Override
	public void read(ChannelBuffer buffer) {
		this.version = buffer.readInt();
	}

	@Override
	public void process(Lineage2Connection conn, Injector injector) {
		if(L2JConstants.SUPPORTED_PROTOCOL != version) {
			logger.info("Incorrect protocol version: "+version);
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
