package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.packet.AbstractServerPacket;

public class CharSelectionInfoPacket extends AbstractServerPacket {
	public static final int OPCODE = 0x09;

	private final String loginName;
	private final int sessionId;
	private final int activeId;
	private final Character[] characters;

	public CharSelectionInfoPacket(int opcode, String loginName, int sessionId,
			int activeId, Character... characters) {
		super(opcode);
		this.loginName = loginName;
		this.sessionId = sessionId;
		this.activeId = activeId;
		this.characters = characters;
	}

	@Override
	public void write(ChannelBuffer buffer) {
		// buffer.writeByte(0x09);
		// int size = (characters.length);
		// buffer.writeInt(size);
		//
		// // Can prevent players from creating new characters (if 0); (if 1,
		// the client will ask if chars may be created (0x13) Response: (0x0D) )
		// buffer.writeInt(0x07);
		// buffer.writeByte(0x00);
		//
		// long lastAccess = 0L;
		//
		// // if (activeId == -1) {
		// // for (int i = 0; i < size; i++) {
		// // if (lastAccess < characters[i].getLastAccess()) {
		// // lastAccess = characters[i].getLastAccess();
		// // _activeId = i;
		// // }
		// // }
		// // }
		//
		// for (int i = 0; i < size; i++)
		// {
		// Character character = characters[i];
		//
		// buffer.writeBytes(character.getName());
		// buffer.writeInt(character.getCharId());
		// writeS(_loginName);
		// buffer.writeInt(_sessionId);
		// buffer.writeInt(character.getClanId());
		// buffer.writeInt(0x00); // ??
		//
		// buffer.writeInt(character.getSex());
		// buffer.writeInt(character.getRace());
		//
		// if (character.getClassId() == character.getBaseClassId())
		// buffer.writeInt(character.getClassId());
		// else
		// buffer.writeInt(character.getBaseClassId());
		//
		// buffer.writeInt(0x01); // active ??
		//
		// buffer.writeInt(character.getX()); // x
		// buffer.writeInt(character.getY()); // y
		// buffer.writeInt(character.getZ()); // z
		//
		// buffer.writeDouble(character.getCurrentHp()); // hp cur
		// buffer.writeDouble(character.getCurrentMp()); // mp cur
		//
		// buffer.writeInt(character.getSp());
		// writeQ(character.getExp());
		// buffer.writeInt(character.getLevel());
		//
		// buffer.writeInt(character.getKarma()); // karma
		// buffer.writeInt(character.getPkKills());
		//
		// buffer.writeInt(character.getPvPKills());
		// buffer.writeInt(0x00);
		// buffer.writeInt(0x00);
		// buffer.writeInt(0x00);
		// buffer.writeInt(0x00);
		// buffer.writeInt(0x00);
		// buffer.writeInt(0x00);
		// buffer.writeInt(0x00);
		//
		//
		// for(int id = 0; id <27; id++) {
		// buffer.writeInt(0x00);
		// }
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_REAR));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LEAR));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_NECK));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RFINGER));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LFINGER));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HEAD));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LHAND));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_GLOVES));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_CHEST));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LEGS));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_FEET));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_CLOAK));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RHAND));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HAIR));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_HAIR2));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_RBRACELET));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_LBRACELET));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO1));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO2));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO3));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO4));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO5));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_DECO6));
		// //
		// buffer.writeInt(charInfoPackage.getPaperdollItemId(Inventory.PAPERDOLL_BELT));
		//
		// buffer.writeInt(character.getHairStyle());
		// buffer.writeInt(character.getHairColor());
		// buffer.writeInt(character.getFace());
		//
		// buffer.writeDouble(character.getMaxHp()); // hp max
		// buffer.writeDouble(character.getMaxMp()); // mp max
		//
		// long deleteTime = character.getDeleteTimer();
		// int deletedays = 0;
		// if (deleteTime > 0)
		// deletedays = (int)((deleteTime-System.currentTimeMillis())/1000);
		// buffer.writeInt(deletedays); // days left before
		// // delete .. if != 0
		// // then char is inactive
		// buffer.writeInt(character.getClassId());
		// if (i == _activeId)
		// buffer.writeInt(0x01);
		// else
		// buffer.writeInt(0x00); //c3 auto-select char
		//
		// buffer.writeByte(character.getEnchantEffect() > 127 ? 127 :
		// character.getEnchantEffect());
		//
		// buffer.writeInt(character.getAugmentationId());
		//
		// //buffer.writeInt(charInfoPackage.getTransformId()); // Used to
		// display Transformations
		// buffer.writeInt(0x00); // Currently on retail when you are on
		// character select you don't see your transformation.
		//
		// // Freya by Vistall:
		// buffer.writeInt(0); // npdid - 16024 Tame Tiny Baby Kookaburra A9E89C
		// buffer.writeInt(0); // level
		// buffer.writeInt(0); // ?
		// buffer.writeInt(0); // food? - 1200
		// buffer.writeDouble(0); // max Hp
		// buffer.writeDouble(0); // cur Hp
	}
}
