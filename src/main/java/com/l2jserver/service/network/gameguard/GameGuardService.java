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
package com.l2jserver.service.network.gameguard;

import java.util.concurrent.Future;

import com.l2jserver.game.net.Lineage2Connection;

/**
 * This service is responsible for querying and validating GameGuard packets
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface GameGuardService {
	/**
	 * Queries the client GameGuard for an response
	 * 
	 * @param conn
	 *            the lineage 2 connection
	 * @return an future that will be used to obtain validation status
	 */
	Future<GameGuardResponse> query(Lineage2Connection conn);

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
	GameGuardResponse key(Lineage2Connection conn, byte[] key);
}
