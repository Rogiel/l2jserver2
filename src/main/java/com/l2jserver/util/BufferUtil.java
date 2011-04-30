package com.l2jserver.util;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

public class BufferUtil {
	public static final String readString(ChannelBuffer buffer) {
		char[] str = new char[buffer.readableBytes()];
		int index = 0;
		char c;
		while ((c = buffer.readChar()) != 0) {
			str[index++] = c;
		}
		return String.valueOf(Arrays.copyOfRange(str, 0, index));
	}

	public static final void writeString(ChannelBuffer buffer, String str) {
		for (char c : str.toCharArray()) {
			buffer.writeChar(c);
		}
		buffer.writeChar(0x00);
	}
}
