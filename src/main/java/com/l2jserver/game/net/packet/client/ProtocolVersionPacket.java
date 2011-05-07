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

/**
 * In this packet the client is informing its protocol version. It is possible
 * to do an test and refuse invalid protocol versions. After this packet, the
 * messages received and sent are all encrypted, except for the encryptation key
 * which is sent here.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ProtocolVersionPacket extends AbstractClientPacket {
	public static final int OPCODE = 0x0e;

	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory
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
		log.debug("Decrypter has been enabled");
		
		log.debug("Client protocol version: {}", version);
		if (L2JConstants.SUPPORTED_PROTOCOL != version) {
			log.info(
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
						log.debug("Encrypter has been enabled");
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
