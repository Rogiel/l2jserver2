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
package com.l2jserver;

import com.l2jserver.routines.GameServerInitializationRoutine;

public class L2JGameServerMain {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		final L2JGameServer server = new L2JGameServer();
		try {
			server.getInjector()
					.getInstance(GameServerInitializationRoutine.class).call();
		} catch (Exception e) {
			System.out.println("GameServer could not be started!");
			e.printStackTrace();
		}

		Thread.sleep(60 * 60 * 1000);
	}

}
