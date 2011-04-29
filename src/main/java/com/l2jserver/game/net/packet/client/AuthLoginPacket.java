package com.l2jserver.game.net.packet.client;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.service.game.world.WorldService;

public class AuthLoginPacket extends AbstractClientPacket {
	public static final int OPCODE = 0x0e;
	
	@Inject
	private WorldService world;

	// packet
	private String loginName;
	private int playKey1;
	private int playKey2;
	private int loginKey1;
	private int loginKey2;

	@Override
	public void read(ChannelBuffer buffer) {
		this.loginName = buffer.readBytes(buffer.bytesBefore((byte) 0x00))
				.toString(Charset.defaultCharset());
		this.playKey1 = buffer.readInt();
		this.playKey2 = buffer.readInt();
		this.loginKey1 = buffer.readInt();
		this.loginKey2 = buffer.readInt();
	}

	@Override
	public void process(final Lineage2Connection conn) {
		// assume it is correct, for now
		// send character list
		world.getEventDispatcher().dispatch(null);
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @return the playKey1
	 */
	public int getPlayKey1() {
		return playKey1;
	}

	/**
	 * @return the playKey2
	 */
	public int getPlayKey2() {
		return playKey2;
	}

	/**
	 * @return the loginKey1
	 */
	public int getLoginKey1() {
		return loginKey1;
	}

	/**
	 * @return the loginKey2
	 */
	public int getLoginKey2() {
		return loginKey2;
	}
}
