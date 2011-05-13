package com.l2jserver.model.world.character;

public interface CharacterAttributes {
	/**
	 * @return the intelligence
	 */
	public int getIntelligence();

	/**
	 * @return the strength
	 */
	public int getStrength();

	/**
	 * @return the concentration
	 */
	public int getConcentration();

	/**
	 * @return the mentality
	 */
	public int getMentality();

	/**
	 * @return the dexterity
	 */
	public int getDexterity();

	/**
	 * @return the witness
	 */
	public int getWitness();

	/**
	 * @return the physicalAttack
	 */
	public int getPhysicalAttack();

	/**
	 * @return the magicalAttack
	 */
	public int getMagicalAttack();

	/**
	 * @return the physicalDefense
	 */
	public int getPhysicalDefense();

	/**
	 * @return the magicalDefense
	 */
	public int getMagicalDefense();

	/**
	 * @return the attackSpeed
	 */
	public int getAttackSpeed();

	/**
	 * @return the castSpeed
	 */
	public int getCastSpeed();

	/**
	 * @return the accuracy
	 */
	public int getAccuracy();

	/**
	 * @return the criticalChance
	 */
	public int getCriticalChance();

	/**
	 * @return the evasionChance
	 */
	public int getEvasionChance();

	/**
	 * @return the movement speed
	 */
	public int getMoveSpeed();

	/**
	 * @return the maxWeigth
	 */
	public int getMaxWeigth();

	/**
	 * @return the craft
	 */
	public boolean canCraft();
}
