package com.l2jserver.model.template.item;

import com.l2jserver.model.template.PotionTemplate;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;

public class TestPotionTemplate extends PotionTemplate {
	public TestPotionTemplate() {
		super(null);
	}

	@Override
	public void consume(Attacker source, Attackable target) {
		//TODO consume item
	}
}
