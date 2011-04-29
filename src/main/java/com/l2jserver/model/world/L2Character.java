package com.l2jserver.model.world;

import com.l2jserver.model.id.CharacterID;

public class L2Character extends Player {
	@Override
	public CharacterID getId() {
		return (CharacterID) super.getId();
	}
}
