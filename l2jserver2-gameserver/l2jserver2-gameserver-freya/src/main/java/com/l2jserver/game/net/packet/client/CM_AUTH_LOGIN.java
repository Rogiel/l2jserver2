/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.game.net.packet.client;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.game.net.packet.server.SM_CHAR_LIST;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.provider.AccountIDProvider;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.Lineage2Session;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;
import com.l2jserver.util.BufferUtils;

/**
 * This packet is sent by the client once the login server has authorized
 * authentication into this server. A new {@link Lineage2Session} object will be
 * set to the current connection and the character list is sent to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_AUTH_LOGIN extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x2b;

	/**
	 * The {@link CharacterDAO} implementation
	 */
	private final CharacterDAO characterDao;
	/**
	 * The {@link AccountID} factory
	 */
	private final AccountIDProvider accountIdFactory;

	// packet
	/**
	 * User account name
	 */
	private String loginName;
	/**
	 * The play key 1
	 */
	private int playKey1;
	/**
	 * The play key 2
	 */
	private int playKey2;
	/**
	 * The login key 1
	 */
	private int loginKey1;
	/**
	 * The login key 2
	 */
	private int loginKey2;

	/**
	 * @param characterDao
	 *            the character DAO
	 * @param accountIdFactory
	 *            the account id factory
	 */
	@Inject
	public CM_AUTH_LOGIN(CharacterDAO characterDao,
			AccountIDProvider accountIdFactory) {
		this.characterDao = characterDao;
		this.accountIdFactory = accountIdFactory;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		this.loginName = BufferUtils.readString(buffer).toLowerCase();
		this.playKey1 = buffer.readInt();
		this.playKey2 = buffer.readInt();
		this.loginKey1 = buffer.readInt();
		this.loginKey2 = buffer.readInt();
	}

	@Override
	public void process(final Lineage2Client conn) {
		final AccountID accountId = accountIdFactory.resolveID(loginName);
		conn.setSession(new Lineage2Session(accountId, playKey1, playKey2,
				loginKey1, loginKey2));

		final List<L2Character> chars = characterDao.selectByAccount(accountId);
		conn.write(SM_CHAR_LIST.fromL2Session(conn.getSession(),
				chars.toArray(new L2Character[chars.size()])));
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
