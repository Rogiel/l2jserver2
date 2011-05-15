package com.l2jserver.model.template;

import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.L2Character;

/**
 * Template for {@link NPC}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class NPCTemplate extends ActorTemplate<L2Character> {
	protected NPCTemplate(CharacterTemplateID id, Race race, int intelligence,
			int strength, int concentration, int mentality, int dexterity,
			int witness, int physicalAttack, int magicalAttack,
			int physicalDefense, int magicalDefense, int attackSpeed,
			int castSpeed, int accuracy, int criticalChance, int evasionChance,
			int moveSpeed, int maxWeigth, boolean craft) {
		super(id, race, intelligence, strength, concentration, mentality,
				dexterity, witness, physicalAttack, magicalAttack,
				physicalDefense, magicalDefense, attackSpeed, castSpeed,
				accuracy, criticalChance, evasionChance, moveSpeed, maxWeigth,
				craft);
	}

	@Override
	public L2Character createInstance() {
		return null;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	@Override
	public NPCTemplateID getID() {
		return (NPCTemplateID) super.getID();
	}
}
