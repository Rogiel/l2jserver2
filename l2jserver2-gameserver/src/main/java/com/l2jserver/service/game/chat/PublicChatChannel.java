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

import com.l2jserver.model.server.ChatMessage;

/**
 * An public {@link ChatChannel}. Please note that the concept of "public" does
 * not mean it is available to anyone, but there are more than 2 player chatting
 * (i.e. clan, global, region, etc...). That mean that a single message can be
 * broadcasted to more than a single client. Note that even in a public channel
 * only a single event
 * {@link ChatChannelListener#onMessage(ChatChannel, ChatMessage)} will be
 * dispatched per listener.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface PublicChatChannel extends ChatChannel {
}
