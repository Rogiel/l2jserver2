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
package com.l2jserver.service.game.chat;

public enum ChatMessageDestination {
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
	 * @
	 */
	CLAN(4), GM(5), PETITION_PLAYER(6), PETITION_GM(7),
	/**
	 * +
	 */
	TRADE(8),
	/**
	 * $
	 */
	ALLIANCE(9), ANNOUNCEMENT(10), BOAT(11), L2FRIEND(12), MSNCHAT(13), PARTYMATCH_ROOM(
			14), PARTYROOM_COMMANDER(15), PARTYROOM_ALL(16), HERO_VOICE(17), CRITICAL_ANNOUNCE(
			18), SCREEN_ANNOUNCE(19), BATTLEFIELD(20), MPCC_ROOM(21);

	public final int id;

	ChatMessageDestination(int id) {
		this.id = id;
	}

	public static ChatMessageDestination fromID(int id) {
		for (final ChatMessageDestination dest : values()) {
			if (dest.id == id)
				return dest;
		}
		return null;
	}
}