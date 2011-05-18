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
package com.l2jserver.service.game;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.client.CharacterChatMessagePacket.MessageDestination;
import com.l2jserver.game.net.packet.server.ActorChatMessagePacket;
import com.l2jserver.game.net.packet.server.GameGuardQueryPacket;
import com.l2jserver.game.net.packet.server.InventoryPacket;
import com.l2jserver.game.net.packet.server.UserInformationPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.event.CharacterEnterWorldEvent;
import com.l2jserver.model.world.character.event.CharacterEvent;
import com.l2jserver.model.world.character.event.CharacterLeaveWorldEvent;
import com.l2jserver.model.world.character.event.CharacterListener;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.game.chat.channel.ChatChannel;
import com.l2jserver.service.game.chat.channel.ChatChannelListener;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;

/**
 * Default implementation for {@link CharacterService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class, ChatService.class, NetworkService.class })
public class CharacterServiceImpl extends AbstractService implements
		CharacterService {
	/**
	 * The {@link WorldService}
	 */
	private final WorldService worldService;
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The {@link ChatService}
	 */
	private final ChatService chatService;
	/**
	 * The {@link NetworkService}
	 */
	private final NetworkService networkService;
	/**
	 * The {@link SpawnService}
	 */
	private final SpawnService spawnService;

	@Inject
	public CharacterServiceImpl(WorldService worldService,
			WorldEventDispatcher eventDispatcher, ChatService chatService,
			NetworkService networkService, SpawnService spawnService) {
		this.worldService = worldService;
		this.eventDispatcher = eventDispatcher;
		this.chatService = chatService;
		this.networkService = networkService;
		this.spawnService = spawnService;
	}

	@Override
	public void enterWorld(final L2Character character) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		if (conn == null)
			return;
		if (!worldService.add(character))
			// character is already in the world!
			return;

		// chat listener
		final ChatChannelListener globalChatListener = new ChatChannelListener() {
			@Override
			public void onMessage(ChatChannel channel, CharacterID source,
					String message) {
				conn.write(new ActorChatMessagePacket(source.getObject(),
						MessageDestination.ALL, message));
			}
		};

		// leave world event
		eventDispatcher.addListener(id, new CharacterListener() {
			@Override
			protected boolean dispatch(CharacterEvent e) {
				if (!(e instanceof CharacterLeaveWorldEvent))
					return true;

				// remove chat listeners
				chatService.getGlobalChannel().removeChatChannelListener(
						globalChatListener);

				return false;
			}
		});

		// register global chat listener
		chatService.getGlobalChannel().addChatChannelListener(
				globalChatListener);

		// send this user information
		conn.write(new UserInformationPacket(character));
		// TODO game guard enforcing
		conn.write(new GameGuardQueryPacket());
		conn.write(new InventoryPacket(character.getInventory()));

		// dispatch enter world event
		eventDispatcher.dispatch(new CharacterEnterWorldEvent(character));

		// spawn the player -- this will also dispatch a spawn event
		spawnService.spawn(character);
	}

	@Override
	public void leaveWorld(L2Character character) {
		if (!worldService.remove(character))
			return;
		eventDispatcher.dispatch(new CharacterLeaveWorldEvent(character));
	}
}
