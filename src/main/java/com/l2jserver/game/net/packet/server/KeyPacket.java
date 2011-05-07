package com.l2jserver.game.net.packet.server;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.packet.AbstractServerPacket;

/**
 * This packet send the encryptation keys for the client. After this message all
 * communication is done with the cryptography engine enabled.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class KeyPacket extends AbstractServerPacket {
	/**
	 * Message OPCODE
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

	public KeyPacket(byte[] key, boolean status) {
		super(OPCODE);
		this.key = Arrays.copyOfRange(key, 0, 8);
		this.status = status;
	}

	/**
	 * Creates a new {@link KeyPacket} with <tt>key</tt> and valid protocol.
	 * 
	 * @param key
	 *            the key
	 * @return the new instance
	 */
	public static KeyPacket valid(byte[] key) {
		return new KeyPacket(key, true);
	}

	/**
	 * Creates a new {@link KeyPacket} with <tt>key</tt> and invalid protocol.
	 * 
	 * @param key
	 *            the key
	 * @return the new instance
	 */
	public static KeyPacket invalid(byte[] key) {
		return new KeyPacket(key, false);
	}

	@Override
	public void write(ChannelBuffer buffer) {
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
