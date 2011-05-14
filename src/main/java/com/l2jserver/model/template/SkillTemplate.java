package com.l2jserver.model.template;

import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.capability.Castable;
import com.l2jserver.model.world.character.CharacterClass;

/**
 * Template for skill
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class SkillTemplate extends AbstractTemplate<Void> implements
		Castable {
	public SkillTemplate(SkillTemplateID id) {
		super(id);
	}

	public abstract CharacterClass[] getClasses();

	@Override
	public Void create() {
		return null;
	}

	@Override
	public SkillTemplateID getID() {
		return (SkillTemplateID) super.getID();
	}
}
