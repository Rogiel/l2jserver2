package com.l2jserver.game.net.codec;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.l2jserver.util.BlowFishKeygen;

public class Lineage2Decrypter extends OneToOneDecoder {
	public static final String HANDLER_NAME = "crypto.decoder";

	private boolean enabled = false;
	private final byte[] key = new byte[16];

	@Override
	protected synchronized Object decode(ChannelHandlerContext ctx,
			Channel channel, Object msg) throws Exception {
		if (!(msg instanceof ChannelBuffer))
			return msg;
		if (!enabled)
			return msg;
		final ChannelBuffer buffer = (ChannelBuffer) msg;

		final int offset = buffer.readerIndex();
		final int size = buffer.readableBytes();
		int temp = 0;
		for (int i = 0; i < size; i++) {
			int temp2 = buffer.getByte(offset + i) & 0xFF;
			buffer.setByte(offset + i, (temp2 ^ key[i & 15] ^ temp));
			temp = temp2;
		}

		// int old = _inKey[8] &0xff;
		// old |= _inKey[9] << 8 &0xff00;
		// old |= _inKey[10] << 0x10 &0xff0000;
		// old |= _inKey[11] << 0x18 &0xff000000;
		//
		// old += size;
		//
		// _inKey[8] = (byte)(old &0xff);
		// _inKey[9] = (byte)(old >> 0x08 &0xff);
		// _inKey[10] = (byte)(old >> 0x10 &0xff);
		// _inKey[11] = (byte)(old >> 0x18 &0xff);

		int old = key[8] & 0xff;
		old |= key[9] << 8 & 0xff00;
		old |= key[10] << 0x10 & 0xff0000;
		old |= key[11] << 0x18 & 0xff000000;

		old += size;

		key[8] = (byte) (old & 0xff);
		key[9] = (byte) (old >> 0x08 & 0xff);
		key[10] = (byte) (old >> 0x10 & 0xff);
		key[11] = (byte) (old >> 0x18 & 0xff);

		return buffer;
	}

	public byte[] enable() {
		byte[] key = BlowFishKeygen.getRandomKey();
		this.setKey(key);
		this.setEnabled(true);
		return key;
	}

	public void setKey(byte[] key) {
		System.arraycopy(key, 0, this.key, 0, key.length);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
