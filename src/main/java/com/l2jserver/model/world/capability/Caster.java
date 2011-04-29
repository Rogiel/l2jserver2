package com.l2jserver.model.world.capability;

import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.AbstractObject;

/**
 * Defines an {@link AbstractObject} that can cast skills.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Caster extends ObjectCapability {
	void cast(SkillTemplate skill, Castable cast);
}
