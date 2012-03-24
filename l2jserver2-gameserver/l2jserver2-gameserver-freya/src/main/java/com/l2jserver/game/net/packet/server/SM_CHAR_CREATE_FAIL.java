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
package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.AbstractServerPacket;

/**
 * An packet informing that the character could not be created.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see Reason
 */
public class SM_CHAR_CREATE_FAIL extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x10;

	/**
	 * The character creation failure reason
	 */
	private Reason reason;

	/**
	 * The character creation error reason
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum Reason {
		/**
		 * "Your character creation has failed."
		 */
		REASON_CREATION_FAILED(0x00),
		/**
		 * "You cannot create another character. Please delete the existing
		 * character and try again." Removes all settings that were selected
		 * (race, class, etc)"
		 */
		REASON_TOO_MANY_CHARACTERS(0x01),
		/**
		 * "This name already exists."
		 */
		REASON_NAME_ALREADY_EXISTS(0x02),
		/**
		 * "Your title cannot exceed 16 characters in length. Please try again."
		 */
		REASON_16_ENG_CHARS(0x03),
		/**
		 * "Incorrect name. Please try again."
		 */
		REASON_INCORRECT_NAME(0x04),
		/**
		 * "Characters cannot be created from this server."
		 */
		REASON_CREATE_NOT_ALLOWED(0x05),
		/**
		 * "Unable to create character. You are unable to create a new character
		 * on the selected server. A restriction is in place which restricts
		 * users from creating characters on different servers where no previous
		 * character exists. Please choose another server."
		 */
		REASON_CHOOSE_ANOTHER_SVR(0x06);

		/**
		 * The error code id
		 */
		public final int id;

		/**
		 * @param id
		 *            the reason id
		 */
		Reason(int id) {
			this.id = id;
		}

		/**
		 * @return an {@link SM_CHAR_CREATE_FAIL} instance for this enum
		 *         constant
		 */
		public SM_CHAR_CREATE_FAIL newInstance() {
			return new SM_CHAR_CREATE_FAIL(this);
		}
	}

	/**
	 * @param reason
	 *            the reason
	 */
	public SM_CHAR_CREATE_FAIL(Reason reason) {
		super(OPCODE);
		this.reason = reason;
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeInt(reason.id);
	}
}
