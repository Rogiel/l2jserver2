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

import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.actor.calculator.ActorCalculator;
import com.l2jserver.model.world.actor.stat.ActorStats;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.character.calculator.CharacterCalculator;
import com.l2jserver.model.world.character.calculator.CharacterCalculatorContext;
import com.l2jserver.model.world.character.calculator.CharacterFormula;
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

/**
 * This class is responsible for calculating the real character stats. The real
 * stats vary from the values from the templates, also, skills and items
 * equipped can change those values. Once an buff is applied, a new calculator
 * is {@link CharacterCalculator#add(com.l2jserver.util.calculator.Function...)
 * imported} and their functions are added to this class calculator. Once the
 * skill effect has past away, all the functions that were imported are now
 * {@link CharacterCalculator#remove(com.l2jserver.util.calculator.Function)
 * removed} and the calculator return to its original state.
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
	private static final CharacterFormula BASE_HP_FORMULA = new CharacterBaseHPCalculator();
	/**
	 * The calculator for base maximum MP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_MP_FORMULA = new CharacterBaseMPCalculator();
	/**
	 * The calculator for base maximum CP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_CP_FORMULA = new CharacterBaseCPCalculator();

	/**
	 * The calculator for base intelligence
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_INT_FORMULA = new CharacterBaseIntelligenceCalculator();
	/**
	 * The calculator for base strength
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_STR_FORMULA = new CharacterBaseStrengthCalculator();
	/**
	 * The calculator for base concentration
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_CON_FORMULA = new CharacterBaseConcentrationCalculator();
	/**
	 * The calculator for base mentality
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_MEN_FORMULA = new CharacterBaseMentalityCalculator();
	/**
	 * The calculator for base dexterity
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_DEX_FORMULA = new CharacterBaseDexterityCalculator();
	/**
	 * The calculator for base witness
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_WIT_FORMULA = new CharacterBaseWitnessCalculator();

	/**
	 * The calculator for base run speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_RUN_SPEED_FORMULA = new CharacterBaseRunSpeedCalculator();
	/**
	 * The calculator for base walk speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_WALK_SPEED_FORMULA = new CharacterBaseWalkSpeedCalculator();

	/**
	 * The calculator base physical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_PHYSICAL_ATTACK_FORMULA = new CharacterBasePhysicalAttackCalculator();
	/**
	 * The calculator base physical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_PHYSICAL_ATTACK_SPEED_FORMULA = new CharacterBasePhysicalAttackSpeedCalculator();
	/**
	 * The calculator base physical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_PHYSICAL_CRITICAL_RATE_FORMULA = new CharacterBasePhysicalCriticalRateCalculator();
	/**
	 * The calculator base physical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_PHYSICAL_DEFENSE_FORMULA = new CharacterBasePhysicalDefenseCalculator();

	/**
	 * The calculator base magical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_MAGICAL_ATTACK_FORMULA = new CharacterBaseMagicalAttackCalculator();
	/**
	 * The calculator base magical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_MAGICAL_ATTACK_SPEED_FORMULA = new CharacterBaseMagicalAttackSpeedCalculator();
	/**
	 * The calculator base magical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_MAGICAL_CRITICAL_RATE_FORMULA = new CharacterBaseMagicalCriticalRateCalculator();
	/**
	 * The calculator base magical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_MAGICAL_DEFENSE_FORMULA = new CharacterBaseMagicalDefenseCalculator();

	/**
	 * The calculator base attack accuracy
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_ATTACK_ACCURACY_FORMULA = new CharacterBaseAttackAccuracyCalculator();
	/**
	 * The calculator base evasion
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula BASE_ATTACK_EVASION_FORMULA = new CharacterBaseAttackEvasionCalculator();

	// BONUS
	/**
	 * The calculator for CP bonus
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula CP_BONUS_FORMULA = new MaximumCPBonusCalculator();

	// ADD
	/**
	 * The calculator for HP add
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula HP_ADD_FORMULA = new MaximumHPAddCalculator();
	/**
	 * The calculator for MP add
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula MP_ADD_FORMULA = new MaximumMPAddCalculator();
	/**
	 * The calculator for CP add
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final CharacterFormula CP_ADD_FORMULA = new MaximumCPAddCalculator();

	/**
	 * The character
	 */
	private final L2Character character;

	@SuppressWarnings("unchecked")
	private static final CharacterCalculator calculator = new CharacterCalculator();

	/**
	 * Creates a new {@link CharacterStats} and adds default calculators
	 * 
	 * @param character
	 *            the character
	 */
	public CharacterStats(L2Character character) {
		super();
		this.character = character;
		setup();
	}

	@Override
	protected ActorCalculator getCalculator() {
		return calculator;
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

	@SuppressWarnings("unchecked")
	private void setup() {
		calculator.addNoSort(BASE_HP_FORMULA, BASE_MP_FORMULA, BASE_CP_FORMULA);

		calculator.addNoSort(BASE_INT_FORMULA, BASE_STR_FORMULA,
				BASE_CON_FORMULA, BASE_MEN_FORMULA, BASE_DEX_FORMULA,
				BASE_WIT_FORMULA);

		calculator.addNoSort(BASE_RUN_SPEED_FORMULA, BASE_WALK_SPEED_FORMULA);

		calculator.addNoSort(BASE_PHYSICAL_ATTACK_FORMULA,
				BASE_PHYSICAL_ATTACK_SPEED_FORMULA,
				BASE_PHYSICAL_CRITICAL_RATE_FORMULA,
				BASE_PHYSICAL_DEFENSE_FORMULA);

		calculator.addNoSort(BASE_MAGICAL_ATTACK_FORMULA,
				BASE_MAGICAL_ATTACK_SPEED_FORMULA,
				BASE_MAGICAL_CRITICAL_RATE_FORMULA,
				BASE_MAGICAL_DEFENSE_FORMULA);

		calculator.addNoSort(BASE_ATTACK_ACCURACY_FORMULA,
				BASE_ATTACK_EVASION_FORMULA);

		// add hp/mp/cp add functions
		calculator.addNoSort(HP_ADD_FORMULA, MP_ADD_FORMULA, CP_ADD_FORMULA);

		// bonus
		calculator.addNoSort(CP_BONUS_FORMULA);

		addTo(calculator);
		calculator.sort();
	}

	public void updateCalculator() {
		calculator.clear();
		setup();
		addItem(InventoryPaperdoll.RIGHT_HAND);
	}

	private void addItem(InventoryPaperdoll paperdoll) {
		if (!character.getInventory().has(paperdoll))
			return;
		final ItemTemplate item = character.getInventory().getItem(paperdoll)
				.getTemplate();
		calculator.add(item.getPhysicalDamage());
		calculator.add(item.getMagicalDamage());
	}

	@Override
	protected CharacterCalculatorContext createContext() {
		return new CharacterCalculatorContext(character);
	}
}
