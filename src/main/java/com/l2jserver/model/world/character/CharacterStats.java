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
import com.l2jserver.model.world.actor.stat.ActorStats;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.model.world.character.calculator.CharacterCalculator;
import com.l2jserver.model.world.character.calculator.CharacterCalculatorContext;
import com.l2jserver.model.world.character.calculator.MaximumCPAddCalculator;
import com.l2jserver.model.world.character.calculator.MaximumCPBonusCalculator;
import com.l2jserver.model.world.character.calculator.MaximumHPAddCalculator;
import com.l2jserver.model.world.character.calculator.MaximumMPAddCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseAttackAccuracyCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseAttackEvasionCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseCPCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseConcentrationCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseDexterityCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseHPCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseIntelligenceCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseMPCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseMagicalAttackCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseMagicalAttackSpeedCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseMagicalCriticalRateCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseMagicalDefenseCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseMentalityCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBasePhysicalAttackCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBasePhysicalAttackSpeedCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBasePhysicalCriticalRateCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBasePhysicalDefenseCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseRunSpeedCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseStrengthCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseWalkSpeedCalculator;
import com.l2jserver.model.world.character.calculator.base.CharacterBaseWitnessCalculator;
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
public class CharacterStats extends ActorStats<CharacterCalculatorContext> {
	// base calculators
	/**
	 * The calculator for base maximum MP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_HP_CALCULATOR = new CharacterBaseHPCalculator();
	/**
	 * The calculator for base maximum MP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MP_CALCULATOR = new CharacterBaseMPCalculator();
	/**
	 * The calculator for base maximum CP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_CP_CALCULATOR = new CharacterBaseCPCalculator();

	/**
	 * The calculator for base intelligence
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_INT_CALCULATOR = new CharacterBaseIntelligenceCalculator();
	/**
	 * The calculator for base strength
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_STR_CALCULATOR = new CharacterBaseStrengthCalculator();
	/**
	 * The calculator for base concentration
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_CON_CALCULATOR = new CharacterBaseConcentrationCalculator();
	/**
	 * The calculator for base mentality
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MEN_CALCULATOR = new CharacterBaseMentalityCalculator();
	/**
	 * The calculator for base dexterity
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_DEX_CALCULATOR = new CharacterBaseDexterityCalculator();
	/**
	 * The calculator for base witness
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_WIT_CALCULATOR = new CharacterBaseWitnessCalculator();

	/**
	 * The calculator for base run speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_RUN_SPEED_CALCULATOR = new CharacterBaseRunSpeedCalculator();
	/**
	 * The calculator for base walk speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_WALK_SPEED_CALCULATOR = new CharacterBaseWalkSpeedCalculator();

	/**
	 * The calculator base physical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_ATTACK_CALCULATOR = new CharacterBasePhysicalAttackCalculator();
	/**
	 * The calculator base physical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_ATTACK_SPEED_CALCULATOR = new CharacterBasePhysicalAttackSpeedCalculator();
	/**
	 * The calculator base physical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_CRITICAL_RATE_CALCULATOR = new CharacterBasePhysicalCriticalRateCalculator();
	/**
	 * The calculator base physical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_PHYSICAL_DEFENSE_CALCULATOR = new CharacterBasePhysicalDefenseCalculator();

	/**
	 * The calculator base magical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_ATTACK_CALCULATOR = new CharacterBaseMagicalAttackCalculator();
	/**
	 * The calculator base magical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_ATTACK_SPEED_CALCULATOR = new CharacterBaseMagicalAttackSpeedCalculator();
	/**
	 * The calculator base magical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_CRITICAL_RATE_CALCULATOR = new CharacterBaseMagicalCriticalRateCalculator();
	/**
	 * The calculator base magical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_MAGICAL_DEFENSE_CALCULATOR = new CharacterBaseMagicalDefenseCalculator();

	/**
	 * The calculator base attack accuracy
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_ATTACK_ACCURACY_CALCULATOR = new CharacterBaseAttackAccuracyCalculator();
	/**
	 * The calculator base evasion
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator BASE_ATTACK_EVASION_CALCULATOR = new CharacterBaseAttackEvasionCalculator();
	
	// BONUS
	/**
	 * The calculator for CP bonus
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator CP_BONUS_CALCULATOR = new MaximumCPBonusCalculator();
	
	// ADD
	/**
	 * The calculator for HP add
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator HP_ADD_CALCULATOR = new MaximumHPAddCalculator();
	/**
	 * The calculator for MP add
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator MP_ADD_CALCULATOR = new MaximumMPAddCalculator();
	/**
	 * The calculator for CP add
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterCalculator CP_ADD_CALCULATOR = new MaximumCPAddCalculator();

	/**
	 * The character
	 */
	private final L2Character character;

	/**
	 * Creates a new {@link CharacterStats} and adds default calculators
	 * 
	 * @param character
	 *            the character
	 */
	public CharacterStats(L2Character character) {
		super();
		this.character = character;

		// base
		add(StatType.MAX_HP, BASE_HP_CALCULATOR);
		add(StatType.MAX_MP, BASE_MP_CALCULATOR);
		add(StatType.MAX_CP, BASE_CP_CALCULATOR);

		add(StatType.STAT_INT, BASE_INT_CALCULATOR);
		add(StatType.STAT_STR, BASE_STR_CALCULATOR);
		add(StatType.STAT_CON, BASE_CON_CALCULATOR);
		add(StatType.STAT_MEN, BASE_MEN_CALCULATOR);
		add(StatType.STAT_DEX, BASE_DEX_CALCULATOR);
		add(StatType.STAT_WIT, BASE_WIT_CALCULATOR);

		add(StatType.RUN_SPEED, BASE_RUN_SPEED_CALCULATOR);
		add(StatType.WALK_SPEED, BASE_WALK_SPEED_CALCULATOR);

		add(StatType.POWER_ATTACK, BASE_PHYSICAL_ATTACK_CALCULATOR);
		add(StatType.POWER_ATTACK_SPEED, BASE_PHYSICAL_ATTACK_SPEED_CALCULATOR);
		add(StatType.CRITICAL_RATE, BASE_PHYSICAL_CRITICAL_RATE_CALCULATOR);
		add(StatType.POWER_DEFENSE, BASE_PHYSICAL_DEFENSE_CALCULATOR);

		add(StatType.MAGIC_ATTACK, BASE_MAGICAL_ATTACK_CALCULATOR);
		add(StatType.MAGIC_ATTACK_SPEED, BASE_MAGICAL_ATTACK_SPEED_CALCULATOR);
		add(StatType.MCRITICAL_RATE, BASE_MAGICAL_CRITICAL_RATE_CALCULATOR);
		add(StatType.MAGIC_DEFENSE, BASE_MAGICAL_DEFENSE_CALCULATOR);

		add(StatType.ACCURACY_COMBAT, BASE_ATTACK_ACCURACY_CALCULATOR);
		add(StatType.EVASION_RATE, BASE_ATTACK_EVASION_CALCULATOR);

		// add hp/mp/cp add functions
		add(StatType.MAX_HP, HP_ADD_CALCULATOR);
		add(StatType.MAX_MP, MP_ADD_CALCULATOR);
		add(StatType.MAX_CP, CP_ADD_CALCULATOR);
		
		// bonus
		add(StatType.MAX_CP, CP_BONUS_CALCULATOR);
		
		// TODO henna stats calculators
	}

	/**
	 * @return the calculated maximum CP
	 */
	public int getMaxCP() {
		return (int) calc(StatType.MAX_CP);
	}

	/**
	 * @return the calculated maximum load
	 */
	public int getMaximumLoad() {
		return (int) calc(StatType.MAX_LOAD);
	}

	@Override
	protected CharacterCalculatorContext createContext() {
		return new CharacterCalculatorContext(character);
	}
}
