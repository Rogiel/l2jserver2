package com.l2jserver.model.template;

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.capability.Castable;
import com.l2jserver.model.world.character.CharacterClass;

/**
 * Template for {@link Skill} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class SkillTemplate extends AbstractTemplate<Skill> implements
		Castable {
	/**
	 * The maximum level supported by this skill
	 */
	protected int maximumLevel = 1;

	public SkillTemplate(SkillTemplateID id) {
		super(id);
	}

	/**
	 * @return the maximumLevel
	 */
	public int getMaximumLevel() {
		return maximumLevel;
	}

	public abstract CharacterClass[] getClasses();

	@Override
	public Skill create() {
		final Skill skill = new Skill(this.getID());
		return skill;
	}

	@Override
	public SkillTemplateID getID() {
		return (SkillTemplateID) super.getID();
	}
}
