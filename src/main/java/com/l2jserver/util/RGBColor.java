package com.l2jserver.util;

public class RGBColor {
	private final byte red;
	private final byte green;
	private final byte blue;

	protected RGBColor(byte r, byte g, byte b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}

	/**
	 * @return the red
	 */
	public byte getR() {
		return red;
	}

	/**
	 * @return the green
	 */
	public byte getG() {
		return green;
	}

	/**
	 * @return the blue
	 */
	public byte getB() {
		return blue;
	}

	public byte[] toByteArray() {
		return new byte[] { red, green, blue };
	}

	public static RGBColor fromByteArray(byte[] rgb) {
		return new RGBColor(rgb[0], rgb[1], rgb[2]);
	}

	public static RGBColor fromInteger(int color) {
		return new RGBColor((byte) (color << 0), (byte) (color << 8),
				(byte) (color << 16));
	}
}
