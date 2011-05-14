package com.l2jserver.model.world.character;

import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.world.L2Character;

/**
 * This {@link CharacterAttributes} implementation calculates the <b>real</b>
 * character attributes based on it's {@link CharacterTemplate} and active
 * buffs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterCalculatedAttributes implements CharacterAttributes {
	/**
	 * The character
	 */
	private final L2Character character;
	/**
	 * The base attributes (from {@link CharacterTemplate})
	 */
	private final CharacterAttributes baseAttributes;

	public CharacterCalculatedAttributes(L2Character character) {
		this.character = character;
		this.baseAttributes = this.character.getBaseAttributes();
	}

	public int getIntelligence() {
		return baseAttributes.getIntelligence();
	}

	public int getStrength() {
		return baseAttributes.getStrength();
	}

	public int getConcentration() {
		return baseAttributes.getConcentration();
	}

	public int getMentality() {
		return baseAttributes.getMentality();
	}

	public int getDexterity() {
		return baseAttributes.getDexterity();
	}

	public int getWitness() {
		return baseAttributes.getWitness();
	}

	public int getPhysicalAttack() {
		return baseAttributes.getPhysicalAttack();
	}

	public int getMagicalAttack() {
		return baseAttributes.getMagicalAttack();
	}

	public int getPhysicalDefense() {
		return baseAttributes.getPhysicalDefense();
	}

	public int getMagicalDefense() {
		return baseAttributes.getMagicalDefense();
	}

	public int getAttackSpeed() {
		return baseAttributes.getAttackSpeed();
	}

	public int getCastSpeed() {
		return baseAttributes.getCastSpeed();
	}

	public int getAccuracy() {
		return baseAttributes.getAccuracy();
	}

	public int getCriticalChance() {
		return baseAttributes.getCriticalChance();
	}

	public int getEvasionChance() {
		return baseAttributes.getEvasionChance();
	}

	public int getMoveSpeed() {
		return baseAttributes.getMoveSpeed();
	}

	public int getMaxWeigth() {
		return baseAttributes.getMaxWeigth();
	}

	public boolean canCraft() {
		return baseAttributes.canCraft();
	}

}
