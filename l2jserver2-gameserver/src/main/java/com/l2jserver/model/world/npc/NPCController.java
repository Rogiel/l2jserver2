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
package com.l2jserver.model.world.npc;

import com.l2jserver.game.net.SystemMessage;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.util.exception.L2Exception;

/**
 * The {@link NPC} controller is used to control any given NPC. Implementations
 * will add behaviors to each NPC.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface NPCController {
	/**
	 * Performs an interaction with this {@link NPC}.
	 * 
	 * @param npc
	 *            the {@link NPC} instance
	 * @param character
	 *            the interacting character
	 * @param args
	 *            the action arguments
	 * @throws NPCControllerException
	 *             if the exception requires an system message response
	 * @throws L2Exception
	 *             any {@link L2Exception}
	 */
	void action(NPC npc, L2Character character, String... args)
			throws NPCControllerException, L2Exception;

	/**
	 * Exception thrown if the {@link NPCController} could not perform an
	 * certain operation
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public static class NPCControllerException extends L2Exception {
		/**
		 * Serialization ID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * The {@link SystemMessage}
		 */
		private final SystemMessage systemMessage;

		/**
		 * Creates a new instance
		 * 
		 * @param systemMessage
		 *            the {@link SystemMessage} to be sent to the client
		 */
		public NPCControllerException(SystemMessage systemMessage) {
			this.systemMessage = systemMessage;
		}

		/**
		 * Creates a new instance with a <code>null</code> system message.
		 */
		public NPCControllerException() {
			this(null);
		}

		/**
		 * @return the system message
		 */
		public SystemMessage getSystemMessage() {
			return systemMessage;
		}
	}
}
