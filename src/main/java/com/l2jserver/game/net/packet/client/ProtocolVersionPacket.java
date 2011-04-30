package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.L2JConstants;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.KeyPacket;

public class ProtocolVersionPacket extends AbstractClientPacket {
	public static final int OPCODE = 0x0e;

	// services
	private final Logger logger = LoggerFactory
			.getLogger(ProtocolVersionPacket.class);

	// packet
	private long version;

	@Override
	public void read(ChannelBuffer buffer) {
		this.version = buffer.readUnsignedInt();
	}

	@Override
	public void process(final Lineage2Connection conn) {
		// generate a new key
		final byte[] key = conn.getDecrypter().enable();

		if (L2JConstants.SUPPORTED_PROTOCOL != version) {
			logger.info(
					"Incorrect protocol version: {0}. Only {1} is supported.",
					version, L2JConstants.SUPPORTED_PROTOCOL);
			// notify wrong protocol and close connection
			conn.write(new KeyPacket(key, false)).addListener(
					new ChannelFutureListener() {
						@Override
						public void operationComplete(ChannelFuture future)
								throws Exception {
							// close connection
							conn.close();
						}
					});
			return;
		}
		// activate encrypter once packet has been sent.
		conn.write(new KeyPacket(key, true)).addListener(
				new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future)
							throws Exception {
						// enable encrypter
						conn.getEncrypter().setKey(key);
						conn.getEncrypter().setEnabled(true);
					}
				});
	}

	public long getVersion() {
		return version;
	}
}
