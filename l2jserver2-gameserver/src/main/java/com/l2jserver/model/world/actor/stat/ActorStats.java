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
package com.l2jserver.model.world.actor.stat;

import com.l2jserver.model.world.actor.calculator.ActorCalculator;
import com.l2jserver.model.world.actor.calculator.ActorCalculatorContext;
import com.l2jserver.model.world.actor.calculator.ActorFormula;
import com.l2jserver.model.world.actor.calculator.AttackAccuracyBonusCalculator;
import com.l2jserver.model.world.actor.calculator.AttackEvasionBonusCalculator;
import com.l2jserver.model.world.actor.calculator.MagicalAttackBonusCalculator;
import com.l2jserver.model.world.actor.calculator.MagicalAttackSpeedBonusCalculator;
import com.l2jserver.model.world.actor.calculator.MagicalCriticalRateBonusCalculator;
import com.l2jserver.model.world.actor.calculator.MagicalDefenseBonusCalculator;
import com.l2jserver.model.world.actor.calculator.MaximumHPBonusCalculator;
import com.l2jserver.model.world.actor.calculator.MaximumMPBonusCalculator;
import com.l2jserver.model.world.actor.calculator.PhysicalAttackBonusCalculator;
import com.l2jserver.model.world.actor.calculator.PhysicalAttackSpeedBonusCalculator;
import com.l2jserver.model.world.actor.calculator.PhysicalCriticalRateBonusCalculator;
import com.l2jserver.model.world.actor.calculator.PhysicalDefenseBonusCalculator;
import com.l2jserver.model.world.actor.calculator.RunSpeedBonusCalculator;
import com.l2jserver.model.world.actor.calculator.WalkSpeedBonusCalculator;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the {@link ActorCalculatorContext} type
 */
public abstract class ActorStats<T extends ActorCalculatorContext> {
	// bonuses
	/**
	 * The calculator for base maximum HP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula HP_BONUS_FORMULA = new MaximumHPBonusCalculator();
	/**
	 * The calculator for base maximum HP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula MP_BONUS_FORMULA = new MaximumMPBonusCalculator();
	/**
	 * The calculator for run speed bonus
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula RUN_SPEED_BONUS_FORMULA = new RunSpeedBonusCalculator();
	/**
	 * The calculator for walk speed bonus
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula WALK_SPEED_BONUS_FORMULA = new WalkSpeedBonusCalculator();

	/**
	 * The calculator base physical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula PHYSICAL_ATTACK_BONUS_FORMULA = new PhysicalAttackBonusCalculator();
	/**
	 * The calculator base physical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula PHYSICAL_ATTACK_SPEED_BONUS_FORMULA = new PhysicalAttackSpeedBonusCalculator();
	/**
	 * The calculator base physical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula PHYSICAL_CRITICAL_RATE_BONUS_FORMULA = new PhysicalCriticalRateBonusCalculator();
	/**
	 * The calculator base physical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula PHYSICAL_DEFENSE_BONUS_FORMULA = new PhysicalDefenseBonusCalculator();

	/**
	 * The calculator base magical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula MAGICAL_ATTACK_BONUS_FORMULA = new MagicalAttackBonusCalculator();
	/**
	 * The calculator base magical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula MAGICAL_ATTACK_SPEED_BONUS_FORMULA = new MagicalAttackSpeedBonusCalculator();
	/**
	 * The calculator base magical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula MAGICAL_CRITICAL_RATE_BONUS_FORMULA = new MagicalCriticalRateBonusCalculator();
	/**
	 * The calculator base magical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula MAGICAL_DEFENSE_BONUS_FORMULA = new MagicalDefenseBonusCalculator();

	/**
	 * The calculator base attack accuracy
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula ATTACK_ACCURACY_BONUS_FORMULA = new AttackAccuracyBonusCalculator();
	/**
	 * The calculator base evasion
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorFormula ATTACK_EVASION_BONUS_FORMULA = new AttackEvasionBonusCalculator();

	/**
	 * Adds default formulas to the <tt>calculator</tt>
	 * 
	 * @param calculator
	 *            the calculator
	 */
	protected void addTo(ActorCalculator calculator) {
		calculator.addNoSort(HP_BONUS_FORMULA, MP_BONUS_FORMULA,
				RUN_SPEED_BONUS_FORMULA, WALK_SPEED_BONUS_FORMULA,
				PHYSICAL_ATTACK_BONUS_FORMULA,
				PHYSICAL_ATTACK_SPEED_BONUS_FORMULA,
				PHYSICAL_CRITICAL_RATE_BONUS_FORMULA,
				PHYSICAL_DEFENSE_BONUS_FORMULA, MAGICAL_ATTACK_BONUS_FORMULA,
				MAGICAL_ATTACK_SPEED_BONUS_FORMULA,
				MAGICAL_CRITICAL_RATE_BONUS_FORMULA,
				MAGICAL_DEFENSE_BONUS_FORMULA, ATTACK_ACCURACY_BONUS_FORMULA,
				ATTACK_EVASION_BONUS_FORMULA);
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

	// public void add(StatType type, ActorCalculator calculator) {
	// if (calculator == null)
	// return;
	// getCalculator(type).importFunctions(calculator);
	// }
	//
	// public void remove(StatType type, ActorCalculator calculator) {
	// getCalculator(type).removeFunctions(calculator);
	// }

	/**
	 * Does the calculation of an given {@link StatType}
	 * 
	 * @param type
	 *            the type
	 * @return the value calculated
	 */
	protected double calc(StatType type) {
		final T ctx = createContext();
		return getCalculator().calculate(type, ctx);
	}

	/**
	 * @return the calculator
	 */
	protected abstract ActorCalculator getCalculator();

	/**
	 * @return the new context
	 */
	protected abstract T createContext();
}
