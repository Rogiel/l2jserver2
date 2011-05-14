package com.l2jserver.game.net;

import java.util.Arrays;

import com.l2jserver.util.BlowFishKeygen;

/**
 * Manages the cryptography key used to write/read packets. This class also
 * updates the key once data has been sent/received.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2CryptographyKey implements Cloneable {
	/**
	 * The raw key
	 */
	public final byte[] key;

	/**
	 * Creates a new instance
	 * 
	 * @param key
	 *            the raw key
	 */
	public Lineage2CryptographyKey(byte[] key) {
		this.key = key;
	}

	/**
	 * Crates a new random key
	 * 
	 * @return the random created key
	 */
	public static Lineage2CryptographyKey createRandomKey() {
		return new Lineage2CryptographyKey(Arrays.copyOf(
				BlowFishKeygen.getRandomKey(), 16));
	}

	/**
	 * @return the key
	 */
	public byte[] getKey() {
		return key;
	}

	/**
	 * Get the key value for byte index <tt>i</tt>
	 * 
	 * @param i
	 *            the index i
	 * @return the key byte
	 */
	public byte get(int i) {
		return key[i & 15];
	}

	/**
	 * Updates this key once data has been sent/received.
	 * 
	 * @param size
	 *            the data size
	 */
	public void update(int size) {
		int old = key[8] & 0xff;
		old |= key[9] << 8 & 0xff00;
		old |= key[10] << 0x10 & 0xff0000;
		old |= key[11] << 0x18 & 0xff000000;

		old += size;

		key[8] = (byte) (old & 0xff);
		key[9] = (byte) (old >> 0x08 & 0xff);
		key[10] = (byte) (old >> 0x10 & 0xff);
		key[11] = (byte) (old >> 0x18 & 0xff);
	}

	@Override
	public Lineage2CryptographyKey clone() {
		try {
			return (Lineage2CryptographyKey) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
