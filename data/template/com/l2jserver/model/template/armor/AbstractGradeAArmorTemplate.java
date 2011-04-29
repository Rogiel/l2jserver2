package com.l2jserver.model.template.armor;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.ArmorTemplate;
import com.l2jserver.model.template.capability.Enchantable;
import com.l2jserver.model.template.capability.Penalty;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Equiper;
import com.l2jserver.model.world.capability.Levelable;

public abstract class AbstractGradeAArmorTemplate extends ArmorTemplate
		implements Enchantable, Penalty {
	public AbstractGradeAArmorTemplate(TemplateID id) {
		super(id);
	}

	@Override
	public void enchant(com.l2jserver.model.world.capability.Enchantable target) {
		target.setEnchantLevel(target.getEnchantLevel() + 1);
	}

	@Override
	public void penalty(Equiper user) {
		if (!(user instanceof Levelable) && !(user instanceof Castable) && !(user instanceof Equiper))
			return;
		final Levelable levelable = (Levelable) user;
		final Castable castable = (Castable) user;
		if (levelable.getLevel() < 20) {
			
		}
	}
}
