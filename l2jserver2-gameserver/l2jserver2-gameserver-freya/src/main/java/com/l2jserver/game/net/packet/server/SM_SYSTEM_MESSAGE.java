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

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.model.game.Castle;
import com.l2jserver.model.game.Fort;
import com.l2jserver.model.game.Skill;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.SystemMessage;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;
import com.l2jserver.util.BufferUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This packet sends an System Message to the client. Most messages appear in
 * the console.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_SYSTEM_MESSAGE extends AbstractServerPacket {
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

	/**
	 * System message parameter IDs
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface SystemMessagePacketParameter {
		/**
		 * String parameter
		 */
		public static final byte TYPE_SYSTEM_STRING = 13;
		/**
		 * Player name parameter
		 */
		public static final byte TYPE_PLAYER_NAME = 12;
		// id 11 - unknown
		/**
		 * Instance name parameter
		 */
		public static final byte TYPE_INSTANCE_NAME = 10;
		/**
		 * Element name parameter
		 */
		public static final byte TYPE_ELEMENT_NAME = 9;
		// id 8 - same as 3
		/**
		 * Zone name parameter
		 */
		public static final byte TYPE_ZONE_NAME = 7;
		/**
		 * {@link Item} number parameter
		 */
		public static final byte TYPE_ITEM_NUMBER = 6;
		/**
		 * {@link Castle} name parameter
		 */
		public static final byte TYPE_CASTLE_NAME = 5;
		/**
		 * {@link Skill} name parameter
		 */
		public static final byte TYPE_SKILL_NAME = 4;
		/**
		 * {@link Item} name parameter
		 */
		public static final byte TYPE_ITEM_NAME = 3;
		/**
		 * {@link NPC} name parameter
		 */
		public static final byte TYPE_NPC_NAME = 2;
		/**
		 * Number parameter
		 */
		public static final byte TYPE_NUMBER = 1;
		/**
		 * Text parameter
		 */
		public static final byte TYPE_TEXT = 0;

		/**
		 * @param conn
		 *            the connection
		 * @param buffer
		 *            the buffer
		 */
		void write(Lineage2Client conn, ChannelBuffer buffer);
	}

	/**
	 * Creates a new instance
	 * 
	 * @param message
	 *            the {@link SystemMessage}
	 */
	public SM_SYSTEM_MESSAGE(SystemMessage message) {
		super(OPCODE);
		this.id = message.id;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(id);
		buffer.writeInt(params.size());
		for (final SystemMessagePacketParameter param : params) {
			param.write(conn, buffer);
		}
	}

	/**
	 * Adds an string parameter
	 * 
	 * @param text
	 *            the text
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addString(final String text) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
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
	 * @param fort
	 *            the fort
	 * @return the {@link SM_SYSTEM_MESSAGE} instance
	 */
	public final SM_SYSTEM_MESSAGE addFort(final Fort fort) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_CASTLE_NAME);
				buffer.writeInt(fort.getID().getID());
			}
		});
		return this;
	}

	/**
	 * Adds an number parameter
	 * 
	 * @param number
	 *            the number
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addNumber(final int number) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_NUMBER);
				buffer.writeInt(number);
			}
		});
		return this;
	}

	/**
	 * Adds an item count parameter
	 * 
	 * @param number
	 *            the number
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addItemCount(final long number) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_ITEM_NUMBER);
				buffer.writeLong(number);
			}
		});
		return this;
	}

	/**
	 * Adds an the actor name
	 * 
	 * @param actor
	 *            the actor
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addActorName(final Actor actor) {
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

	/**
	 * Adds the item name
	 * 
	 * @param item
	 *            the item
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addItem(final ItemTemplate item) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_ITEM_NAME);
				buffer.writeInt(item.getID().getID());
			}
		});
		return this;
	}

	/**
	 * Adds the item name
	 * 
	 * @param item
	 *            the item
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addItem(final Item item) {
		return addItem(item.getTemplateID().getTemplate());
	}

	/**
	 * Adds the zone name
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * 
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addZoneName(final int x, final int y,
			final int z) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_ZONE_NAME);
				buffer.writeInt(x);
				buffer.writeInt(y);
				buffer.writeInt(z);
			}
		});
		return this;
	}

	/**
	 * @param skill
	 *            the skill template
	 * @param level
	 *            the skill level
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addSkill(final SkillTemplate skill,
			final int level) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_SKILL_NAME);
				buffer.writeInt(skill.getID().getID());
				buffer.writeInt(level);
			}
		});
		return this;
	}

	/**
	 * @param skill
	 *            the skill
	 * @return this instance
	 */
	public final SM_SYSTEM_MESSAGE addSkill(final Skill skill) {
		return addSkill(skill.getTemplate(), skill.getLevel());
	}

	/**
	 * Elemental name - 0(Fire) ...
	 * 
	 * @param type
	 *            the type
	 * @return the {@link SM_SYSTEM_MESSAGE} instance
	 */
	public final SM_SYSTEM_MESSAGE addElemntal(final int type) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
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
	 *            the type
	 * @return the {@link SM_SYSTEM_MESSAGE} instance
	 */
	public final SM_SYSTEM_MESSAGE addSystemString(final int type) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
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
	 * @return the {@link SM_SYSTEM_MESSAGE} instance
	 */
	public final SM_SYSTEM_MESSAGE addInstanceName(final int type) {
		params.add(new SystemMessagePacketParameter() {
			@Override
			public void write(Lineage2Client conn, ChannelBuffer buffer) {
				buffer.writeInt(TYPE_INSTANCE_NAME);
				buffer.writeInt(type);
			}
		});
		return this;
	}
}
