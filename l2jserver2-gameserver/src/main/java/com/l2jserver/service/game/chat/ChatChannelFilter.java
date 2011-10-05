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

import com.l2jserver.model.id.object.CharacterID;

/**
 * This is a filter that can filter messages sent into an given
 * {@link ChatChannel}. The filter is applied in a individual basis per each
 * message. Note that the filter is called <b>before</b> the message is logged
 * or sent to listeners.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ChatChannelFilter {
	/**
	 * Filter the message
	 * 
	 * @param sender
	 *            the sender
	 * @param channel
	 *            the chat channel
	 * @param message
	 *            the message
	 * @return true if message was accepted, false otherwise
	 */
	boolean filter(CharacterID sender, ChatChannel channel, String message);
}
