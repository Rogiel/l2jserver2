package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.InventoryPacket;

/**
 * The client is requesting a logout. Currently, when this packet is received
 * the connection is immediately closed.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class EnterWorld extends AbstractClientPacket {
	public static final int OPCODE = 0x11;

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.readBytes(new byte[32]); // Unknown Byte Array
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readInt(); // Unknown Value
		buffer.readBytes(new byte[32]); // Unknown Byte Array
		buffer.readInt(); // Unknown Value
		// TODO parse tracert
		// for (int i = 0; i < 5; i++)
		// for (int o = 0; o < 4; o++)
		// tracert[i][o] = readC();
	}

	@Override
	public void process(final Lineage2Connection conn) {
		conn.write(new InventoryPacket(conn.getCharacter().getInventory()));
	}
}
