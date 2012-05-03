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
package com.l2jserver.game.net.packet.server;

import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.BELT;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.CHEST;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.CLOAK;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.DECORATION_1;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.DECORATION_2;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.DECORATION_3;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.DECORATION_4;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.DECORATION_5;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.DECORATION_6;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.FEET;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.GLOVES;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.HAIR1;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.HAIR2;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.HEAD;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEFT_BRACELET;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEFT_EAR;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEFT_FINGER;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEFT_HAND;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEGS;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.NECK;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_BRACELET;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_EAR;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_FINGER;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_HAND;

import java.util.Collection;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.Lineage2Session;
import com.l2jserver.service.network.model.ProtocolVersion;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;
import com.l2jserver.util.BufferUtils;

/**
 * The list of characters sent to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_LIST extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x13;

	/**
	 * The account username
	 */
	private final String loginName;
	/**
	 * The session ID
	 */
	private final int sessionId;
	// private int lastCharacterId;
	/**
	 * The list of character to be displayed
	 */
	private final L2Character[] characters;

	/**
	 * @param loginName
	 *            the account id
	 * @param sessionId
	 *            the session id
	 * @param lastCharacterId
	 *            the last character used
	 * @param characters
	 *            the characters
	 */
	public SM_CHAR_LIST(String loginName, int sessionId, int lastCharacterId,
			L2Character... characters) {
		super(OPCODE);
		this.loginName = loginName;
		this.sessionId = sessionId;
		// this.lastCharacterId = lastCharacterId;
		this.characters = characters;
	}

	/**
	 * @param session
	 *            the session
	 * @param characters
	 *            the characters
	 * @return an {@link SM_CHAR_LIST} instance
	 */
	public static SM_CHAR_LIST fromL2Session(Lineage2Session session,
			L2Character... characters) {
		return new SM_CHAR_LIST(session.getAccountID().getID(),
				session.getPlayKey2(), -1, characters);
	}

	/**
	 * @param session
	 *            the session
	 * @param characters
	 *            the characters
	 * @return an {@link SM_CHAR_LIST} instance
	 */
	public static SM_CHAR_LIST fromL2Session(Lineage2Session session,
			Collection<L2Character> characters) {
		return fromL2Session(session,
				characters.toArray(new L2Character[characters.size()]));
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(characters.length);

		// Can prevent players from creating new characters (if 0);
		// if 1 the client will ask if chars may be created
		// (RequestCharacterTemplatesPacket) Response: (CharacterTemplatePacket)
		if (conn.supports(ProtocolVersion.FREYA)) {
			buffer.writeInt(7); // max chars
			buffer.writeByte(0x00);
		}

		for (final L2Character character : characters) {
			BufferUtils.writeString(buffer, character.getName());
			buffer.writeInt(character.getID().getID());
			BufferUtils.writeString(buffer, loginName);
			buffer.writeInt(sessionId);
			buffer.writeInt((character.getClanID() != null ? character
					.getClanID().getID() : 0x01));
			buffer.writeInt(0x00);

			buffer.writeInt(character.getSex().option);
			buffer.writeInt(character.getRace().id);

			buffer.writeInt(character.getCharacterClass().id);

			buffer.writeInt(0x01); // active

			buffer.writeInt(character.getPoint().getX()); // x
			buffer.writeInt(character.getPoint().getY()); // y
			buffer.writeInt(character.getPoint().getZ()); // z

			buffer.writeDouble(character.getHP()); // hp cur
			buffer.writeDouble(character.getMP()); // mp cur

			buffer.writeInt(character.getSP()); // sp
			buffer.writeLong(character.getExperience()); // exp
			buffer.writeInt(character.getLevel()); // level

			buffer.writeInt(character.getKarma()); // karma
			buffer.writeInt(character.getPkKills()); // pk
			buffer.writeInt(character.getPvpKills()); // pvp

			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);

			writePaperdollItemID(buffer, character, HAIR1);
			writePaperdollItemID(buffer, character, RIGHT_EAR);
			writePaperdollItemID(buffer, character, LEFT_EAR);
			writePaperdollItemID(buffer, character, NECK);
			writePaperdollItemID(buffer, character, RIGHT_FINGER);
			writePaperdollItemID(buffer, character, LEFT_FINGER);
			writePaperdollItemID(buffer, character, HEAD);
			writePaperdollItemID(buffer, character, RIGHT_HAND);
			writePaperdollItemID(buffer, character, LEFT_HAND);
			writePaperdollItemID(buffer, character, GLOVES);
			writePaperdollItemID(buffer, character, CHEST);
			writePaperdollItemID(buffer, character, LEGS);
			writePaperdollItemID(buffer, character, FEET);
			writePaperdollItemID(buffer, character, CLOAK);
			writePaperdollItemID(buffer, character, RIGHT_HAND);
			writePaperdollItemID(buffer, character, HAIR1);
			writePaperdollItemID(buffer, character, HAIR2);

			if (conn.supports(ProtocolVersion.FREYA)) {
				writePaperdollItemID(buffer, character, RIGHT_BRACELET);
				writePaperdollItemID(buffer, character, LEFT_BRACELET);
				writePaperdollItemID(buffer, character, DECORATION_1);
				writePaperdollItemID(buffer, character, DECORATION_2);
				writePaperdollItemID(buffer, character, DECORATION_3);
				writePaperdollItemID(buffer, character, DECORATION_4);
				writePaperdollItemID(buffer, character, DECORATION_5);
				writePaperdollItemID(buffer, character, DECORATION_6);
				writePaperdollItemID(buffer, character, BELT);
			}

			if (conn.supports(ProtocolVersion.RELEASE,
					ProtocolVersion.INTERLUDE)) {
				// duplicated to "fill the packet space".
				writePaperdollItemID(buffer, character, HAIR1);
				writePaperdollItemID(buffer, character, RIGHT_EAR);
				writePaperdollItemID(buffer, character, LEFT_EAR);
				writePaperdollItemID(buffer, character, NECK);
				writePaperdollItemID(buffer, character, RIGHT_FINGER);
				writePaperdollItemID(buffer, character, LEFT_FINGER);
				writePaperdollItemID(buffer, character, HEAD);
				writePaperdollItemID(buffer, character, RIGHT_HAND);
				writePaperdollItemID(buffer, character, LEFT_HAND);
				writePaperdollItemID(buffer, character, GLOVES);
				writePaperdollItemID(buffer, character, CHEST);
				writePaperdollItemID(buffer, character, LEGS);
				writePaperdollItemID(buffer, character, FEET);
				writePaperdollItemID(buffer, character, CLOAK);
				writePaperdollItemID(buffer, character, RIGHT_HAND);
				writePaperdollItemID(buffer, character, HAIR1);
				writePaperdollItemID(buffer, character, HAIR2);
			}

			// hair style
			buffer.writeInt(character.getAppearance().getHairStyle().option);
			// hair color
			buffer.writeInt(character.getAppearance().getHairColor().option);
			// face
			buffer.writeInt(character.getAppearance().getFace().option);
			// max hp
			buffer.writeDouble(character.getStats().getMaxHP());
			// max mp
			buffer.writeDouble(character.getStats().getMaxMP());

			// time left before character is deleted (in seconds?) TODO
			buffer.writeInt(0x00);

			buffer.writeInt(character.getCharacterClass().id);

			// 0x01 auto selects this character. TODO
			buffer.writeInt(0x00);
			// enchant effect TODO
			buffer.writeByte(0x16);
			// augmentation id
			buffer.writeInt(0x00);

			if (conn.supports(ProtocolVersion.FREYA)) {
				// Currently on retail when you are on character select you
				// don't
				// see your transformation.
				buffer.writeInt(0x00);

				// Freya by Vistall:
				// npdid - 16024 Tame Tiny Baby Kookaburra
				buffer.writeInt(16024); // A9E89C
				buffer.writeInt(0); // level
				buffer.writeInt(0); // ?
				buffer.writeInt(0); // food? - 1200
				buffer.writeDouble(0); // max Hp
				buffer.writeDouble(0); // cur Hp
			}
		}
	}

	/**
	 * Writes the paperdoll item id
	 * 
	 * @param buffer
	 *            the buffer
	 * @param character
	 *            the character
	 * @param paperdoll
	 *            the slot
	 */
	private void writePaperdollItemID(ChannelBuffer buffer,
			L2Character character, InventoryPaperdoll paperdoll) {
		final Item item = character.getInventory().getItem(paperdoll);
		int id = 0;
		if (item != null)
			id = item.getTemplateID().getID();
		buffer.writeInt(id);
	}
}
