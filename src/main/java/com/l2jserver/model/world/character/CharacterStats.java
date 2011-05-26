/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.world.character;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.stat.Stats.StatType;
import com.l2jserver.model.world.character.calculator.BaseAttackAccuracyCalculator;
import com.l2jserver.model.world.character.calculator.BaseAttackEvasionCalculator;
import com.l2jserver.model.world.character.calculator.BaseCPCalculator;
import com.l2jserver.model.world.character.calculator.BaseConcentrationCalculator;
import com.l2jserver.model.world.character.calculator.BaseDexterityCalculator;
import com.l2jserver.model.world.character.calculator.BaseHPCalculator;
import com.l2jserver.model.world.character.calculator.BaseIntelligenceCalculator;
import com.l2jserver.model.world.character.calculator.BaseMPCalculator;
import com.l2jserver.model.world.character.calculator.BaseMagicalAttackCalculator;
import com.l2jserver.model.world.character.calculator.BaseMagicalAttackSpeedCalculator;
import com.l2jserver.model.world.character.calculator.BaseMagicalCriticalRateCalculator;
import com.l2jserver.model.world.character.calculator.BaseMagicalDefenseCalculator;
import com.l2jserver.model.world.character.calculator.BaseMentalityCalculator;
import com.l2jserver.model.world.character.calculator.BasePhysicalAttackCalculator;
import com.l2jserver.model.world.character.calculator.BasePhysicalAttackSpeedCalculator;
import com.l2jserver.model.world.character.calculator.BasePhysicalCriticalRateCalculator;
import com.l2jserver.model.world.character.calculator.BasePhysicalDefenseCalculator;
import com.l2jserver.model.world.character.calculator.BaseRunSpeedCalculator;
import com.l2jserver.model.world.character.calculator.BaseStrengthCalculator;
import com.l2jserver.model.world.character.calculator.BaseWalkSpeedCalculator;
import com.l2jserver.model.world.character.calculator.BaseWitnessCalculator;
import com.l2jserver.model.world.character.calculator.CharacterCalculator;
import com.l2jserver.model.world.character.calculator.CharacterCalculatorContext;
import com.l2jserver.util.calculator.Calculator;

/**
 * This class is responsible for calculating the real character stats. The real
 * stats vary from the values from the templates, also, skills and items
 * equipped can change those values. Once an buff is applied, a new calculator
 * is {@link Calculator#importFunctions(Calculator) imported} and their
 * functions are added to this class calculator. Once the skill effect has past
 * away, all the functions that were imported are now
 * {@link Calculator#removeFunctions(Calculator) removed} and the calculator
 * return to its original state.
 * <p>
 * Another important note is that calculators should perform calculations as
 * fast as possible.
 * <p>
 * <i><b>IMPORTANT</b>: NEVER TOUCH THE STATIC CALCULATORS!</i>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterStats {
	/**
	 * The calculator for base maximum HP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_HP_CALCULATOR = new BaseHPCalculator();
	/**
	 * The calculator for base maximum MP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MP_CALCULATOR = new BaseMPCalculator();
	/**
	 * The calculator for base maximum CP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_CP_CALCULATOR = new BaseCPCalculator();

	/**
	 * The calculator for base intelligence
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_INT_CALCULATOR = new BaseIntelligenceCalculator();
	/**
	 * The calculator for base strength
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_STR_CALCULATOR = new BaseStrengthCalculator();
	/**
	 * The calculator for base concentration
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_CON_CALCULATOR = new BaseConcentrationCalculator();
	/**
	 * The calculator for base mentality
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MEN_CALCULATOR = new BaseMentalityCalculator();
	/**
	 * The calculator for base dexterity
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_DEX_CALCULATOR = new BaseDexterityCalculator();
	/**
	 * The calculator for base witness
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_WIT_CALCULATOR = new BaseWitnessCalculator();

	/**
	 * The calculator for base run speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_RUN_SPEED_CALCULATOR = new BaseRunSpeedCalculator();
	/**
	 * The calculator for base walk speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_WALK_SPEED_CALCULATOR = new BaseWalkSpeedCalculator();

	/**
	 * The calculator base physical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_ATTACK_CALCULATOR = new BasePhysicalAttackCalculator();
	/**
	 * The calculator base physical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_ATTACK_SPEED_CALCULATOR = new BasePhysicalAttackSpeedCalculator();
	/**
	 * The calculator base physical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_CRITICAL_RATE_CALCULATOR = new BasePhysicalCriticalRateCalculator();
	/**
	 * The calculator base physical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_DEFENSE_CALCULATOR = new BasePhysicalDefenseCalculator();

	/**
	 * The calculator base magical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_ATTACK_CALCULATOR = new BaseMagicalAttackCalculator();
	/**
	 * The calculator base magical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_ATTACK_SPEED_CALCULATOR = new BaseMagicalAttackSpeedCalculator();
	/**
	 * The calculator base magical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_CRITICAL_RATE_CALCULATOR = new BaseMagicalCriticalRateCalculator();
	/**
	 * The calculator base magical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_DEFENSE_CALCULATOR = new BaseMagicalDefenseCalculator();

	/**
	 * The calculator base attack accuracy
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_ATTACK_ACCURACY_CALCULATOR = new BaseAttackAccuracyCalculator();
	/**
	 * The calculator base evasion
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_ATTACK_EVASION_CALCULATOR = new BaseAttackEvasionCalculator();

	/**
	 * The character
	 */
	private final L2Character character;
	/**
	 * The list of calculators for this character
	 * <p>
	 * It is safe to use an array since this number cannot be changed in
	 * runtime, it would be required to be able to change the {@link StatType}
	 * enum. Also, an full-sized array is created because this way we don't need
	 * to change the array size very often. A bit of memory is "lost", but the
	 * speed gain is much higher.
	 */
	@SuppressWarnings("unchecked")
	private final Calculator<L2Character>[] calculators = new Calculator[StatType
			.values().length];

	/**
	 * Creates a new {@link CharacterStats} and adds default calculators
	 * 
	 * @param character
	 *            the character
	 */
	public CharacterStats(L2Character character) {
		this.character = character;
		for (int i = 0; i < calculators.length; i++) {
			calculators[i] = new Calculator<L2Character>();
		}

		// import default functions
		getCalculator(StatType.MAX_HP).importFunctions(BASE_HP_CALCULATOR);
		getCalculator(StatType.MAX_MP).importFunctions(BASE_MP_CALCULATOR);
		getCalculator(StatType.MAX_CP).importFunctions(BASE_CP_CALCULATOR);

		getCalculator(StatType.STAT_INT).importFunctions(BASE_INT_CALCULATOR);
		getCalculator(StatType.STAT_STR).importFunctions(BASE_STR_CALCULATOR);
		getCalculator(StatType.STAT_CON).importFunctions(BASE_CON_CALCULATOR);
		getCalculator(StatType.STAT_MEN).importFunctions(BASE_MEN_CALCULATOR);
		getCalculator(StatType.STAT_DEX).importFunctions(BASE_DEX_CALCULATOR);
		getCalculator(StatType.STAT_WIT).importFunctions(BASE_WIT_CALCULATOR);

		getCalculator(StatType.RUN_SPEED).importFunctions(
				BASE_RUN_SPEED_CALCULATOR);
		getCalculator(StatType.WALK_SPEED).importFunctions(
				BASE_WALK_SPEED_CALCULATOR);

		getCalculator(StatType.POWER_ATTACK).importFunctions(
				BASE_PHYSICAL_ATTACK_CALCULATOR);
		getCalculator(StatType.POWER_ATTACK_SPEED).importFunctions(
				BASE_PHYSICAL_ATTACK_SPEED_CALCULATOR);
		getCalculator(StatType.CRITICAL_RATE).importFunctions(
				BASE_PHYSICAL_CRITICAL_RATE_CALCULATOR);
		getCalculator(StatType.POWER_DEFENSE).importFunctions(
				BASE_PHYSICAL_DEFENSE_CALCULATOR);

		getCalculator(StatType.MAGIC_ATTACK).importFunctions(
				BASE_MAGICAL_ATTACK_CALCULATOR);
		getCalculator(StatType.MAGIC_ATTACK_SPEED).importFunctions(
				BASE_MAGICAL_ATTACK_SPEED_CALCULATOR);
		getCalculator(StatType.MCRITICAL_RATE).importFunctions(
				BASE_MAGICAL_CRITICAL_RATE_CALCULATOR);
		getCalculator(StatType.MAGIC_DEFENSE).importFunctions(
				BASE_MAGICAL_DEFENSE_CALCULATOR);

		getCalculator(StatType.ACCURACY_COMBAT).importFunctions(
				BASE_ATTACK_ACCURACY_CALCULATOR);
		getCalculator(StatType.EVASION_RATE).importFunctions(
				BASE_ATTACK_EVASION_CALCULATOR);

		// TODO henna stats calculators
	}

	/**
	 * @return the calculated maximum HP
	 */
	public int getMaxHP() {
		return (int) calc(StatType.MAX_HP);
	}

	/**
	 * @return the calculated maximum MP
	 */
	public int getMaxMP() {
		return (int) calc(StatType.MAX_MP);
	}

	/**
	 * @return the calculated maximum CP
	 */
	public int getMaxCP() {
		return (int) calc(StatType.MAX_CP);
	}

	/**
	 * @return the calculated intelligence
	 */
	public int getIntelligence() {
		return (int) calc(StatType.STAT_INT);
	}

	/**
	 * @return the calculated strength
	 */
	public int getStrength() {
		return (int) calc(StatType.STAT_STR);
	}

	/**
	 * @return the calculated concentration
	 */
	public int getConcentration() {
		return (int) calc(StatType.STAT_CON);
	}

	/**
	 * @return the calculated mentality
	 */
	public int getMentality() {
		return (int) calc(StatType.STAT_MEN);
	}

	/**
	 * @return the calculated dexterity
	 */
	public int getDexterity() {
		return (int) calc(StatType.STAT_DEX);
	}

	/**
	 * @return the calculated witness
	 */
	public int getWitness() {
		return (int) calc(StatType.STAT_WIT);
	}

	/**
	 * @return the calculated run speed
	 */
	public int getRunSpeed() {
		return (int) calc(StatType.RUN_SPEED);
	}

	/**
	 * @return the calculated walk speed
	 */
	public int getWalkSpeed() {
		return (int) calc(StatType.WALK_SPEED);
	}

	/**
	 * @return the calculated physical attack
	 */
	public int getPhysicalAttack() {
		return (int) calc(StatType.POWER_ATTACK);
	}

	/**
	 * @return the calculated physical attack speed
	 */
	public int getPhysicalAttackSpeed() {
		return (int) calc(StatType.POWER_ATTACK_SPEED);
	}

	/**
	 * @return the calculated physical attack critical rate
	 */
	public int getPhysicalCriticalRate() {
		return (int) calc(StatType.CRITICAL_RATE);
	}

	/**
	 * @return the calculated physical defense
	 */
	public int getPhysicalDefense() {
		return (int) calc(StatType.POWER_DEFENSE);
	}

	/**
	 * @return the calculated magical attack
	 */
	public int getMagicalAttack() {
		return (int) calc(StatType.MAGIC_ATTACK);
	}

	/**
	 * @return the calculated magical attack speed
	 */
	public int getMagicalAttackSpeed() {
		return (int) calc(StatType.MAGIC_ATTACK_SPEED);
	}

	/**
	 * @return the calculated magical attack critical rate
	 */
	public int getMagicalCriticalRate() {
		return (int) calc(StatType.MCRITICAL_RATE);
	}

	/**
	 * @return the calculated magical defense
	 */
	public int getMagicalDefense() {
		return (int) calc(StatType.MAGIC_DEFENSE);
	}

	/**
	 * @return the calculated accuracy
	 */
	public int getAccuracy() {
		return (int) calc(StatType.ACCURACY_COMBAT);
	}

	/**
	 * @return the calculated evasion rate
	 */
	public int getEvasionRate() {
		return (int) calc(StatType.EVASION_RATE);
	}

	/**
	 * @return the calculated maximum load
	 */
	public int getMaximumLoad() {
		return (int) calc(StatType.MAX_LOAD);
	}

	// public void add(StatType type, Calculator<?> calculator) {
	// getCalculator(type).importFunctions(calculator);
	// }

	/**
	 * @param the
	 *            calculator {@link StatType}
	 * @return the calculator object associated with the given <tt>type</tt>
	 */
	protected Calculator<L2Character> getCalculator(StatType type) {
		return calculators[type.ordinal()];
	}

	/**
	 * Does the calculation of an given {@link StatType}
	 * 
	 * @param type
	 *            the type
	 * @return the value calculated
	 */
	public double calc(StatType type) {
		final CharacterCalculatorContext ctx = new CharacterCalculatorContext(
				character);
		getCalculator(type).calculate(character, ctx);
		return ctx.result;
	}
}
