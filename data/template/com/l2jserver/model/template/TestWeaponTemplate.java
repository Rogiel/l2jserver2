package com.l2jserver.model.template;

import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Enchantable;

public class TestWeaponTemplate extends WeaponTemplate {
	private static final int DAMAGE = 10;
	private static final int MAX_ENCHANT_LEVEL = 10;

	public TestWeaponTemplate() {
		super(null);
	}

	@Override
	public void attack(Attacker source, Attackable target) {
		// TODO deal damage!
	}

	@Override
	public void enchant(Enchantable target) {
		if (target.getEnchantLevel() == MAX_ENCHANT_LEVEL)
			return;
		// TODO do enchant
	}

	@Override
	public int getDamage() {
		return DAMAGE;
	}
}
