package com.l2jserver.game.net;

import com.l2jserver.util.BlowFishKeygen;

public class Lineage2CryptographyKey implements Cloneable {
	public final byte[] key;

	public Lineage2CryptographyKey(byte[] key) {
		this.key = key;
	}

	public static Lineage2CryptographyKey createRandomKey() {
		return new Lineage2CryptographyKey(BlowFishKeygen.getRandomKey());
	}

	/**
	 * @return the key
	 */
	public byte[] getKey() {
		return key;
	}

	public byte get(int i) {
		return key[i & 15];
	}

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
