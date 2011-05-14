package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.ProtocolVersion;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.Lineage2Session;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.ActorExperience;
import com.l2jserver.util.BufferUtils;

/**
 * The list of characters sent to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterSelectionListPacket extends AbstractServerPacket {
	public static final int OPCODE = 0x09;

	private final String loginName;
	private final int sessionId;
	// private int lastCharacterId;
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
		return new CharacterSelectionListPacket(session.getUsername(),
				session.getPlayKey2(), -1, characters);
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		// buffer.writeByte(0x09);
		buffer.writeInt(characters.length);

		// Can prevent players from creating new characters (if 0);
		// if 1 the client will ask if chars may be created
		// (RequestCharacterTemplatesPacket) Response: (CharacterTemplatePacket)
		buffer.writeInt(0x07); // max chars
		buffer.writeByte(0x00);

		// int i = 0;
		for (final L2Character character : characters) {
			BufferUtils.writeString(buffer, character.getName());
			buffer.writeInt(character.getID().getID());
			BufferUtils.writeString(buffer, loginName);
			buffer.writeInt(sessionId);
			// if (character.getClanID() == null) {
			buffer.writeInt(0x00); // clan id
			// } else {
			// buffer.writeInt(character.getClanID().getID()); // clan id
			// }
			buffer.writeInt(0x00); // ??

			buffer.writeInt(0x01); // sex
			buffer.writeInt(character.getRace().option); // race

			// if (character.getClassId() == character.getBaseClassId())
			buffer.writeInt(character.getCharacterClass().id); // base class id
																// or class id
			// else
			// buffer.writeInt(character.getBaseClassId());

			buffer.writeInt(0x01); // active ??

			buffer.writeInt(-71338); // x
			buffer.writeInt(258271); // y
			buffer.writeInt(-3104); // z

			buffer.writeDouble(20); // hp cur
			buffer.writeDouble(20); // mp cur

			buffer.writeInt(0x00); // sp
			buffer.writeLong(ActorExperience.LEVEL_1.experience); // exp
			buffer.writeInt(ActorExperience.LEVEL_1.level); // level

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

			for (int id = 0; id < 25; id++) {
				buffer.writeInt(0x00); // paperdolls
			}
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_REAR));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LEAR));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_NECK));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_FEET));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_CLOAK));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HAIR2));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RBRACELET));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LBRACELET));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO1));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO2));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO3));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO4));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO5));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO6));
			// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_BELT));

			// hair style
			// buffer.writeInt(character.getAppearance().getHairStyle().option);
			buffer.writeInt(0x02);
			// hair color
			// buffer.writeInt(character.getAppearance().getHairColor().option);
			buffer.writeInt(0x03);
			// face
			// buffer.writeInt(character.getAppearance().getFace().option);
			buffer.writeInt(0x00);

			buffer.writeDouble(30); // hp max
			buffer.writeDouble(30); // mp max

			buffer.writeInt(0x0); // seconds left before delete
			buffer.writeInt(character.getCharacterClass().id); // class
			buffer.writeInt(0x01); // c3 auto-select char

			buffer.writeByte(0x00); // enchant effect

			buffer.writeInt(0x00); // augmentation id

			// Currently on retail when you are on character select you don't
			// see your transformation.
			buffer.writeInt(0x00);

			// Freya by Vistall:
			if (conn.supports(ProtocolVersion.FREYA)) {
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
}
