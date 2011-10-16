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

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
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
public class CM_ACTION_USE extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x56;

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService charService;

	/**
	 * The action to be performed
	 */
	private Action action;

	/**
	 * The enumeration of all possible actions
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum Action {
		/**
		 * Toggles the character in SIT or STAND mode
		 */
		SIT_STAND(0),
		/**
		 * Toggles the character in WALK or RUN mode
		 */
		WALK_RUN(1),

		/**
		 * Stats a new private store sell
		 */
		PRIVATE_STORE_SELL(10),
		/**
		 * Stats a new private store buy
		 */
		PRIVATE_STORE_BUY(11),
		/**
		 * Sets the pet in follow mode
		 */
		PET_FOLLOW_MOVE(15),
		/**
		 * Sets the pet in follow mode 2
		 */
		PET_FOLLOW_MOVE2(21),
		/**
		 * Orders the pet to attack
		 */
		PET_ATTACK(16),
		/**
		 * Orders the pet to attack (second type)
		 */
		PET_ATTACK2(22),
		/**
		 * Orders the pet to stop
		 */
		PET_STOP(17),
		/**
		 * Orders the pet to stop (second type)
		 */
		PET_STOP2(23),
		/**
		 * Unsummons the pet
		 */
		PET_UNSUMMON(19),
		/**
		 * Mounts or dismount from pet
		 */
		MOUNT_DISMOUNT(38),
		/**
		 * Switch Wild Hog Cannon mode
		 */
		WILD_HOG_CANNON_SWITCH_MODE(32),
		/**
		 * Stops Wild Hog Cannon
		 */
		WILD_HOG_CANNON_STOP(41),

		/**
		 * Souless toxic smoke
		 */
		SOULESS_TOXIC_SMOKE(36),
		/**
		 * Souless parasite burst
		 */
		SOULESS_PARASITE_BURST(39),
		/**
		 * Creates a new darwven manufacture
		 */
		DWARVEN_MANUFACTURE(37);

		/**
		 * The numeric action id
		 */
		public final int id;

		/**
		 * @param id
		 *            the numeric action id
		 */
		Action(int id) {
			this.id = id;
		}

		/**
		 * Resolves the numeric id into an Java type action
		 * 
		 * @param id
		 *            the numeric id
		 * @return the resolved action
		 */
		public static Action fromID(int id) {
			for (final Action action : values())
				if (action.id == id)
					return action;
			return null;
		}
	}

	/**
	 * If CTRL key was pressed for this action
	 */
	@SuppressWarnings("unused")
	private boolean ctrlPressed;
	/**
	 * If SHIFT key was pressed for this action
	 */
	@SuppressWarnings("unused")
	private boolean shiftPressed;

	/**
	 * @param charService
	 *            the character service
	 */
	@Inject
	public CM_ACTION_USE(CharacterService charService) {
		this.charService = charService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		action = Action.fromID(buffer.readInt());
		ctrlPressed = (buffer.readByte() == 1 ? true : false);
		shiftPressed = (buffer.readByte() == 1 ? true : false);
	}

	@Override
	public void process(final Lineage2Client conn) {
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
