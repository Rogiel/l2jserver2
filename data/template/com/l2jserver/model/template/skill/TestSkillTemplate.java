package com.l2jserver.model.template.skill;

import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Caster;

public class TestSkillTemplate extends SkillTemplate {
	public TestSkillTemplate() {
		super(null);
	}

	@Override
	public void cast(Caster caster, Castable target) {
		// TODO do casting
	}
}
