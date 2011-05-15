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
package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.l2jserver.game.net.Lineage2CryptographyKey;

/**
 * Decrypts encrypted Lineage II packets
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2Decrypter extends OneToOneDecoder {
	/**
	 * The handler name
	 */
	public static final String HANDLER_NAME = "crypto.decoder";

	/**
	 * Enabled state
	 */
	private boolean enabled = false;
	/**
	 * Crypto key
	 */
	private Lineage2CryptographyKey key;

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer))
			return msg;
		if (!enabled)
			return msg;
		final ChannelBuffer buffer = (ChannelBuffer) msg;

		final int offset = buffer.readerIndex();
		final int size = buffer.readableBytes();
		int temp = 0;
		synchronized (key) {
			for (int i = 0; i < size; i++) {
				int temp2 = buffer.getByte(offset + i) & 0xFF;
				buffer.setByte(offset + i, (temp2 ^ key.get(i) ^ temp));
				temp = temp2;
			}
			key.update(size);
		}

		return buffer;
	}

	/**
	 * Creates a random key and enables descrypting
	 * 
	 * @return the generated key
	 */
	public Lineage2CryptographyKey enable() {
		Lineage2CryptographyKey key = Lineage2CryptographyKey.createRandomKey();
		this.setKey(key);
		this.setEnabled(true);
		return key;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
