package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.l2jserver.game.net.Lineage2CryptographyKey;

public class Lineage2Encrypter extends OneToOneEncoder {
	public static final String HANDLER_NAME = "crypto.encoder";

	private boolean enabled = false;
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
		int temp = 0;
		synchronized (key) {
			for (int i = 0; i < size; i++) {
				int temp2 = buffer.getByte(offset + i) & 0xFF;
				buffer.setByte(offset + i, (byte) (temp2 ^ key.get(i) ^ temp));
				temp = temp2;
			}
			key.update(size);
		}

		return msg;
	}

	public void enable(Lineage2CryptographyKey key) {
		this.setKey(key);
		this.setEnabled(true);
	}

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
