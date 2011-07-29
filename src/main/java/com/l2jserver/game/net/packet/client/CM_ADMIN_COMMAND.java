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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.service.game.admin.AdministratorService;
import com.l2jserver.util.BufferUtils;

/**
 * Executes an administrator action
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CM_ADMIN_COMMAND extends AbstractClientPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x74;

	/**
	 * The admin service
	 */
	private final AdministratorService adminService;

	/**
	 * The command
	 */
	@SuppressWarnings("unused")
	private String command;

	@Inject
	public CM_ADMIN_COMMAND(AdministratorService adminService) {
		this.adminService = adminService;
	}

	@Override
	public void read(Lineage2Client conn, ChannelBuffer buffer) {
		command = BufferUtils.readString(buffer).trim();
	}

	@Override
	public void process(final Lineage2Client conn) {
		// final StringTokenizer tokenizer = new StringTokenizer(command, " ");
		// final String cmd = tokenizer.nextToken();
		// if (cmd.equals("tele")) {
		// Coordinate coord = Coordinate.fromXYZ(
		// Integer.parseInt(tokenizer.nextToken()),
		// Integer.parseInt(tokenizer.nextToken()),
		// Integer.parseInt(tokenizer.nextToken()));
		// try {
		// spawnService.teleport(conn.getCharacter(), coord);
		// } catch (NotSpawnedServiceException e) {
		// conn.sendActionFailed();
		// } catch (CharacterAlreadyTeleportingServiceException e) {
		// conn.sendActionFailed();
		// }
		// }
		adminService.command(conn, conn.getCharacter(), "", new String[] {});

		// TODO implement admin commands
	}
}
