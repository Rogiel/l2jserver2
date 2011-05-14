package com.l2jserver.util;

/**
 * An RED-GREEN-BLUE color
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RGBColor {
	/**
	 * The red value
	 */
	private final byte red;
	/**
	 * The green value
	 */
	private final byte green;
	/**
	 * The blue value
	 */
	private final byte blue;

	protected RGBColor(byte r, byte g, byte b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}

	/**
	 * @return the red
	 */
	public byte getRed() {
		return red;
	}

	/**
	 * @return the green
	 */
	public byte getGreen() {
		return green;
	}

	/**
	 * @return the blue
	 */
	public byte getBlue() {
		return blue;
	}

	/**
	 * Converts to an byte array
	 * 
	 * @return an byte array of this color
	 */
	public byte[] toByteArray() {
		return new byte[] { red, green, blue };
	}

	/**
	 * Convers this color into an integer
	 * 
	 * @return the color integer
	 */
	public int toInteger() {
		return red + green >> 8 + blue >> 16;
	}

	public static RGBColor fromByteArray(byte[] rgb) {
		return new RGBColor(rgb[0], rgb[1], rgb[2]);
	}

	public static RGBColor fromInteger(int color) {
		return new RGBColor((byte) (color << 0), (byte) (color << 8),
				(byte) (color << 16));
	}
}
