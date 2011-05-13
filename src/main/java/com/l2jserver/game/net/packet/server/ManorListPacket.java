package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.util.BufferUtils;

/**
 * This packet send the manor list to the client
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ManorListPacket extends AbstractServerPacket {
	/**
	 * Message OPCODE
	 */
	public static final int OPCODE = 0xfe;

	private String[] manors;

	public ManorListPacket(String... manors) {
		super(OPCODE);
		this.manors = manors;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeShort(0x22);
		buffer.writeInt(manors.length);
		int i = 1;
		for (String manor : manors) {
			buffer.writeInt(i++);
			BufferUtils.writeString(buffer, manor);
		}
	}
}
