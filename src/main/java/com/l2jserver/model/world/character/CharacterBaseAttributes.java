package com.l2jserver.model.world.character;

/**
 * Defines the attributes of an character
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterBaseAttributes implements CharacterAttributes {
	/**
	 * The character intelligence
	 */
	private final int intelligence;
	/**
	 * The character strength
	 */
	private final int strength;
	/**
	 * The character concentration
	 */
	private final int concentration;
	/**
	 * The character mentality power
	 */
	private final int mentality;
	/**
	 * The character dexterity
	 */
	private final int dexterity;
	/**
	 * The character witness
	 */
	private final int witness;

	/**
	 * The default physical attack
	 */
	private final int physicalAttack;
	/**
	 * The default magical attack
	 */
	private final int magicalAttack;
	/**
	 * The physical defense
	 */
	private final int physicalDefense;
	/**
	 * The magical defense
	 */
	private final int magicalDefense;

	/**
	 * The physical attack speed
	 */
	private final int attackSpeed;
	/**
	 * The "magical attack speed" (aka cast speed)
	 */
	private final int castSpeed;

	/**
	 * The character accuracy
	 */
	private final int accuracy;
	/**
	 * Chance of issuing an critical attack
	 */
	private final int criticalChance;
	/**
	 * Chance of avoiding an attack
	 */
	private final int evasionChance;
	/**
	 * The character's movement speed
	 */
	private final int moveSpeed;
	/**
	 * The maximum weigth in items to be carried in the inventory
	 */
	private final int maxWeigth;
	/**
	 * If this character can craft
	 */
	private final boolean craft;

	/**
	 * Creates a new instance
	 * 
	 * @param intelligence
	 *            the intelligence
	 * @param strength
	 *            the strength
	 * @param concentration
	 *            the concentration
	 * @param mentality
	 *            the mentality
	 * @param dexterity
	 *            the dexterity
	 * @param witness
	 *            the witness
	 * @param physicalAttack
	 *            the physical attack
	 * @param magicalAttack
	 *            the magical attack
	 * @param physicalDefense
	 *            the physical defense
	 * @param magicalDefense
	 *            the magical defense
	 * @param attackSpeed
	 *            the attack speed
	 * @param castSpeed
	 *            the cast speed
	 * @param accuracy
	 *            the accuracy
	 * @param criticalChance
	 *            the critical chance
	 * @param evasionChance
	 *            the evasion chance
	 * @param maxWeigth
	 *            the maximum weight in inventory
	 * @param moveSpeed
	 *            the character movement speed
	 * @param craft
	 *            if the character can craft items
	 */
	public CharacterBaseAttributes(int intelligence, int strength,
			int concentration, int mentality, int dextry, int witness,
			int physicalAttack, int magicalAttack, int physicalDefense,
			int magicalDefense, int attackSpeed, int castSpeed, int accuracy,
			int criticalChance, int evasionChance, int moveSpeed,
			int maxWeigth, boolean craft) {
		this.intelligence = intelligence;
		this.strength = strength;
		this.concentration = concentration;
		this.mentality = mentality;
		this.dexterity = dextry;
		this.witness = witness;
		this.physicalAttack = physicalAttack;
		this.magicalAttack = magicalAttack;
		this.physicalDefense = physicalDefense;
		this.magicalDefense = magicalDefense;
		this.attackSpeed = attackSpeed;
		this.castSpeed = castSpeed;
		this.accuracy = accuracy;
		this.criticalChance = criticalChance;
		this.evasionChance = evasionChance;
		this.moveSpeed = moveSpeed;
		this.maxWeigth = maxWeigth;
		this.craft = craft;
	}

	/**
	 * @return the intelligence
	 */
	@Override
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * @return the strength
	 */
	@Override
	public int getStrength() {
		return strength;
	}

	/**
	 * @return the concentration
	 */
	@Override
	public int getConcentration() {
		return concentration;
	}

	/**
	 * @return the mentality
	 */
	@Override
	public int getMentality() {
		return mentality;
	}

	/**
	 * @return the dexterity
	 */
	@Override
	public int getDexterity() {
		return dexterity;
	}

	/**
	 * @return the witness
	 */
	@Override
	public int getWitness() {
		return witness;
	}

	/**
	 * @return the physicalAttack
	 */
	@Override
	public int getPhysicalAttack() {
		return physicalAttack;
	}

	/**
	 * @return the magicalAttack
	 */
	@Override
	public int getMagicalAttack() {
		return magicalAttack;
	}

	/**
	 * @return the physicalDefense
	 */
	@Override
	public int getPhysicalDefense() {
		return physicalDefense;
	}

	/**
	 * @return the magicalDefense
	 */
	@Override
	public int getMagicalDefense() {
		return magicalDefense;
	}

	/**
	 * @return the attackSpeed
	 */
	@Override
	public int getAttackSpeed() {
		return attackSpeed;
	}

	/**
	 * @return the castSpeed
	 */
	@Override
	public int getCastSpeed() {
		return castSpeed;
	}

	/**
	 * @return the accuracy
	 */
	@Override
	public int getAccuracy() {
		return accuracy;
	}

	/**
	 * @return the criticalChance
	 */
	@Override
	public int getCriticalChance() {
		return criticalChance;
	}

	/**
	 * @return the evasionChance
	 */
	@Override
	public int getEvasionChance() {
		return evasionChance;
	}

	/**
	 * @return the moveSpeed
	 */
	@Override
	public int getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * @return the maxWeigth
	 */
	@Override
	public int getMaxWeigth() {
		return maxWeigth;
	}

	/**
	 * @return the craft
	 */
	@Override
	public boolean canCraft() {
		return craft;
	}
}
