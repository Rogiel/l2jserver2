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

	@Override
	public int getIntelligence() {
		return baseAttributes.getIntelligence();
	}

	@Override
	public int getStrength() {
		return baseAttributes.getStrength();
	}

	@Override
	public int getConcentration() {
		return baseAttributes.getConcentration();
	}

	@Override
	public int getMentality() {
		return baseAttributes.getMentality();
	}

	@Override
	public int getDexterity() {
		return baseAttributes.getDexterity();
	}

	@Override
	public int getWitness() {
		return baseAttributes.getWitness();
	}

	@Override
	public int getPhysicalAttack() {
		return baseAttributes.getPhysicalAttack();
	}

	@Override
	public int getMagicalAttack() {
		return baseAttributes.getMagicalAttack();
	}

	@Override
	public int getPhysicalDefense() {
		return baseAttributes.getPhysicalDefense();
	}

	@Override
	public int getMagicalDefense() {
		return baseAttributes.getMagicalDefense();
	}

	@Override
	public int getAttackSpeed() {
		return baseAttributes.getAttackSpeed();
	}

	@Override
	public int getCastSpeed() {
		return baseAttributes.getCastSpeed();
	}

	@Override
	public int getAccuracy() {
		return baseAttributes.getAccuracy();
	}

	@Override
	public int getCriticalChance() {
		return baseAttributes.getCriticalChance();
	}

	@Override
	public int getEvasionChance() {
		return baseAttributes.getEvasionChance();
	}

	@Override
	public int getMoveSpeed() {
		return baseAttributes.getMoveSpeed();
	}

	@Override
	public int getMaxWeigth() {
		return baseAttributes.getMaxWeigth();
	}

	@Override
	public boolean canCraft() {
		return baseAttributes.canCraft();
	}

}
