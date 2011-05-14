package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.ClientPacket;
import com.l2jserver.game.net.packet.client.AuthLoginPacket;
import com.l2jserver.game.net.packet.client.CharacterCreatePacket;
import com.l2jserver.game.net.packet.client.EnterWorld;
import com.l2jserver.game.net.packet.client.LogoutPacket;
import com.l2jserver.game.net.packet.client.ProtocolVersionPacket;
import com.l2jserver.game.net.packet.client.RequestCharacterTemplatesPacket;
import com.l2jserver.game.net.packet.client.RequestGotoLobby;
import com.l2jserver.game.net.packet.client.RequestKeyMapping;
import com.l2jserver.game.net.packet.client.RequestManorList;

/**
 * This decoder reads an frame and decodes the packet in it. Each packet has an
 * fixed single opcode byte. Once the packet has been identified, reading is
 * done by the {@link ClientPacket} class.
 * <p>
 * Note that some packets have an additional opcode. This class also handle
 * those cases.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2PacketReader extends OneToOneDecoder {
	/**
	 * The handler name
	 */
	public static final String HANDLER_NAME = "packet.reader";

	/**
	 * The Google Guice {@link Injector}
	 */
	private final Injector injector;
	/**
	 * The logger
	 */
	private final Logger logger = LoggerFactory
			.getLogger(Lineage2PacketReader.class);

	/**
	 * The active Lineage 2 connection
	 */
	private Lineage2Connection connection;

	/**
	 * Creates a new instance
	 * 
	 * @param injector
	 *            the injector
	 */
	@Inject
	public Lineage2PacketReader(Injector injector) {
		this.injector = injector;
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer))
			return msg;
		final ChannelBuffer buffer = (ChannelBuffer) msg;
		final ClientPacket packet = createPacket(getPacketClass(buffer));
		if (packet == null)
			return null;
		packet.read(connection, buffer);
		return packet;
	}

	/**
	 * Crates a new instance of the packet <tt>type</tt>
	 * 
	 * @param type
	 *            the packet type
	 * @return the created packet
	 */
	private ClientPacket createPacket(Class<? extends ClientPacket> type) {
		if (type == null)
			return null;
		return injector.getInstance(type);
	}

	/**
	 * Discovers the packet type
	 * 
	 * @param buffer
	 *            the buffer
	 * @return the packet class
	 */
	private Class<? extends ClientPacket> getPacketClass(ChannelBuffer buffer) {
		final short opcode = buffer.readUnsignedByte();
		switch (opcode) {
		case LogoutPacket.OPCODE:
			return LogoutPacket.class;
		case ProtocolVersionPacket.OPCODE:
			return ProtocolVersionPacket.class;
		case AuthLoginPacket.OPCODE:
			return AuthLoginPacket.class;
		case CharacterCreatePacket.OPCODE:
			return CharacterCreatePacket.class;
		case RequestCharacterTemplatesPacket.OPCODE:
			return RequestCharacterTemplatesPacket.class;
		case 0xd0: // COMPOSED
			final int opcode2 = buffer.readUnsignedShort();
			switch (opcode2) {
			case RequestGotoLobby.OPCODE2:
				return RequestGotoLobby.class;
			case RequestKeyMapping.OPCODE2:
				return RequestKeyMapping.class;
			case RequestManorList.OPCODE2:
				return RequestManorList.class;
			default:
				logger.warn("Unknown opcode2 for 0xd0: 0x{}",
						Integer.toHexString(opcode2));
				break;
			}
			break;
		case EnterWorld.OPCODE:
			return EnterWorld.class;
		default:
			logger.warn("Unknown opcode: 0x{}", Integer.toHexString(opcode));
			break;
		}
		return null;
	}

	/**
	 * @return the connection
	 */
	public Lineage2Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Lineage2Connection connection) {
		this.connection = connection;
	}
}
