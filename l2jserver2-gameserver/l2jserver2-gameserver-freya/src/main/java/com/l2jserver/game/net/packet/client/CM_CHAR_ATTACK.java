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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.game.net.packet.server.SM_ACTION_FAILED;
import com.l2jserver.model.id.ObjectID;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.object.provider.ObjectIDResolver;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.game.character.ActorIsNotAttackableServiceException;
import com.l2jserver.service.game.character.CannotSetTargetServiceException;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.npc.NotAttackableNPCServiceException;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractClientPacket;
import com.l2jserver.util.geometry.Coordinate;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_ATTACK extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x01;

	/**
	 * The Logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link CharacterService}
	 */
	private final CharacterService charService;
	/**
	 * The {@link ObjectID} resolver
	 */
	private final ObjectIDResolver idResolver;

	/**
	 * The {@link ObjectID} being attacked
	 */
	private int objectId;
	/**
	 * The position of the target
	 */
	@SuppressWarnings("unused")
	private Coordinate origin;
	/**
	 * The attack action type
	 */
	@SuppressWarnings("unused")
	private CharacterAttackAction action;

	/**
	 * Enumeration of all possible attack actions
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum CharacterAttackAction {
		/**
		 * Normal click
		 */
		CLICK(0),
		/**
		 * Clicked with shift-click
		 */
		SHIFT_CLICK(1);

		/**
		 * The attack action numeric id
		 */
		public final int id;

		/**
		 * @param id
		 *            the action numeric id
		 */
		CharacterAttackAction(int id) {
			this.id = id;
		}

		/**
		 * Resolves the numeric action id to an {@link CharacterAttackAction}
		 * type
		 * 
		 * @param id
		 *            the numeric id
		 * @return the resolved action
		 */
		public static CharacterAttackAction fromID(int id) {
			for (final CharacterAttackAction action : values())
				if (action.id == id)
					return action;
			return null;
		}
	}

	/**
	 * @param charService
	 *            the character service
	 * @param idResolver
	 *            the object id resolver
	 */
	@Inject
	public CM_CHAR_ATTACK(CharacterService charService, ObjectIDResolver idResolver) {
		this.charService = charService;
		this.idResolver = idResolver;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		this.objectId = buffer.readInt();
		this.origin = Coordinate.fromXYZ(buffer.readInt(), buffer.readInt(),
				buffer.readInt());
		this.action = CharacterAttackAction.fromID(buffer.readByte());
	}

	@Override
	public void process(final Lineage2Client conn) {
		final L2Character character = conn.getCharacter();
		// since this is an erasure type, this is safe.
		final ObjectID<Actor> id = idResolver.resolve(objectId);
		if (!(id instanceof ActorID)) {
			conn.write(SM_ACTION_FAILED.SHARED_INSTANCE);
			log.warn("Player {} is trying to attack {}, which is not an actor",
					character, id);
			return;
		}
		final Actor actor = id.getObject();
		try {
			charService.attack(character, actor);
		} catch (CannotSetTargetServiceException e) {
			conn.sendActionFailed();
		} catch (ActorIsNotAttackableServiceException e) {
			conn.sendActionFailed();
		} catch (NotAttackableNPCServiceException e) {
			conn.sendActionFailed();
		}
	}
}
