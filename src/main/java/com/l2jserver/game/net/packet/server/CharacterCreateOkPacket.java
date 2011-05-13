package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractServerPacket;

/**
 * An packet informing that the character was created with success.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterCreateOkPacket extends AbstractServerPacket {
	public static final int OPCODE = 0x0f;

	public static final CharacterCreateOkPacket INSTANCE = new CharacterCreateOkPacket();

	public CharacterCreateOkPacket() {
		super(OPCODE);
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(0x01);
	}
}
