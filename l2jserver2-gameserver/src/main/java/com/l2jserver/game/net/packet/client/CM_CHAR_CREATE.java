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
import com.l2jserver.game.net.packet.server.SM_CHAR_CREATE_FAIL;
import com.l2jserver.game.net.packet.server.SM_CHAR_CREATE_OK;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.service.game.character.CharacterInvalidAppearanceException;
import com.l2jserver.service.game.character.CharacterInvalidNameException;
import com.l2jserver.service.game.character.CharacterNameAlreadyExistsException;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.util.BufferUtils;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_CHAR_CREATE extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x0c;

	// services and daos
	/**
	 * The {@link CharacterService} implementation
	 */
	private final CharacterService characterService;

	// packet
	/**
	 * The name of the new character
	 */
	private String name;
	/**
	 * The race of the new character. Note that this is ignored and the template
	 * value is used.
	 */
	@SuppressWarnings("unused")
	private CharacterRace race;
	/**
	 * The sex of the new character. Note that this is ignored and the template
	 * value is used.
	 */
	private ActorSex sex;
	/**
	 * The class of the new character
	 */
	private CharacterClass characterClass;

	/**
	 * The new character intelligence. Note that this is ignored and the
	 * template value is used.
	 */
	@SuppressWarnings("unused")
	private int intelligence;
	/**
	 * The new character intelligence. Note that this is ignored and the
	 * template value is used.
	 */
	@SuppressWarnings("unused")
	private int strength;
	/**
	 * The new character strength. Note that this is ignored and the template
	 * value is used.
	 */
	@SuppressWarnings("unused")
	private int concentration;
	/**
	 * The new character concentration. Note that this is ignored and the
	 * template value is used.
	 */
	@SuppressWarnings("unused")
	private int mentality;
	/**
	 * The new character mentality. Note that this is ignored and the template
	 * value is used.
	 */
	@SuppressWarnings("unused")
	private int dexterity;
	/**
	 * The new character dexterity. Note that this is ignored and the template
	 * value is used.
	 */
	@SuppressWarnings("unused")
	private int witness;

	/**
	 * The new character hair style
	 */
	private CharacterHairStyle hairStyle;
	/**
	 * The new character hair color
	 */
	private CharacterHairColor hairColor;
	/**
	 * The new character face
	 */
	private CharacterFace face;

	/**
	 * @param characterService the character service
	 */
	@Inject
	public CM_CHAR_CREATE(CharacterService characterService) {
		this.characterService = characterService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		name = BufferUtils.readString(buffer);
		race = CharacterRace.fromOption(buffer.readInt());
		sex = ActorSex.fromOption(buffer.readInt());
		characterClass = CharacterClass.fromID(buffer.readInt());

		intelligence = buffer.readInt();
		strength = buffer.readInt();
		concentration = buffer.readInt();
		mentality = buffer.readInt();
		dexterity = buffer.readInt();
		witness = buffer.readInt();

		hairStyle = CharacterHairStyle.fromOption(buffer.readInt());
		hairColor = CharacterHairColor.fromOption(buffer.readInt());
		face = CharacterFace.fromOption(buffer.readInt());
	}

	@Override
	public void process(final Lineage2Client conn) {
		try {
			final L2Character character = characterService.create(name, conn
					.getSession().getAccountID(), sex, characterClass,
					hairStyle, hairColor, face);

			if (character != null)
				conn.write(SM_CHAR_CREATE_OK.INSTANCE);
			else
				conn.write(new SM_CHAR_CREATE_FAIL(
						SM_CHAR_CREATE_FAIL.Reason.REASON_CREATION_FAILED));
		} catch (CharacterInvalidNameException e) {
			conn.write(new SM_CHAR_CREATE_FAIL(
					SM_CHAR_CREATE_FAIL.Reason.REASON_16_ENG_CHARS));
		} catch (CharacterInvalidAppearanceException e) {
			conn.write(new SM_CHAR_CREATE_FAIL(
					SM_CHAR_CREATE_FAIL.Reason.REASON_CREATION_FAILED));
		} catch (CharacterNameAlreadyExistsException e) {
			conn.write(new SM_CHAR_CREATE_FAIL(
					SM_CHAR_CREATE_FAIL.Reason.REASON_NAME_ALREADY_EXISTS));
		}
	}
}
