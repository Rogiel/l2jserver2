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
package com.l2jserver.service.network;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.ServerPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.service.Service;

/**
 * The network service is responsible for communicating the server and the game
 * client. You can see more details in each implementation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface NetworkService extends Service {
	/**
	 * Registers a new client
	 * 
	 * @param client
	 *            the client
	 */
	void register(Lineage2Connection client);

	/**
	 * Unregisters a client
	 * 
	 * @param client
	 *            the client
	 */
	void unregister(Lineage2Connection client);

	/**
	 * Discover the client using <tt>character</tt>
	 * 
	 * @param character
	 *            the character
	 * @return the found connection
	 */
	Lineage2Connection discover(CharacterID character);

	/**
	 * Broadcast an given <tt>packet</tt> to all clients connected
	 * 
	 * @param packet
	 *            the packet
	 */
	void broadcast(ServerPacket packet);

	/**
	 * Searches for idle connection and removes them
	 */
	void cleanup();
}
