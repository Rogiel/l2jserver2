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

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.SystemMessage;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.game.Fort;
import com.l2jserver.model.game.Skill;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.util.BufferUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This packet sends an System Message to the client. Most messages appear in
 * the console.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SystemMessagePacket extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x62;

	/**
	 * The System message id
	 */
	private int id;
	/**
	 * The system message parameters
	 */
	private List<SystemMessagePacketParameter> params = CollectionFactory
			.newList();

	public interface SystemMessagePacketParameter {
		public static final byte TYPE_SYSTEM_STRING = 13;
		public static final byte TYPE_PLAYER_NAME = 12;
		// id 11 - unknown
		public static final byte TYPE_INSTANCE_NAME = 10;
		public static final byte TYPE_ELEMENT_NAME = 9;
		// id 8 - same as 3
		public static final byte TYPE_ZONE_NAME = 7;
		public static final byte TYPE_ITEM_NUMBER = 6;
		public static final byte TYPE_CASTLE_NAME = 5;
		public static final byte TYPE_SKILL_NAME = 4;
		public static final byte TYPE_ITEM_NAME = 3;
		public static final byte TYPE_NPC_NAME = 2;
		public static final byte TYPE_NUMBER = 1;
		public static final byte TYPE_TEXT = 0;

		void write(Lineage2Connection conn, ChannelBuffer buffer);
	}

	/**
	 * Creates a new instance
	 */
	public SystemMessagePacket(SystemMessage message) {
		super(OPCODE);
		this.id = message.id;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(id);
		buffer.writeInt(params.size());
		for (final SystemMessagePacketParameter param : params) {
			param.write(conn, buffer);
		}
	}

	public final SystemMessagePacket addString(final String text) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_TEXT);
				BufferUtils.writeString(buffer, text);
			}
		});
		return this;
	}

	/**
	 * Castlename-e.dat<br>
	 * 0-9 Castle names<br>
	 * 21-64 CH names<br>
	 * 81-89 Territory names<br>
	 * 101-121 Fortress names<br>
	 * 
	 * @param number
	 * @return
	 */
	public final SystemMessagePacket addFort(final Fort fort) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_CASTLE_NAME);
				buffer.writeInt(fort.getID().getID());
			}
		});
		return this;
	}

	public final SystemMessagePacket addNumber(final int number) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_NUMBER);
				buffer.writeInt(number);
			}
		});
		return this;
	}

	public final SystemMessagePacket addItemCount(final long number) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_ITEM_NUMBER);
				buffer.writeLong(number);
			}
		});
		return this;
	}

	public final SystemMessagePacket addActorName(final Actor actor) {
		// params.add(new SystemMessagePacketParameter() {
		// @Override
		// public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		// // buffer.writeInt(TYPE_TEXT);
		// // buffer.writeInt(number);
		// // TODO
		// }
		// });
		return this;
	}

	public final SystemMessagePacket addItem(final ItemTemplate item) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_ITEM_NAME);
				buffer.writeInt(item.getID().getID());
			}
		});
		return this;
	}

	public final SystemMessagePacket addItem(final Item item) {
		return addItem(item.getTemplateID().getTemplate());
	}

	public final SystemMessagePacket addZoneName(final int x, final int y,
			final int z) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_ZONE_NAME);
				buffer.writeInt(x);
				buffer.writeInt(y);
				buffer.writeInt(z);
			}
		});
		return this;
	}

	public final SystemMessagePacket addSkill(final SkillTemplate skill,
			final int level) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_SKILL_NAME);
				buffer.writeInt(skill.getID().getID());
				buffer.writeInt(level);
			}
		});
		return this;
	}

	public final SystemMessagePacket addSkill(final Skill skill) {
		return addSkill(skill.getSkillTemplate(), skill.getLevel());
	}

	/**
	 * Elemental name - 0(Fire) ...
	 * 
	 * @param type
	 * @return
	 */
	public final SystemMessagePacket addElemntal(final int type) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_ELEMENT_NAME);
				buffer.writeInt(type);
			}
		});
		return this;
	}

	/**
	 * ID from sysstring-e.dat
	 * 
	 * @param type
	 * @return
	 */
	public final SystemMessagePacket addSystemString(final int type) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_SYSTEM_STRING);
				buffer.writeInt(type);
			}
		});
		return this;
	}

	/**
	 * Instance name from instantzonedata-e.dat
	 * 
	 * @param type
	 *            id of instance
	 * @return
	 */
	public final SystemMessagePacket addInstanceName(final int type) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Connection conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_INSTANCE_NAME);
				buffer.writeInt(type);
			}
		});
		return this;
	}
}
