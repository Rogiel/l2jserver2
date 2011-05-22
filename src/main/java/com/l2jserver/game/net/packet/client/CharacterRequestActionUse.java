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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterMoveType;
import com.l2jserver.service.game.character.CharacterAlreadyRunningServiceException;
import com.l2jserver.service.game.character.CharacterAlreadyWalkingServiceException;
import com.l2jserver.service.game.character.CharacterService;

/**
 * This packet notifies the server which character the player has chosen to use.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterRequestActionUse extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x56;

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService charService;

	private Action action;

	public enum Action {
		SIT_STAND(0), WALK_RUN(1),

		PRIVATE_STORE_SELL(10), PRIVATE_STORE_BUY(11),

		PET_FOLLOW_MOVE(15), PET_FOLLOW_MOVE2(21),

		PET_ATTACK(16), PET_ATTACK2(22),

		PET_STOP(17), PET_STOP2(23),

		PET_UNSUMMON(19),

		MOUNT_DISMOUNT(38),

		WILD_HOG_CANNON_SWITCH_MODE(32), WILD_HOG_CANNON_STOP(41),

		SOULESS_TOXIC_SMOKE(36), SOULESS_PARASITE_BURST(39),

		DWARVEN_MANUFACTURE(37);

		public final int id;

		Action(int id) {
			this.id = id;
		}

		public static Action fromID(int id) {
			for (final Action action : values())
				if (action.id == id)
					return action;
			return null;
		}
	}

	private boolean ctrlPressed;
	private boolean shiftPressed;

	@Inject
	public CharacterRequestActionUse(CharacterService charService) {
		this.charService = charService;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		action = Action.fromID(buffer.readInt());
		ctrlPressed = (buffer.readByte() == 1 ? true : false);
		shiftPressed = (buffer.readByte() == 1 ? true : false);
	}

	@Override
	public void process(final Lineage2Connection conn) {
		final L2Character character = conn.getCharacter();
		switch (action) {
		case SIT_STAND:
			// TODO
			break;
		case WALK_RUN:
			try {
				if (character.getMoveType() == CharacterMoveType.WALK) {
					charService.run(character);
				} else {
					charService.walk(character);
				}
			} catch (CharacterAlreadyWalkingServiceException e) {
				conn.sendActionFailed();
			} catch (CharacterAlreadyRunningServiceException e) {
				conn.sendActionFailed();
			}
			break;
		}
	}
}
