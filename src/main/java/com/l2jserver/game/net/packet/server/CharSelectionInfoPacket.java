package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.BufferUtil;

public class CharSelectionInfoPacket extends AbstractServerPacket {
	public static final int OPCODE = 0x09;

	private final String loginName;
	private final int sessionId;
	private int lastCharacterId;
	private final L2Character[] characters;

	public CharSelectionInfoPacket(String loginName, int sessionId,
			int lastCharacterId, L2Character... characters) {
		super(OPCODE);
		this.loginName = loginName;
		this.sessionId = sessionId;
		this.lastCharacterId = lastCharacterId;
		this.characters = characters;
	}

	@Override
	public void write(ChannelBuffer buffer) {
		// buffer.writeByte(0x09);
		buffer.writeInt(characters.length);

		// Can prevent players from creating new characters (if 0); (if 1,
		// the client will ask if chars may be created (0x13) Response: (0x0D) )
		buffer.writeInt(0x07);
		buffer.writeByte(0x00);

		long lastAccess = 0L;

		if (lastCharacterId == -1) {
			for (int i = 0; i < characters.length; i++) {
				if (characters[i].getLastAccess() == null)
					continue;
				if (lastAccess < characters[i].getLastAccess().getTime()) {
					lastAccess = characters[i].getLastAccess().getTime();
					lastCharacterId = i;
				}
			}
		}

		int i = 0;
		for (final L2Character character : characters) {
			// buffer.writeBytes(character.getName().getBytes());
			// buffer.writeByte(0x00); // NULL termination
			BufferUtil.writeString(buffer, character.getName());
			buffer.writeInt(character.getID().getID());
			BufferUtil.writeString(buffer, loginName);
			// buffer.writeBytes(loginName.getBytes());
			// buffer.writeByte(0x00); // NULL termination
			buffer.writeInt(sessionId);
			if (character.getClanID() == null) {
				buffer.writeInt(0x00); // clan id
			} else {
				buffer.writeInt(character.getClanID().getID()); // clan id
			}
			buffer.writeInt(0x00); // ??

			buffer.writeInt(0x00); // sex
			buffer.writeInt(0x00); // race

			// if (character.getClassId() == character.getBaseClassId())
			buffer.writeInt(0x00);
			// else
			// buffer.writeInt(character.getBaseClassId());

			buffer.writeInt(0x01); // active ??

			buffer.writeInt(-71338); // x
			buffer.writeInt(258271); // y
			buffer.writeInt(-3104); // z

			buffer.writeDouble(20); // hp cur
			buffer.writeDouble(20); // mp cur

			buffer.writeInt(3000); // sp
			buffer.writeLong(0); // exp
			buffer.writeInt(0x01); // level

			buffer.writeInt(0x00); // karma
			buffer.writeInt(0x00); // pk

			buffer.writeInt(0x00); // pvp
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);
			buffer.writeInt(0x00);

			for (int id = 0; id < 26; id++) {
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

			buffer.writeInt(0x01); // hair style
			buffer.writeInt(0x01); // hair color
			buffer.writeInt(0x01); // face

			buffer.writeDouble(30); // hp max
			buffer.writeDouble(30); // mp max

			long deleteTime = 0;
			int deletedays = 0;
			if (deleteTime > 0)
				deletedays = (int) ((deleteTime - System.currentTimeMillis()) / 1000);
			buffer.writeInt(deletedays); // days left before
			// delete .. if != 0
			// then char is inactive
			buffer.writeInt(0x00); // class
			buffer.writeInt(0x01); // c3 auto-select char

			buffer.writeByte(0x00); // enchant effect

			buffer.writeInt(0x00); // augmentation id

			// buffer.writeInt(charInfoPackage.getTransformId()); // Used to
			// display Transformations
			buffer.writeInt(0x00); // Currently on retail when you are on
			// character select you don't see your transformation.

			// Freya by Vistall:
			buffer.writeInt(0); // npdid - 16024 Tame Tiny Baby Kookaburra
								// A9E89C
			buffer.writeInt(0); // level
			buffer.writeInt(0); // ?
			buffer.writeInt(0); // food? - 1200
			buffer.writeDouble(0); // max Hp
			buffer.writeDouble(0); // cur Hp

			i++;
		}
	}
}
