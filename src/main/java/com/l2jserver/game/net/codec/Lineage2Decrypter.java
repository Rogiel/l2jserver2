package com.l2jserver.game.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.l2jserver.game.net.Lineage2CryptographyKey;

public class Lineage2Decrypter extends OneToOneDecoder {
	public static final String HANDLER_NAME = "crypto.decoder";

	private boolean enabled = false;
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

	public Lineage2CryptographyKey enable() {
		Lineage2CryptographyKey key = Lineage2CryptographyKey.createRandomKey();
		this.setKey(key);
		this.setEnabled(true);
		return key;
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
