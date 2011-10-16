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
package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.l2jserver.game.net.Lineage2CryptographyKey;

/**
 * Encrypts Lineage II packets
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2Encrypter extends OneToOneEncoder {
	/**
	 * The handler name
	 */
	public static final String HANDLER_NAME = "crypto.encoder";

	/**
	 * Enabled state
	 */
	private boolean enabled = false;
	/**
	 * Crypto key
	 */
	private Lineage2CryptographyKey key;

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer))
			return msg;
		if (!enabled)
			return msg;
		final ChannelBuffer buffer = (ChannelBuffer) msg;

		final int offset = buffer.readerIndex() + 2; // skip header
		final int size = buffer.readableBytes() - 2;
		int temp = 0, temp2 = 0;
		synchronized (key) {
			for (int i = 0; i < size; i++) {
				temp2 = buffer.getByte(offset + i) & 0xFF;
				temp = temp2 ^ key.get(i) ^ temp;
				buffer.setByte(offset + i, (byte) temp);
			}
			key.update(size);
		}

		return msg;
	}

	/**
	 * Enables this encrypter with the given <tt>key</tt>
	 * 
	 * @param key
	 *            the key
	 */
	public void enable(Lineage2CryptographyKey key) {
		this.setKey(key);
		this.setEnabled(true);
	}

	/**
	 * Set this decrypter key. The key can only be set once.
	 * 
	 * @param key
	 *            the key
	 */
	public void setKey(Lineage2CryptographyKey key) {
		if (this.key != null)
			throw new IllegalStateException("Key is already set");
		this.key = key;
	}

	/**
	 * @return true if this encrypter is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the state of this encrypter
	 * 
	 * @param enabled
	 *            the new state
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
