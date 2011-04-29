package com.l2jserver.model.template.weapon;

import com.l2jserver.model.template.WeaponTemplate;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;

public class TestWeaponTemplate extends WeaponTemplate {
	private static final int DAMAGE_PHYSICAL = 10;
	private static final int DAMAGE_MAGICAL = 10;
	private static final int MAX_ENCHANT_LEVEL = 10;

	public TestWeaponTemplate() {
		super(null);
	}

	@Override
	public void attack(Attacker source, Attackable target) {
		source.attack(target, this);
		target.receiveAttack(source, this);
	}

	@Override
	public int getPhysicalDamage() {
		return DAMAGE_PHYSICAL;
	}

	@Override
	public int getMagicalDamage() {
		return DAMAGE_MAGICAL;
	}
}
