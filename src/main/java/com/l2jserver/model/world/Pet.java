package com.l2jserver.model.world;

import com.l2jserver.model.world.capability.Child;

public class Pet extends Player implements Child<Character> {
	@Override
	public Character getParent() {
		return null;
	}
}
