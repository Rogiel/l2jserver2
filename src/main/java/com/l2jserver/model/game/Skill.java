package com.l2jserver.model.game;

import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.capability.Actor;

/**
 * Register the state of a skill known by an {@link Actor}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Skill {
	/**
	 * The skill template ID
	 */
	private final SkillTemplateID skillTemplateID;
	/**
	 * The actor id that has learned this skill
	 */
	private ActorID<?> actorID;

	/**
	 * The skill level learned
	 */
	private int level;

	/**
	 * Creates a new instance
	 * 
	 * @param skillTemplateID
	 *            the skill template id
	 * @param level
	 *            the skill level
	 */
	public Skill(SkillTemplateID skillTemplateID, int level) {
		this.skillTemplateID = skillTemplateID;
		this.level = level;
	}

	/**
	 * Creates a new instance in level one
	 * 
	 * @param skillTemplateID
	 *            the skill template id
	 */
	public Skill(SkillTemplateID skillTemplateID) {
		this(skillTemplateID, 1);
	}

	/**
	 * @return true if this skill is learned in the maximum level
	 */
	public boolean isMaximumLevel() {
		return level >= skillTemplateID.getTemplate().getMaximumLevel();
	}

	/**
	 * @return the actorID
	 */
	public ActorID<?> getActorID() {
		return actorID;
	}

	/**
	 * @return the actor
	 */
	public Actor getActor() {
		return actorID.getObject();
	}

	/**
	 * @param actorID
	 *            the actor ID to set
	 */
	public void setActorID(ActorID<?> actorID) {
		this.actorID = actorID;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the skillTemplateID
	 */
	public SkillTemplateID getSkillTemplateID() {
		return skillTemplateID;
	}

	/**
	 * @return the skillTemplate
	 */
	public SkillTemplate getSkillTemplate() {
		return skillTemplateID.getTemplate();
	}
}
