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
package com.l2jserver.service.network.broadcast;

import com.l2jserver.model.template.NPCTemplate.Droplist.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.Service;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEvent;
import com.l2jserver.service.game.world.event.WorldEventDispatcherServiceImpl;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.service.network.model.packet.ServerPacket;

/**
 * This service hooks to the {@link WorldEventDispatcherServiceImpl} from
 * {@link WorldService} and captures world events near the character requesting
 * broadcast messages. It will capture server-side world events and convert them
 * into an network packet and send them though {@link NetworkService}.
 * <p>
 * It can broadcast several types of {@link WorldObject} types, including, but
 * not restricted to:
 * 
 * <ul>
 * <li> {@link L2Character} - user playable character</li>
 * <li> {@link NPC} - not playable characters and monsters</li>
 * <li> {@link Item} - dropped items</li>
 * </ul>
 * 
 * This service main purpose is to keep server modularity. No other service
 * should be aware of network packets. Services, instead of generating an
 * {@link ServerPacket packet}, generate an {@link WorldEvent packet}, which
 * gets converted into an {@link ServerPacket packet} by this service.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface BroadcastService extends Service {
	/**
	 * Broadcast all nearby objects to the given <tt>client</tt>
	 * 
	 * @param conn
	 *            the character
	 */
	void broadcast(L2Character conn);
}
