/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
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
import com.l2jserver.game.net.packet.client.CM_ADMIN_COMMAND;
import com.l2jserver.game.net.packet.client.CM_AUTH_LOGIN;
import com.l2jserver.game.net.packet.client.CM_CHAR_ACTION;
import com.l2jserver.game.net.packet.client.CM_CHAR_APPEARING;
import com.l2jserver.game.net.packet.client.CM_ATTACK;
import com.l2jserver.game.net.packet.client.CM_CHAT;
import com.l2jserver.game.net.packet.client.CM_CHAR_CREATE;
import com.l2jserver.game.net.packet.client.CM_ACTION_USE;
import com.l2jserver.game.net.packet.client.CM_BYPASS;
import com.l2jserver.game.net.packet.client.CM_CHAR_REQ_INVENTORY;
import com.l2jserver.game.net.packet.client.CM_CHAR_MOVE;
import com.l2jserver.game.net.packet.client.CM_CHAR_OPEN_MAP;
import com.l2jserver.game.net.packet.client.CM_CHAR_SELECT;
import com.l2jserver.game.net.packet.client.CM_CHAR_POSITION;
import com.l2jserver.game.net.packet.client.CM_ENTER_WORLD;
import com.l2jserver.game.net.packet.client.CM_LOGOUT;
import com.l2jserver.game.net.packet.client.CM_PROTOCOL_VERSION;
import com.l2jserver.game.net.packet.client.CM_EXT_REQ_ALL_FORTRESS_INFO;
import com.l2jserver.game.net.packet.client.CM_REQUEST_CHAR_TEMPLATE;
import com.l2jserver.game.net.packet.client.CM_GOTO_LOBBY;
import com.l2jserver.game.net.packet.client.CM_EXT_REQ_KEY_MAPPING;
import com.l2jserver.game.net.packet.client.CM_EXT_REQ_MANOR_LIST;
import com.l2jserver.game.net.packet.client.CM_RESTART;

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
		case CM_LOGOUT.OPCODE:
			return CM_LOGOUT.class;
		case CM_PROTOCOL_VERSION.OPCODE:
			return CM_PROTOCOL_VERSION.class;
		case CM_AUTH_LOGIN.OPCODE:
			return CM_AUTH_LOGIN.class;
		case CM_CHAR_CREATE.OPCODE:
			return CM_CHAR_CREATE.class;
		case CM_REQUEST_CHAR_TEMPLATE.OPCODE:
			return CM_REQUEST_CHAR_TEMPLATE.class;
		case 0xd0: // CM_EXTENDED
			final int opcode2 = buffer.readUnsignedShort();
			switch (opcode2) {
			case CM_GOTO_LOBBY.OPCODE2:
				return CM_GOTO_LOBBY.class;
			case CM_EXT_REQ_KEY_MAPPING.OPCODE2:
				return CM_EXT_REQ_KEY_MAPPING.class;
			case CM_EXT_REQ_MANOR_LIST.OPCODE2:
				return CM_EXT_REQ_MANOR_LIST.class;
			case CM_EXT_REQ_ALL_FORTRESS_INFO.OPCODE2:
				return CM_EXT_REQ_ALL_FORTRESS_INFO.class;
			default:
				logger.warn("Unknown opcode2 for 0xd0: 0x{}",
						Integer.toHexString(opcode2));
				break;
			}
			break;
		case CM_CHAR_SELECT.OPCODE:
			return CM_CHAR_SELECT.class;
		case CM_CHAR_MOVE.OPCODE:
			return CM_CHAR_MOVE.class;
		case CM_RESTART.OPCODE:
			return CM_RESTART.class;
		case CM_CHAT.OPCODE:
			return CM_CHAT.class;
		case CM_CHAR_POSITION.OPCODE:
			return CM_CHAR_POSITION.class;
		case CM_ENTER_WORLD.OPCODE:
			return CM_ENTER_WORLD.class;
		case CM_CHAR_ACTION.OPCODE:
			return CM_CHAR_ACTION.class;
		case CM_CHAR_REQ_INVENTORY.OPCODE:
			return CM_CHAR_REQ_INVENTORY.class;
		case CM_ADMIN_COMMAND.OPCODE:
			return CM_ADMIN_COMMAND.class;
		case CM_BYPASS.OPCODE:
			return CM_BYPASS.class;
		case CM_CHAR_APPEARING.OPCODE:
			return CM_CHAR_APPEARING.class;
		case CM_ACTION_USE.OPCODE:
			return CM_ACTION_USE.class;
		case CM_CHAR_OPEN_MAP.OPCODE:
			return CM_CHAR_OPEN_MAP.class;
		case CM_ATTACK.OPCODE:
			return CM_ATTACK.class;
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
