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
package com.l2jserver.game.net.packet.server;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.Lineage2CryptographyKey;
import com.l2jserver.game.net.packet.AbstractServerPacket;

/**
 * This packet send the encryptation keys for the client. After this message all
 * communication is done with the cryptography engine enabled.
 * 
 * <pre>
 * (c) cbddcd
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_KEY extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x2e;

	/**
	 * 8-byte key cryptography key
	 */
	private byte[] key;
	/**
	 * The protocol state. True if valid, false if not.
	 */
	private boolean status;

	public SM_KEY(Lineage2CryptographyKey key, boolean status) {
		super(OPCODE);
		this.key = Arrays.copyOfRange(key.key, 0, 8);
		this.status = status;
	}

	/**
	 * Creates a new {@link SM_KEY} with <tt>key</tt> and valid protocol.
	 * 
	 * @param key
	 *            the key
	 * @return the new instance
	 */
	public static SM_KEY valid(Lineage2CryptographyKey key) {
		return new SM_KEY(key, true);
	}

	/**
	 * Creates a new {@link SM_KEY} with <tt>key</tt> and invalid protocol.
	 * 
	 * @param key
	 *            the key
	 * @return the new instance
	 */
	public static SM_KEY invalid(Lineage2CryptographyKey key) {
		return new SM_KEY(key, false);
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeByte((status ? 0x01 : 0x00));
		for (int i = 0; i < 8; i++) {
			buffer.writeByte(key[i]);
		}
		// buffer.writeBytes(key);
		buffer.writeInt(0x01);
		buffer.writeInt(0x01); // server id
		buffer.writeByte(0x01);
		buffer.writeInt(0x00); // obfuscation key
	}

	/**
	 * @return the key
	 */
	public byte[] getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(byte[] key) {
		this.key = key;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
