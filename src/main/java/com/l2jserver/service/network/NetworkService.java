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

import java.net.InetSocketAddress;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.Lineage2Session;
import com.l2jserver.game.net.packet.ClientPacket;
import com.l2jserver.game.net.packet.ServerPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;
import com.l2jserver.service.ServiceConfiguration;
import com.l2jserver.service.configuration.Configuration;
import com.l2jserver.service.configuration.Configuration.ConfigurationName;

/**
 * The network service is responsible for communicating the server with the game
 * client. The service can have several different implementations, however only
 * a single one can be active at any given time.
 * <p>
 * This service is implementation of the Lineage II protocol and will do the
 * following:
 * 
 * <ul>
 * <li>Listen in the network port (default is 7777 for game server);</li>
 * <li>Process incoming connections and filter them for blocked IPs (not yet
 * implemented);</li>
 * <li>Handshake with the client and enable Cryptography;</li>
 * <li>Read incoming packets, decrypt and parse them into a {@link ClientPacket}
 * ;</li>
 * <li>Write outgoing packets ServerPacket and encrypt them;</li>
 * <li>(optional) Validate GameGuard responses (see GameGuardService);</li>
 * </ul>
 * 
 * Each connection is represented by {@link Lineage2Client} and will be attached
 * with {@link CharacterID} of the active character (if any),
 * {@link Lineage2Session} with authorization keys from LoginServer and the raw
 * connection socket (see implementations for more details).
 * <p>
 * It is also important to note that each {@link ClientPacket} have its own
 * instruction set hard coded in it and they can be injected with any service
 * using Guice framework.
 * <p>
 * The service can also be used to resolve {@link CharacterID} or
 * {@link L2Character} to a {@link Lineage2Client} object in order to establish
 * connection between the client connection and a game character.
 * <p>
 * Packet opcode resolver is implementation specific.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface NetworkService extends Service {
	/**
	 * The network {@link Configuration}
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@ConfigurationName("network")
	public interface NetworkConfiguration extends ServiceConfiguration {
		/**
		 * Get the server listen address
		 * 
		 * @return the listen address
		 */
		@ConfigurationPropertyGetter(name = "listen", defaultValue = "0.0.0.0:7777")
		InetSocketAddress getListenAddress();

		/**
		 * Set the server listen address
		 * 
		 * @param addr
		 *            the listen address
		 */
		@ConfigurationPropertySetter(name = "listen")
		void setListenAddress(InetSocketAddress addr);
	}

	/**
	 * Registers a new client
	 * 
	 * @param client
	 *            the client
	 */
	void register(Lineage2Client client);

	/**
	 * Unregisters a client
	 * 
	 * @param client
	 *            the client
	 */
	void unregister(Lineage2Client client);

	/**
	 * Discover the client using <tt>character</tt>
	 * 
	 * @param character
	 *            the character
	 * @return the found connection
	 */
	Lineage2Client discover(CharacterID character);

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
