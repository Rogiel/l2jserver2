package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractServerPacket;

/**
 * An packet informing that the character could not be created.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @see Reason
 */
public class CharacterCreateFailPacket extends AbstractServerPacket {
	public static final int OPCODE = 0x10;

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

		public final int id;

		private Reason(int id) {
			this.id = id;
		}
	}

	public CharacterCreateFailPacket(Reason reason) {
		super(OPCODE);
		this.reason = reason;
	}

	@Override
	public void write(Lineage2Connection conn, ChannelBuffer buffer) {
		buffer.writeInt(reason.id);
	}
}
