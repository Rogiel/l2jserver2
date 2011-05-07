package com.l2jserver.game.net.packet.client;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.CharacterSelectionListPacket;
import com.l2jserver.model.world.L2Character;

/**
 * Requests the list of characters to be displayed in the lobby. The list of
 * characters is sent to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RequestGotoLobby extends AbstractClientPacket {
	public static final int OPCODE1 = 0xd0;
	public static final int OPCODE2 = 0x36;

	private final CharacterDAO characterDao;

	@Inject
	public RequestGotoLobby(CharacterDAO characterDao) {
		this.characterDao = characterDao;
	}

	@Override
	public void read(ChannelBuffer buffer) {
	}

	@Override
	public void process(final Lineage2Connection conn) {
		final List<L2Character> chars = characterDao.selectByAccount(conn
				.getSession().getUsername());
		conn.write(CharacterSelectionListPacket.fromL2Session(
				conn.getSession(), chars.toArray(new L2Character[0])));
	}
}
