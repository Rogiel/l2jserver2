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
package com.l2jserver.service.game.admin;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This service handles administrators in the server
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface AdministratorService extends Service {
	/**
	 * Executes an command
	 * 
	 * @param conn
	 *            the lineage 2 connection
	 * @param character
	 *            the admin character
	 * @param command
	 *            the command
	 * @param args
	 *            the arguments
	 */
	void command(Lineage2Client conn, L2Character character,
			String command, String... args);

	/**
	 * The base interface for Administrator commands
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface AdministratorCommand {
		void administrator(L2Character character, String... args);
	}
}
