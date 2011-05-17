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
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;
import com.l2jserver.service.game.chat.channel.ChatChannel;
import com.l2jserver.service.game.chat.channel.PrivateChatChannel;
import com.l2jserver.service.game.chat.channel.PublicChatChannel;

/**
 * This service chatting in the server
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ChatService extends Service {
	/**
	 * @return the global {@link ChatChannel}
	 */
	PublicChatChannel getGlobalChannel();

	/**
	 * @param character
	 *            the character in the region
	 * @return the global {@link ChatChannel}
	 */
	PublicChatChannel getRegionChannel(L2Character character);

	/**
	 * Get an private {@link ChatChannel} to {@link CharacterID}
	 * 
	 * @param character
	 *            the target character
	 * @return the private {@link ChatChannel}
	 */
	PrivateChatChannel getChannel(CharacterID character);

	/**
	 * @param clan
	 *            the clan
	 * @return the public clan {@link ChatChannel}
	 */
	PublicChatChannel getChannel(ClanID clan);

	// TODO party chat
}
