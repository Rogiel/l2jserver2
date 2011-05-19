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

import java.util.Map;
import java.util.Set;

import com.google.inject.Inject;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.game.chat.channel.ChatChannel;
import com.l2jserver.service.game.chat.channel.ChatChannelListener;
import com.l2jserver.service.game.chat.channel.PrivateChatChannel;
import com.l2jserver.service.game.chat.channel.PublicChatChannel;
import com.l2jserver.service.game.region.Region;
import com.l2jserver.service.game.region.RegionService;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Default {@link ChatService} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
// @Depends(RegionService.class)
public class SimpleChatService extends AbstractService implements ChatService {
	private final RegionService regionService;

	/**
	 * The global chat channel
	 */
	private GlobalChatChannelImpl global;
	/**
	 * The list of private chat channels
	 */
	// TODO remove private chats from disconnected characters. Maybe plugging it
	// in the NetworkService?
	private Map<CharacterID, PrivateChatChannelImpl> privateChannels;
	/**
	 * The list of clan chat channels
	 */
	private Map<ClanID, ClanChatChannelImpl> clanChannels;
	/**
	 * The list of regional chat channels
	 */
	private Map<Region, RegionChatChannelImpl> regionChannels;

	/**
	 * Creates a new instance
	 * 
	 * @param regionService
	 *            the region service
	 */
	@Inject
	public SimpleChatService() {
		// this.regionService = regionService;
		this.regionService = null;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		this.global = new GlobalChatChannelImpl();
		this.privateChannels = CollectionFactory.newMap();
		this.clanChannels = CollectionFactory.newMap();
		this.regionChannels = CollectionFactory.newMap();
	}

	@Override
	public PublicChatChannel getGlobalChannel() {
		return global;
	}

	@Override
	public PublicChatChannel getRegionChannel(L2Character character) {
		final Region region = regionService.getRegion(character);
		RegionChatChannelImpl channel = regionChannels.get(region);
		if (channel == null) {
			channel = new RegionChatChannelImpl(region);
			regionChannels.put(region, channel);
		}
		return channel;
	}

	@Override
	public PrivateChatChannel getChannel(CharacterID character) {
		PrivateChatChannelImpl channel = privateChannels.get(character);
		if (channel == null) {
			channel = new PrivateChatChannelImpl(character);
			privateChannels.put(character, channel);
		}
		return channel;
	}

	@Override
	public PublicChatChannel getChannel(ClanID clan) {
		ClanChatChannelImpl channel = clanChannels.get(clan);
		if (channel == null) {
			channel = new ClanChatChannelImpl(clan);
			clanChannels.put(clan, channel);
		}
		return channel;
	}

	@Override
	protected void doStop() throws ServiceStopException {
		this.global = null;
		this.privateChannels = null;
		this.clanChannels = null;
		this.regionChannels = null;
	}

	/**
	 * {@link ChatChannel} abstract implementation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private abstract class ChatChannelImpl implements ChatChannel {
		/**
		 * The list of all listeners on this channel
		 */
		protected final Set<ChatChannelListener> listeners = CollectionFactory
				.newSet();

		@Override
		public void send(CharacterID sender, String message) {
			for (final ChatChannelListener listener : listeners) {
				listener.onMessage(this, sender, message);
			}
		}

		@Override
		public void addChatChannelListener(ChatChannelListener listener) {
			listeners.add(listener);
		}

		@Override
		public void removeChatChannelListener(ChatChannelListener listener) {
			listeners.remove(listener);
		}
	}

	/**
	 * {@link PrivateChatChannel} implementation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class PrivateChatChannelImpl extends ChatChannelImpl implements
			PrivateChatChannel {
		private final CharacterID character;

		public PrivateChatChannelImpl(CharacterID character) {
			this.character = character;
		}

		@Override
		public CharacterID getTarget() {
			return character;
		}
	}

	/**
	 * Global {@link PublicChatChannel} implemenetation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class GlobalChatChannelImpl extends ChatChannelImpl implements
			PublicChatChannel {
	}

	/**
	 * {@link PublicChatChannel} implemenetation for {@link Clan clans}
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class ClanChatChannelImpl extends ChatChannelImpl implements
			PublicChatChannel {
		/**
		 * The clan ID
		 */
		@SuppressWarnings("unused")
		private final ClanID clanID;

		/**
		 * Creates a new instance
		 * 
		 * @param clanID
		 */
		public ClanChatChannelImpl(ClanID clanID) {
			this.clanID = clanID;
		}
	}

	/**
	 * {@link PublicChatChannel} implemenetation for {@link Region regions}
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class RegionChatChannelImpl extends ChatChannelImpl implements
			PublicChatChannel {
		/**
		 * The clan ID
		 */
		@SuppressWarnings("unused")
		private final Region region;

		/**
		 * Creates a new instance
		 * 
		 * @param clanID
		 */
		public RegionChatChannelImpl(Region region) {
			this.region = region;
		}
	}
}
