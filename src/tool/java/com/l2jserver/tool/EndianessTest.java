package com.l2jserver.tool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class EndianessTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ByteBuffer bigEndian = ByteBuffer.allocate(8);
		bigEndian.order(ByteOrder.BIG_ENDIAN);
		bigEndian.putDouble(20);
		System.out.println(Arrays.toString(bigEndian.array()));

		final ByteBuffer littleEndian = ByteBuffer.allocate(8);
		littleEndian.order(ByteOrder.LITTLE_ENDIAN);
		littleEndian.putDouble(20);
		System.out.println(Arrays.toString(littleEndian.array()));
	}
}
