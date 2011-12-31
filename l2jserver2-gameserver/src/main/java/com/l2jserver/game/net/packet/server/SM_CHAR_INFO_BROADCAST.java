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
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEFT_HAND;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.LEGS;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_BRACELET;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.RIGHT_HAND;
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.UNDERWEAR;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterMoveType;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.util.BufferUtils;

/**
 * This packet sends to the client an actor information about an actor
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_CHAR_INFO_BROADCAST extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x31;

	/**
	 * The character
	 */
	private final L2Character character;

	/**
	 * @param character
	 *            the character
	 */
	public SM_CHAR_INFO_BROADCAST(L2Character character) {
		super(OPCODE);
		this.character = character;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(character.getPoint().getX());
		buffer.writeInt(character.getPoint().getY());
		buffer.writeInt(character.getPoint().getZ());
		buffer.writeInt(0x00); // unk
		buffer.writeInt(character.getID().getID());
		BufferUtils.writeString(buffer, character.getName());
		buffer.writeInt(character.getRace().id);
		buffer.writeInt(character.getSex().option);

		buffer.writeInt(character.getCharacterClass().id);

		writePaperdollItemID(buffer, character, UNDERWEAR);
		// writePaperdollItemID(buffer, character, RIGHT_EAR);
		// writePaperdollItemID(buffer, character, LEFT_EAR);
		// writePaperdollItemID(buffer, character, NECK);
		// writePaperdollItemID(buffer, character, RIGHT_FINGER);
		// writePaperdollItemID(buffer, character, LEFT_FINGER);
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

		writePaperdollAugumentID(buffer, character, UNDERWEAR);
		// writePaperdollAugumentID(buffer, character, RIGHT_EAR);
		// writePaperdollAugumentID(buffer, character, LEFT_EAR);
		// writePaperdollAugumentID(buffer, character, NECK);
		// writePaperdollAugumentID(buffer, character, RIGHT_FINGER);
		// writePaperdollAugumentID(buffer, character, LEFT_FINGER);
		writePaperdollAugumentID(buffer, character, HEAD);
		writePaperdollAugumentID(buffer, character, RIGHT_HAND);
		writePaperdollAugumentID(buffer, character, LEFT_HAND);
		writePaperdollAugumentID(buffer, character, GLOVES);
		writePaperdollAugumentID(buffer, character, CHEST);
		writePaperdollAugumentID(buffer, character, LEGS);
		writePaperdollAugumentID(buffer, character, FEET);
		writePaperdollAugumentID(buffer, character, CLOAK);
		writePaperdollAugumentID(buffer, character, RIGHT_HAND);
		writePaperdollAugumentID(buffer, character, HAIR1);
		writePaperdollAugumentID(buffer, character, HAIR2);
		writePaperdollAugumentID(buffer, character, RIGHT_BRACELET);
		writePaperdollAugumentID(buffer, character, LEFT_BRACELET);
		writePaperdollAugumentID(buffer, character, DECORATION_1);
		writePaperdollAugumentID(buffer, character, DECORATION_2);
		writePaperdollAugumentID(buffer, character, DECORATION_3);
		writePaperdollAugumentID(buffer, character, DECORATION_4);
		writePaperdollAugumentID(buffer, character, DECORATION_5);
		writePaperdollAugumentID(buffer, character, DECORATION_6);
		writePaperdollAugumentID(buffer, character, BELT);

		buffer.writeInt(0x00); // unk
		buffer.writeInt(0x01); // unk
		// end of t1 new h's

		buffer.writeInt(0x00); // pvp flag
		buffer.writeInt(character.getKarma()); // karma

		buffer.writeInt(character.getStats().getMagicalAttackSpeed());
		buffer.writeInt(character.getStats().getPhysicalAttackSpeed());

		buffer.writeInt(0x00); // unk

		// FIXME half of those are walk speed
		buffer.writeInt(character.getStats().getRunSpeed());
		buffer.writeInt(character.getStats().getRunSpeed());
		buffer.writeInt(character.getStats().getRunSpeed());
		buffer.writeInt(character.getStats().getRunSpeed());
		buffer.writeInt(character.getStats().getRunSpeed());
		buffer.writeInt(character.getStats().getRunSpeed());
		buffer.writeInt(character.getStats().getRunSpeed());
		buffer.writeInt(character.getStats().getRunSpeed());

		buffer.writeDouble(0x01); // move speed multiplier
		buffer.writeDouble(0x01); // attack speed multiplier

		if (character.getSex() == ActorSex.MALE) {
			buffer.writeDouble(character.getTemplate().getCollision().getMale()
					.getRadius());
			buffer.writeDouble(character.getTemplate().getCollision().getMale()
					.getHeigth());
		} else {
			buffer.writeDouble(character.getTemplate().getCollision()
					.getFemale().getRadius());
			buffer.writeDouble(character.getTemplate().getCollision()
					.getFemale().getHeigth());
		}

		buffer.writeInt(character.getAppearance().getHairStyle().option);
		buffer.writeInt(character.getAppearance().getHairColor().option);
		buffer.writeInt(character.getAppearance().getFace().option);

		BufferUtils.writeString(buffer, character.getTitle());

		// dont send those 4 if using cursed weapon
		buffer.writeInt(0); // clan id
		buffer.writeInt(0); // crest id
		buffer.writeInt(0); // ally id
		buffer.writeInt(0); // ally crest id

		buffer.writeByte(0x01); // sitting
		buffer.writeByte((character.getMoveType() == CharacterMoveType.RUN ? 0x01
				: 0x00));
		buffer.writeByte((character.isAttacking() ? 0x01 : 0x00)); // is in
																	// combat

		buffer.writeByte(0x00); // alike dead

		buffer.writeByte((character.getAppearance().isVisible() ? 0x00 : 0x01));

		// 1-on Strider, 2-on Wyvern,
		// 3-on Great Wolf, 0-no mount
		buffer.writeByte(0x00);
		buffer.writeByte(0x00); // 1 - sellshop

		// writeH(_activeChar.getCubics().size());
		// for (int id : _activeChar.getCubics().keySet())
		// writeH(id);
		buffer.writeShort(0x00); // cubics size

		buffer.writeByte(0x00); // in party match room
		buffer.writeInt(0x00); // abnormal
		buffer.writeByte(0x00); // flying mounted

		// recom have
		buffer.writeShort(0x00); // Blue value for name (0 =
									// white, 255 = pure blue)
		buffer.writeInt(1000000); // mount npc
		buffer.writeInt(character.getCharacterClass().id);
		buffer.writeInt(0x00); // ?
		buffer.writeByte(0x00); // enchant effect

		buffer.writeByte(0x00); // team circle around feet 1= Blue, 2 = red

		buffer.writeInt(0x00); // clan crest large id
		buffer.writeByte(0x00); // is noble - Symbol on char menu
								// ctrl+I
		buffer.writeByte(0x00); // Hero Aura

		// (Cant be undone by setting back to 0)
		buffer.writeByte(0x00); // 0x01: Fishing Mode
		buffer.writeInt(0x00); // fish x
		buffer.writeInt(0x00);// fish y
		buffer.writeInt(0x00); // fish z

		buffer.writeInt(character.getAppearance().getNameColor().toInteger());

		buffer.writeInt((int) character.getPoint().getAngle());

		buffer.writeInt(0x00); // pledge class
		buffer.writeInt(0x00); // pledge type

		buffer.writeInt(character.getAppearance().getTitleColor().toInteger());

		buffer.writeInt(0x00); // cursed weapon id
		buffer.writeInt(0x00); // clan reputation

		// T1
		buffer.writeInt(0x00); // transformation id
		buffer.writeInt(0x00); // agathion id

		// T2
		buffer.writeInt(0x01); // unk

		// T2.3
		buffer.writeInt(0x00); // special effect
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

	/**
	 * Writes the paperdoll augument id
	 * 
	 * @param buffer
	 *            the buffer
	 * @param character
	 *            the character
	 * @param paperdoll
	 *            the slot
	 */
	private void writePaperdollAugumentID(ChannelBuffer buffer,
			L2Character character, InventoryPaperdoll paperdoll) {
		buffer.writeInt(0x00);
	}
}
