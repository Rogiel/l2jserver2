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
package com.l2jserver.game.net.packet.server;

import static com.l2jserver.game.ProtocolVersion.FREYA;
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

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.Lineage2Session;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.util.BufferUtils;

/**
 * The list of characters sent to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterSelectionListPacket extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x09;

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

	public CharacterSelectionListPacket(String loginName, int sessionId,
			int lastCharacterId, L2Character... characters) {
		super(OPCODE);
		this.loginName = loginName;
		this.sessionId = sessionId;
		// this.lastCharacterId = lastCharacterId;
		this.characters = characters;
	}

	public static CharacterSelectionListPacket fromL2Session(
			Lineage2Session session, L2Character... characters) {
		return new CharacterSelectionListPacket(session.getAccountID().getID(),
				session.getPlayKey2(), -1, characters);
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		// buffer.writeByte(0x09);
		buffer.writeInt(characters.length);

		// Can prevent players from creating new characters (if 0);
		// if 1 the client will ask if chars may be created
		// (RequestCharacterTemplatesPacket) Response: (CharacterTemplatePacket)
		buffer.writeInt(7); // max chars
		buffer.writeByte(0x00);

		// int i = 0;
		for (final L2Character character : characters) {
			BufferUtils.writeString(buffer, character.getName());
			buffer.writeInt(character.getID().getID());
			BufferUtils.writeString(buffer, loginName);
			buffer.writeInt(sessionId);
			buffer.writeInt((character.getClanID() != null ? character
					.getClanID().getID() : 0x00)); // clan id
			buffer.writeInt(0x00); // ??

			buffer.writeInt(character.getSex().option); // sex
			buffer.writeInt(character.getRace().id); // race

			// if (character.getClassId() == character.getBaseClassId())
			buffer.writeInt(character.getCharacterClass().id); // base class id
																// or class id
			// else
			// buffer.writeInt(character.getBaseClassId());

			buffer.writeInt(1); // active ??

			buffer.writeInt(character.getPoint().getX()); // x
			buffer.writeInt(character.getPoint().getY()); // y
			buffer.writeInt(character.getPoint().getZ()); // z

			buffer.writeDouble(20); // hp cur
			buffer.writeDouble(20); // mp cur

			buffer.writeInt(320); // sp
			buffer.writeLong(4640); // exp
			buffer.writeInt(5); // level

			buffer.writeInt(0x00); // karma
			buffer.writeInt(0x00); // pk
			buffer.writeInt(0x00); // pvp

			for (int n = 0; n < 7; n++) {
				buffer.writeInt(0x00); // unk
			}
			// buffer.writeInt(0x00); // unk 1
			// buffer.writeInt(0x00); // unk 2
			// buffer.writeInt(0x00); // unk 3
			// buffer.writeInt(0x00); // unk 4
			// buffer.writeInt(0x00); // unk 5
			// buffer.writeInt(0x00); // unk 6
			// buffer.writeInt(0x00); // unk 7

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
			writePaperdollItemID(buffer, character, RIGHT_BRACELET);
			writePaperdollItemID(buffer, character, LEFT_BRACELET);
			writePaperdollItemID(buffer, character, DECORATION_1);
			writePaperdollItemID(buffer, character, DECORATION_2);
			writePaperdollItemID(buffer, character, DECORATION_3);
			writePaperdollItemID(buffer, character, DECORATION_4);
			writePaperdollItemID(buffer, character, DECORATION_5);
			writePaperdollItemID(buffer, character, DECORATION_6);
			writePaperdollItemID(buffer, character, BELT);

			// hair style
			buffer.writeInt(character.getAppearance().getHairStyle().option);
			// hair color
			buffer.writeInt(character.getAppearance().getHairColor().option);
			// face
			buffer.writeInt(character.getAppearance().getFace().option);

			buffer.writeDouble(30); // hp max
			buffer.writeDouble(30); // mp max

			buffer.writeInt(0x0); // seconds left before delete
			buffer.writeInt(character.getCharacterClass().id); // class
			buffer.writeInt(1); // c3 auto-select char

			buffer.writeByte(0x00); // enchant effect

			buffer.writeInt(0x00); // augmentation id

			// Currently on retail when you are on character select you don't
			// see your transformation.
			buffer.writeInt(0x00);

			// Freya by Vistall:
			if (conn.supports(FREYA)) {
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

	private void writePaperdollItemID(ChannelBuffer buffer,
			L2Character character, InventoryPaperdoll paperdoll) {
		final Item item = character.getInventory().getItem(paperdoll);
		int id = 0;
		if (item != null)
			id = item.getTemplateID().getID();
		buffer.writeInt(id);
	}
}
