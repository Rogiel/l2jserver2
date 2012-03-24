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
package com.l2jserver.service.network.gameguard;

import java.util.concurrent.Future;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;
import com.l2jserver.service.network.model.Lineage2Client;

/**
 * This service is responsible for querying and validating GameGuard packets
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface GameGuardService extends Service {
	/**
	 * Queries the client GameGuard for an response
	 * 
	 * @param character
	 *            the lineage 2 character
	 * @return an future that will be used to obtain validation status
	 */
	Future<GameGuardResponse> query(L2Character character);

	/**
	 * The Game guard key state
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum GameGuardResponse {
		/**
		 * Key is valid
		 */
		VALID,
		/**
		 * Key is not valid
		 */
		INVALID;
	}

	/**
	 * Sets the game guard key for the given connection. Future will be notified
	 * of the key state (valid or invalid).
	 * 
	 * @param conn
	 *            the connection
	 * @param key
	 *            the key
	 * @return the validation state
	 */
	GameGuardResponse key(Lineage2Client conn, byte[] key);
}
