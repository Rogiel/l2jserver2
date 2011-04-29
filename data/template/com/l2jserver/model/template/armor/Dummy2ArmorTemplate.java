package com.l2jserver.model.template.armor;

import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Damagable;
import com.l2jserver.model.world.capability.Enchantable;

public class Dummy2ArmorTemplate extends AbstractGradeAArmorTemplate {
	private static final int REDUCED_DAMAGE_PHYSICAL = 10;
	private static final int REDUCED_DAMAGE_MAGICAL = 10;
	private static final int MAX_ENCHANT = 10;

	public Dummy2ArmorTemplate() {
		super(null);
	}

	@Override
	public void defend(Attacker source, Attackable target) {
	}

	@Override
	public void interceptIncomingDamage(Damagable target) {
	}

	@Override
	public void enchant(Enchantable target) {
		if (target.getEnchantLevel() >= MAX_ENCHANT)
			return;
		super.enchant(target);
	}

	@Override
	public int getPhysicalDefense() {
		return REDUCED_DAMAGE_PHYSICAL;
	}

	@Override
	public int getMagicalDefense() {
		return REDUCED_DAMAGE_MAGICAL;
	}
}
