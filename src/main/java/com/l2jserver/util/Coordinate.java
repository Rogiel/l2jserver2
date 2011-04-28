package com.l2jserver.util;

public class Coordinate {
	private final int x;
	private final int y;
	private final int z;

	public Coordinate(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public int getDistance(Coordinate other) {
		//TODO calculation
		return x + y + z;
	}
}
