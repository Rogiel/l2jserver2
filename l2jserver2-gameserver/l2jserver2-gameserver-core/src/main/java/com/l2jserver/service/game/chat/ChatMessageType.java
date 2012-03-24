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
package com.l2jserver.service.game.chat;

/**
 * Enumeration of all possible message types
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ChatMessageType {
	/**
	 * Everyone
	 */
	ALL(0),
	/**
	 * !
	 */
	SHOUT(1),
	/**
	 * Private message
	 */
	TELL(2),
	/**
	 * #
	 */
	PARTY(3),
	/**
	 * \@
	 */
	CLAN(4),
	/**
	 * The GM chat
	 */
	GM(5),
	/**
	 * Petition system: player messages
	 */
	PETITION_PLAYER(6),
	/**
	 * Petition system: GM messages
	 */
	PETITION_GM(7),
	/**
	 * +
	 */
	TRADE(8),
	/**
	 * $
	 */
	ALLIANCE(9),
	/**
	 * Announcement
	 */
	ANNOUNCEMENT(10),
	/**
	 * Boat
	 */
	BOAT(11),
	/**
	 * Message sent to an Lineage II friend
	 */
	L2FRIEND(12),
	/**
	 * MSN Chat log
	 */
	MSNCHAT(13),
	/**
	 * PartyMatching room
	 */
	PARTYMATCH_ROOM(14),
	/**
	 * The party room command
	 */
	PARTYROOM_COMMANDER(15),
	/**
	 * Party room all
	 */
	PARTYROOM_ALL(16),
	/**
	 * Hero chat
	 */
	HERO_VOICE(17),
	/**
	 * Critical announcement
	 */
	CRITICAL_ANNOUNCE(18),
	/**
	 * Screen announce
	 */
	SCREEN_ANNOUNCE(19),
	/**
	 * Battlefield
	 */
	BATTLEFIELD(20),
	/**
	 * UNKNOWN
	 */
	MPCC_ROOM(21);

	/**
	 * The numeric id representing this message type
	 */
	public final int id;

	/**
	 * @param id
	 *            the numeric id for this message type
	 */
	ChatMessageType(int id) {
		this.id = id;
	}

	/**
	 * Resolves the numeric ID to {@link ChatMessageType} java type.
	 * 
	 * @param id
	 *            the numeric id
	 * @return the {@link ChatMessageType} resolved. Can be <tt>null</tt>.
	 */
	public static ChatMessageType fromID(int id) {
		for (final ChatMessageType dest : values()) {
			if (dest.id == id)
				return dest;
		}
		return null;
	}
}