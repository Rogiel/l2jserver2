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

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.database.DataAccessObject;
import com.l2jserver.service.game.region.Region;
import com.l2jserver.service.game.region.RegionService;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Default {@link ChatService} implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends(ChatLoggingService.class)
public class SimpleChatService extends AbstractService implements ChatService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link ChatLoggingService} implementation
	 */
	private final ChatLoggingService chatLoggingService;
	/**
	 * The {@link RegionService}
	 */
	private final RegionService regionService;
	/**
	 * The {@link L2Character} DAO
	 */
	private final CharacterDAO charDao;

	/**
	 * The global {@link ChatChannel}
	 */
	private GlobalChatChannelImpl global;
	/**
	 * The trade {@link ChatChannel}
	 */
	private TradeChatChannelImpl trade;
	/**
	 * The announcement {@link ChatChannel}
	 */
	private AnnouncementChatChannelImpl announcement;
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
	 * @param chatLogService
	 *            the chat log service
	 * @param charDao
	 *            the character {@link DataAccessObject DAO}
	 */
	@Inject
	public SimpleChatService(ChatLoggingService chatLogService,
			CharacterDAO charDao) {
		this.chatLoggingService = chatLogService;
		// this.regionService = regionService;
		this.regionService = null;
		this.charDao = charDao;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		this.global = new GlobalChatChannelImpl();
		this.trade = new TradeChatChannelImpl();
		this.announcement = new AnnouncementChatChannelImpl();
		this.privateChannels = CollectionFactory.newMap();
		this.clanChannels = CollectionFactory.newMap();
		this.regionChannels = CollectionFactory.newMap();
	}

	@Override
	public ChatMessage send(CharacterID sender, ChatMessageType chat,
			String message, String extra)
			throws TargetNotFoundChatServiceException,
			CannotChatToSelfChatServiceException,
			ChatBanActiveChatServiceException,
			ChatTargetOfflineServiceException {
		Preconditions.checkNotNull(sender, "sender");
		Preconditions.checkNotNull(message, "message");

		log.debug("Sending message {} from {} to {}", new Object[] { message,
				sender, chat });

		final ChatChannel channel;
		switch (chat) {
		case ALL:
			channel = getGlobalChannel();
			break;
		case TRADE:
			channel = getTradeChannel();
			break;
		case CLAN:
			channel = getChannel(sender.getObject().getClanID());
			break;
		case TELL:
			final L2Character character = charDao.selectByName(extra);
			if (character == null)
				throw new TargetNotFoundChatServiceException();
			if (character.getID().equals(sender))
				throw new CannotChatToSelfChatServiceException();
			channel = getChannel(character.getID());
			break;
		case ANNOUNCEMENT:
			channel = getAnnouncementChannel();
			break;
		default:
			return null;
		}
		return channel.send(sender, message);
	}

	@Override
	public PublicChatChannel getGlobalChannel() {
		return global;
	}

	@Override
	public PublicChatChannel getTradeChannel() {
		return trade;
	}

	@Override
	public PublicChatChannel getAnnouncementChannel() {
		return announcement;
	}

	@Override
	public PublicChatChannel getRegionChannel(L2Character character) {
		Preconditions.checkNotNull(character, "character");
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
		Preconditions.checkNotNull(character, "character");
		if (character == null)
			return null;
		PrivateChatChannelImpl channel = privateChannels.get(character);
		if (channel == null) {
			channel = new PrivateChatChannelImpl(character);
			privateChannels.put(character, channel);
		}
		return channel;
	}

	@Override
	public PublicChatChannel getChannel(ClanID clan) {
		Preconditions.checkNotNull(clan, "clan");
		if (clan == null)
			return null;
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
		/**
		 * The list of all filters on this channel
		 */
		protected final Set<ChatChannelFilter> filters = CollectionFactory
				.newSet();

		@Override
		public ChatMessage send(CharacterID sender, String textMessage) {
			Preconditions.checkNotNull(sender, "sender");
			Preconditions.checkNotNull(textMessage, "message");
			// TODO throw exception if sender is banned from chat

			// filter the message. if a single filter refuses it, the message
			// will be discarded
			for (final ChatChannelFilter filter : filters) {
				if (!filter.filter(sender, this, textMessage))
					// discard message
					log.debug("Message {} discarded by {}", textMessage, filter);
				return null;
			}

			// log this chat message
			ChatMessage message = chatLoggingService.log(sender, this,
					textMessage);

			log.debug("[{}]: {}", this, message);

			for (final ChatChannelListener listener : listeners) {
				listener.onMessage(this, message);
			}

			return message;
		}

		@Override
		public void addMessageListener(ChatChannelListener listener) {
			Preconditions.checkNotNull(listener, "listener");
			log.debug("Added {} to {}", listener, this);
			listeners.add(listener);
		}

		@Override
		public void removeMessageListener(ChatChannelListener listener) {
			Preconditions.checkNotNull(listener, "listener");
			log.debug("Removed {} to {}", listener, this);
			listeners.remove(listener);
		}

		@Override
		public void addMessageFilter(ChatChannelFilter filter) {
			Preconditions.checkNotNull(filter, "filter");
			log.debug("Added {} to {}", filter, this);
			filters.add(filter);
		}

		@Override
		public void removeMessageFilter(ChatChannelFilter filter) {
			Preconditions.checkNotNull(filter, "filter");
			log.debug("Removed {} to {}", filter, this);
			filters.remove(filter);
		}

		@Override
		public String getChannelName() {
			return getMessageType().name();
		}

		@Override
		public int getChannelID() {
			return getMessageType().id;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ChatChannelImpl [" + getChannelName() + "("
					+ getChannelID() + ")]";
		}
	}

	/**
	 * {@link PrivateChatChannel} implementation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class PrivateChatChannelImpl extends ChatChannelImpl implements
			PrivateChatChannel {
		/**
		 * The target character {@link ID}
		 */
		private final CharacterID character;

		/**
		 * @param character
		 *            the target character {@link ID}
		 */
		public PrivateChatChannelImpl(CharacterID character) {
			Preconditions.checkNotNull(character, "character");
			this.character = character;
		}

		@Override
		public CharacterID getDestination() {
			return character;
		}

		@Override
		public int getChannelID() {
			return character.getID();
		}

		@Override
		public ChatMessageType getMessageType() {
			return ChatMessageType.SHOUT;
		}

		@Override
		public void dispose() {
			privateChannels.remove(character);
		}
	}

	/**
	 * Global {@link PublicChatChannel} implementation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class GlobalChatChannelImpl extends ChatChannelImpl implements
			PublicChatChannel {
		@Override
		public ChatMessageType getMessageType() {
			return ChatMessageType.ALL;
		}

		@Override
		public void dispose() {
			throw new UnsupportedOperationException(
					"Cannot dispose the Global Chat Channel");
		}
	}

	/**
	 * Trade {@link PublicChatChannel} implementation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class TradeChatChannelImpl extends ChatChannelImpl implements
			PublicChatChannel {
		@Override
		public ChatMessageType getMessageType() {
			return ChatMessageType.TRADE;
		}

		@Override
		public void dispose() {
			throw new UnsupportedOperationException(
					"Cannot dispose the Trade Chat Channel");
		}
	}

	/**
	 * Announcement {@link PublicChatChannel} implementation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class AnnouncementChatChannelImpl extends ChatChannelImpl implements
			PublicChatChannel {
		@Override
		public ChatMessageType getMessageType() {
			return ChatMessageType.ANNOUNCEMENT;
		}

		@Override
		public void dispose() {
			throw new UnsupportedOperationException(
					"Cannot dispose the Announcement Chat Channel");
		}
	}

	/**
	 * {@link PublicChatChannel} implementation for {@link Clan clans}
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class ClanChatChannelImpl extends ChatChannelImpl implements
			PublicChatChannel {
		/**
		 * The clan ID
		 */
		private final ClanID clanID;

		/**
		 * Creates a new instance
		 * 
		 * @param clanID
		 *            the clan {@link ID} used in this channel
		 */
		public ClanChatChannelImpl(ClanID clanID) {
			Preconditions.checkNotNull(clanID, "clanID");
			this.clanID = clanID;
		}

		@Override
		public ChatMessageType getMessageType() {
			return ChatMessageType.CLAN;
		}

		@Override
		public void dispose() {
			clanChannels.remove(clanID);
		}
	}

	/**
	 * {@link PublicChatChannel} implementation for {@link Region regions}
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
		 * @param region
		 *            the region represented in this channel
		 */
		public RegionChatChannelImpl(Region region) {
			Preconditions.checkNotNull(region, "region");
			this.region = region;
		}

		@Override
		public ChatMessageType getMessageType() {
			return ChatMessageType.ALL;
		}

		@Override
		public void dispose() {
			throw new UnsupportedOperationException();
		}
	}
}
