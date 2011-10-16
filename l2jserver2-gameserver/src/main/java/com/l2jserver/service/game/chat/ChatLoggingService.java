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

import org.slf4j.Logger;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.Service;

/**
 * This service logs each message sent in the server. There can be several
 * implementations that stores logs in different locations (or don't store at
 * all!) however only a single implementation can be active at any given time.
 * <p>
 * This service is called by contract in {@link ChatChannel} implementations.
 * The <b>log</b> method creates a new {@link ChatMessage} object, stores it
 * (optional) and returns the object. The same object will be sent to all
 * {@link ChatChannelListener} and will contain: message text, sender and date.
 * Other optional fields might also be available.
 * <p>
 * <b>{@link ChatChannelFilter} will be called before logging can occur. If any
 * filter refuses the message, it will NOT be logged.</b>
 * <p>
 * This service, however, does not need to log the message using {@link Logger}
 * object, because this is already done by {@link ChatService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ChatLoggingService extends Service {
	/**
	 * Sends a message to a public chat channel.
	 * 
	 * @param sender
	 *            the sender
	 * @param channel
	 *            the chat channel
	 * @param message
	 *            the message
	 * @return the new ChatMessage created
	 */
	ChatMessage log(CharacterID sender, ChatChannel channel, String message);
}
