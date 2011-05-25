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
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class CharacterStats {
	private static final CharacterCalculator BASE_HP_CALCULATOR = new BaseHPCalculator();
	private static final CharacterCalculator BASE_MP_CALCULATOR = new BaseMPCalculator();
	private static final CharacterCalculator BASE_CP_CALCULATOR = new BaseCPCalculator();

	private static final CharacterCalculator BASE_INT_CALCULATOR = new BaseIntelligenceCalculator();
	private static final CharacterCalculator BASE_STR_CALCULATOR = new BaseStrengthCalculator();
	private static final CharacterCalculator BASE_CON_CALCULATOR = new BaseConcentrationCalculator();
	private static final CharacterCalculator BASE_MEN_CALCULATOR = new BaseMentalityCalculator();
	private static final CharacterCalculator BASE_DEX_CALCULATOR = new BaseDexterityCalculator();
	private static final CharacterCalculator BASE_WIT_CALCULATOR = new BaseWitnessCalculator();

	private static final CharacterCalculator BASE_RUN_SPEED_CALCULATOR = new BaseRunSpeedCalculator();
	private static final CharacterCalculator BASE_WALK_SPEED_CALCULATOR = new BaseWalkSpeedCalculator();

	private static final CharacterCalculator BASE_PHYSICAL_ATTACK_CALCULATOR = new BasePhysicalAttackCalculator();
	private static final CharacterCalculator BASE_PHYSICAL_ATTACK_SPEED_CALCULATOR = new BasePhysicalAttackSpeedCalculator();
	private static final CharacterCalculator BASE_PHYSICAL_CRITICAL_RATE_CALCULATOR = new BasePhysicalCriticalRateCalculator();
	private static final CharacterCalculator BASE_PHYSICAL_DEFENSE_CALCULATOR = new BasePhysicalDefenseCalculator();

	private static final CharacterCalculator BASE_MAGICAL_ATTACK_CALCULATOR = new BaseMagicalAttackCalculator();
	private static final CharacterCalculator BASE_MAGICAL_ATTACK_SPEED_CALCULATOR = new BaseMagicalAttackSpeedCalculator();
	private static final CharacterCalculator BASE_MAGICAL_CRITICAL_RATE_CALCULATOR = new BaseMagicalCriticalRateCalculator();
	private static final CharacterCalculator BASE_MAGICAL_DEFENSE_CALCULATOR = new BaseMagicalDefenseCalculator();

	private static final CharacterCalculator BASE_ATTACK_ACCURACY_CALCULATOR = new BaseAttackAccuracyCalculator();
	private static final CharacterCalculator BASE_ATTACK_EVASION_CALCULATOR = new BaseAttackEvasionCalculator();

	private final L2Character character;
	@SuppressWarnings("unchecked")
	private final Calculator<L2Character>[] calculators = new Calculator[StatType
			.values().length];

	public CharacterStats(L2Character character) {
		this.character = character;
		for (int i = 0; i < calculators.length; i++) {
			calculators[i] = new Calculator<L2Character>();
		}

		// bind default functions
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

	public int getMaxHP() {
		return (int) calc(StatType.MAX_HP);
	}

	public int getMaxMP() {
		return (int) calc(StatType.MAX_MP);
	}

	public int getMaxCP() {
		return (int) calc(StatType.MAX_CP);
	}

	public int getIntelligence() {
		return (int) calc(StatType.STAT_INT);
	}

	public int getStrength() {
		return (int) calc(StatType.STAT_STR);
	}

	public int getConcentration() {
		return (int) calc(StatType.STAT_CON);
	}

	public int getMentality() {
		return (int) calc(StatType.STAT_MEN);
	}

	public int getDexterity() {
		return (int) calc(StatType.STAT_DEX);
	}

	public int getWitness() {
		return (int) calc(StatType.STAT_WIT);
	}

	public int getRunSpeed() {
		return (int) calc(StatType.RUN_SPEED);
	}

	public int getWalkSpeed() {
		return (int) calc(StatType.WALK_SPEED);
	}

	public int getPhysicalAttack() {
		return (int) calc(StatType.POWER_ATTACK);
	}

	public int getPhysicalAttackSpeed() {
		return (int) calc(StatType.POWER_ATTACK_SPEED);
	}

	public int getPhysicalCriticalRate() {
		return (int) calc(StatType.CRITICAL_RATE);
	}

	public int getPhysicalDefense() {
		return (int) calc(StatType.POWER_DEFENSE);
	}

	public int getMagicalAttack() {
		return (int) calc(StatType.MAGIC_ATTACK);
	}

	public int getMagicalAttackSpeed() {
		return (int) calc(StatType.MAGIC_ATTACK_SPEED);
	}

	public int getMagicalCriticalRate() {
		return (int) calc(StatType.MCRITICAL_RATE);
	}

	public int getMagicalDefense() {
		return (int) calc(StatType.MAGIC_DEFENSE);
	}

	public int getAccuracy() {
		return (int) calc(StatType.ACCURACY_COMBAT);
	}

	public int getEvasionRate() {
		return (int) calc(StatType.EVASION_RATE);
	}
	
	public int getMaximumLoad() {
		return (int) calc(StatType.MAX_LOAD);
	}

	// public void add(StatType type, Calculator<?> calculator) {
	// getCalculator(type).importFunctions(calculator);
	// }

	protected Calculator<L2Character> getCalculator(StatType type) {
		return calculators[type.ordinal()];
	}

	public double calc(StatType type) {
		final CharacterCalculatorContext ctx = new CharacterCalculatorContext(
				character);
		getCalculator(type).calculate(character, ctx);
		return ctx.result;
	}
}
