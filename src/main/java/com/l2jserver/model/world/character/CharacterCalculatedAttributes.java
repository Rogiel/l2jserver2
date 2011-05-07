package com.l2jserver.model.world.character;

import com.l2jserver.model.world.L2Character;

public class CharacterCalculatedAttributes implements CharacterAttributes {
	private final L2Character character;
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

	public int getDextry() {
		return baseAttributes.getDextry();
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
