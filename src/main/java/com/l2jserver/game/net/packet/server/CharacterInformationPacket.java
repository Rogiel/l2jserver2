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
import static com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll.UNDERWEAR;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.ActorExperience;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.util.BufferUtils;

/**
 * This is an message informing the client of an given player
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterInformationPacket extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x32;

	/**
	 * The character
	 */
	private L2Character character;

	public CharacterInformationPacket(L2Character character) {
		super(OPCODE);
		this.character = character;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(character.getPoint().getX());
		buffer.writeInt(character.getPoint().getY());
		buffer.writeInt(character.getPoint().getZ());
		buffer.writeInt(0); // vehicle
		buffer.writeInt(character.getID().getID());
		BufferUtils.writeString(buffer, character.getName());
		buffer.writeInt(character.getRace().id);
		buffer.writeInt(character.getSex().option);

		buffer.writeInt(character.getCharacterClass().id);

		buffer.writeInt(character.getLevel());
		buffer.writeLong(ActorExperience.LEVEL_1.experience);
		buffer.writeInt(character.getAttributes().getStrength());
		buffer.writeInt(character.getAttributes().getDexterity());
		buffer.writeInt(character.getAttributes().getConcentration());
		buffer.writeInt(character.getAttributes().getIntelligence());
		buffer.writeInt(character.getAttributes().getWitness());
		buffer.writeInt(character.getAttributes().getMentality());
		buffer.writeInt(200); // max hp
		buffer.writeInt(character.getHP()); // cur hp
		buffer.writeInt(200); // max mp
		buffer.writeInt((int) 200); // cur mp
		buffer.writeInt(0); // sp
		buffer.writeInt(0); // load
		buffer.writeInt(character.getAttributes().getMaxWeigth()); // max load

		// 20 no weapon, 40 weapon equippe
		buffer.writeInt(20);

		writePaperdollObjectID(buffer, character, UNDERWEAR);
		writePaperdollObjectID(buffer, character, RIGHT_EAR);
		writePaperdollObjectID(buffer, character, LEFT_EAR);
		writePaperdollObjectID(buffer, character, NECK);
		writePaperdollObjectID(buffer, character, RIGHT_FINGER);
		writePaperdollObjectID(buffer, character, LEFT_FINGER);
		writePaperdollObjectID(buffer, character, HEAD);
		writePaperdollObjectID(buffer, character, RIGHT_HAND);
		writePaperdollObjectID(buffer, character, LEFT_HAND);
		writePaperdollObjectID(buffer, character, GLOVES);
		writePaperdollObjectID(buffer, character, CHEST);
		writePaperdollObjectID(buffer, character, LEGS);
		writePaperdollObjectID(buffer, character, FEET);
		writePaperdollObjectID(buffer, character, CLOAK);
		writePaperdollObjectID(buffer, character, RIGHT_HAND);
		writePaperdollObjectID(buffer, character, HAIR1);
		writePaperdollObjectID(buffer, character, HAIR2);
		writePaperdollObjectID(buffer, character, RIGHT_BRACELET);
		writePaperdollObjectID(buffer, character, LEFT_BRACELET);
		writePaperdollObjectID(buffer, character, DECORATION_1);
		writePaperdollObjectID(buffer, character, DECORATION_2);
		writePaperdollObjectID(buffer, character, DECORATION_3);
		writePaperdollObjectID(buffer, character, DECORATION_4);
		writePaperdollObjectID(buffer, character, DECORATION_5);
		writePaperdollObjectID(buffer, character, DECORATION_6);
		writePaperdollObjectID(buffer, character, BELT);

		writePaperdollItemID(buffer, character, UNDERWEAR);
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

		writePaperdollAugumentID(buffer, character, UNDERWEAR);
		writePaperdollAugumentID(buffer, character, RIGHT_EAR);
		writePaperdollAugumentID(buffer, character, LEFT_EAR);
		writePaperdollAugumentID(buffer, character, NECK);
		writePaperdollAugumentID(buffer, character, RIGHT_FINGER);
		writePaperdollAugumentID(buffer, character, LEFT_FINGER);
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

		buffer.writeInt(0x00); // (max?) talismans count
		buffer.writeInt(0x00); // cloak sratus
		buffer.writeInt(character.getAttributes().getPhysicalAttack());
		buffer.writeInt(character.getAttributes().getAttackSpeed());
		buffer.writeInt(character.getAttributes().getPhysicalDefense());

		buffer.writeInt(character.getAttributes().getEvasionChance()); // evasion
		buffer.writeInt(character.getAttributes().getAccuracy());
		buffer.writeInt(character.getAttributes().getCriticalChance());

		buffer.writeInt(character.getAttributes().getMagicalAttack());
		buffer.writeInt(character.getAttributes().getCastSpeed());
		buffer.writeInt(character.getAttributes().getAttackSpeed());
		buffer.writeInt(character.getAttributes().getMagicalDefense());

		buffer.writeInt(0x00); // 0-non-pvp 1-pvp = violett name
		buffer.writeInt(0x00); // karma

		buffer.writeInt((int) character.getAttributes().getMoveSpeed()); // run
																			// speed
		buffer.writeInt((int) character.getAttributes().getMoveSpeed()); // walk
																			// speed
		buffer.writeInt((int) character.getAttributes().getMoveSpeed()); // swim
																			// run
																			// speed
		buffer.writeInt((int) character.getAttributes().getMoveSpeed()); // swim
																			// walk
																			// speed
		buffer.writeInt(0); // unk
		buffer.writeInt(0); // unk
		buffer.writeInt(0); // fly speed -only if flying
		buffer.writeInt(0); // fly speed -only if flying
		buffer.writeDouble(0x01); // move speed multiplier
		buffer.writeDouble(0x01); // attack speed multiplier

		// L2Summon pet = _activeChar.getPet();
		// L2Transformation trans;
		// if (_activeChar.getMountType() != 0 && pet != null) {
		// writeF(pet.getTemplate().fCollisionRadius);
		// writeF(pet.getTemplate().fCollisionHeight);
		// } else if ((trans = _activeChar.getTransformation()) != null) {
		// writeF(trans.getCollisionRadius());
		// writeF(trans.getCollisionHeight());
		// } else {
		// writeF(_activeChar.getCollisionRadius());
		// writeF(_activeChar.getCollisionHeight());
		// }
		buffer.writeDouble(9.0); // collision radius
		buffer.writeDouble(23.0); // collision heigth

		buffer.writeInt(character.getAppearance().getHairStyle().option);
		buffer.writeInt(character.getAppearance().getHairColor().option);
		buffer.writeInt(character.getAppearance().getFace().option);
		buffer.writeInt(0x00); // is gm

		String title = "Testing"; // title
		BufferUtils.writeString(buffer, title);

		int clanid = 0;
		if (character.getClanID() != null)
			clanid = character.getClanID().getID();
		buffer.writeInt(clanid); // clanid
		buffer.writeInt(0x00); // clan crest id
		buffer.writeInt(0x00); // ally id
		buffer.writeInt(0x00); // ally crest id
		// 0x40 leader rights
		// siege flags: attacker - 0x180 sword over name, defender - 0x80
		// shield, 0xC0 crown (|leader), 0x1C0 flag (|leader)
		buffer.writeInt(0x40);
		buffer.writeByte(0x00); // mount type
		buffer.writeByte(0x00); // private store type
		buffer.writeByte(0x00); // dwarven craft
		buffer.writeInt(0x00); // pk kills
		buffer.writeInt(0x00); // pvp kills

		buffer.writeShort(0x00); // cubics size
		// short:cubicsid[cubicssize]
		// buffer.writeShort(cubicid);

		buffer.writeByte(0); // is party match room

		buffer.writeInt(0x00); // abnormal effect
		buffer.writeByte(0x0); // flying mounted = 2; otherwise: 0

		buffer.writeInt(0x00); // clan privileges

		buffer.writeShort(2); // c2 recommendations remaining
		buffer.writeShort(1); // c2 recommendations received
		buffer.writeInt(0); // mount npc id
		buffer.writeShort(500); // inventory limit

		buffer.writeInt(character.getCharacterClass().id);
		buffer.writeInt(0x01); // special effects? circles around player...
		buffer.writeInt(200); // max cp
		buffer.writeInt(200); // cur cp
		buffer.writeByte(127); // is mount or is airshilhelp = 0; otherwise
								// enchant effect (minimum 127)

		buffer.writeByte(0x00);// team, 1=blue,2 red,0 is unknown

		buffer.writeInt(0x00); // clan crest large id
		// 0x01: symbol on char menu ctrl+I
		buffer.writeByte(0x00); // is noble
		buffer.writeByte(0x00); // 0x01: Hero Aura

		buffer.writeByte(0x00); // Fishing Mode
		buffer.writeInt(0x00); // fishing x
		buffer.writeInt(0x00); // fishing y
		buffer.writeInt(0x00); // fishing z
		buffer.writeInt(character.getAppearance().getNameColor().toInteger());

		// new c5
		// is running
		buffer.writeByte(0x00); // changes the Speed
								// display on Status
								// Window

		// pledge class
		buffer.writeInt(0x00); // changes the text above
								// CP on Status Window
		buffer.writeInt(0x00); // pledge type

		buffer.writeInt(character.getAppearance().getTitleColor().toInteger());

		// cursed weapon ID equipped
		buffer.writeInt(0x00);

		// T1 Starts
		buffer.writeInt(0x00); // transformation id

		buffer.writeShort(0x00); // attack element
		buffer.writeShort(0x10); // attack element value
		buffer.writeShort(0x10); // fire defense value
		buffer.writeShort(0x10); // water def value
		buffer.writeShort(0x10); // wind def value
		buffer.writeShort(0x10); // earth def value
		buffer.writeShort(0x10); // holy def value
		buffer.writeShort(0x10); // dark def value

		buffer.writeInt(0x00); // getAgathionId

		// T2 Starts
		buffer.writeInt(0x00); // Fame
		buffer.writeInt(0x01); // Minimap on Hellbound
		buffer.writeInt(1); // Vitality Points
		buffer.writeInt(0x00); // special effects
	}

	private void writePaperdollObjectID(ChannelBuffer buffer,
			L2Character character, InventoryPaperdoll paperdoll) {
		final Item item = character.getInventory().getItem(paperdoll);
		int id = 0;
		if (item != null)
			id = item.getID().getID();
		buffer.writeInt(id);
	}

	private void writePaperdollItemID(ChannelBuffer buffer,
			L2Character character, InventoryPaperdoll paperdoll) {
		final Item item = character.getInventory().getItem(paperdoll);
		int id = 0;
		if (item != null)
			id = item.getTemplateID().getID();
		buffer.writeInt(id);
	}

	private void writePaperdollAugumentID(ChannelBuffer buffer,
			L2Character character, InventoryPaperdoll paperdoll) {
		buffer.writeInt(0x00);
	}
}
