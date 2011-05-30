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
import com.l2jserver.util.calculator.SimpleCalculator;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public abstract class ActorStats<T extends ActorCalculatorContext> {
	// bonuses
	/**
	 * The calculator for base maximum HP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator HP_BONUS_CALCULATOR = new MaximumHPBonusCalculator();
	/**
	 * The calculator for base maximum HP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator MP_BONUS_CALCULATOR = new MaximumMPBonusCalculator();
	/**
	 * The calculator for run speed bonus
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator RUN_SPEED_BONUS_CALCULATOR = new RunSpeedBonusCalculator();
	/**
	 * The calculator for walk speed bonus
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator WALK_SPEED_BONUS_CALCULATOR = new WalkSpeedBonusCalculator();

	/**
	 * The calculator base physical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator PHYSICAL_ATTACK_BONUS_CALCULATOR = new PhysicalAttackBonusCalculator();
	/**
	 * The calculator base physical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator PHYSICAL_ATTACK_SPEED_BONUS_CALCULATOR = new PhysicalAttackSpeedBonusCalculator();
	/**
	 * The calculator base physical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator PHYSICAL_CRITICAL_RATE_BONUS_CALCULATOR = new PhysicalCriticalRateBonusCalculator();
	/**
	 * The calculator base physical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator PHYSICAL_DEFENSE_BONUS_CALCULATOR = new PhysicalDefenseBonusCalculator();

	/**
	 * The calculator base magical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator MAGICAL_ATTACK_BONUS_CALCULATOR = new MagicalAttackBonusCalculator();
	/**
	 * The calculator base magical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator MAGICAL_ATTACK_SPEED_BONUS_CALCULATOR = new MagicalAttackSpeedBonusCalculator();
	/**
	 * The calculator base magical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator MAGICAL_CRITICAL_RATE_BONUS_CALCULATOR = new MagicalCriticalRateBonusCalculator();
	/**
	 * The calculator base magical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator MAGICAL_DEFENSE_BONUS_CALCULATOR = new MagicalDefenseBonusCalculator();

	/**
	 * The calculator base attack accuracy
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator ATTACK_ACCURACY_BONUS_CALCULATOR = new AttackAccuracyBonusCalculator();
	/**
	 * The calculator base evasion
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final ActorCalculator ATTACK_EVASION_BONUS_CALCULATOR = new AttackEvasionBonusCalculator();

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
	private final SimpleCalculator<T>[] calculators = new SimpleCalculator[StatType
			.values().length];

	public ActorStats() {
		for (int i = 0; i < calculators.length; i++) {
			calculators[i] = new SimpleCalculator<T>();
		}

		// bonuses
		add(StatType.MAX_HP, HP_BONUS_CALCULATOR);
		add(StatType.MAX_MP, MP_BONUS_CALCULATOR);

		add(StatType.RUN_SPEED, RUN_SPEED_BONUS_CALCULATOR);
		add(StatType.WALK_SPEED, WALK_SPEED_BONUS_CALCULATOR);

		add(StatType.POWER_ATTACK, PHYSICAL_ATTACK_BONUS_CALCULATOR);
		add(StatType.POWER_ATTACK_SPEED, PHYSICAL_ATTACK_SPEED_BONUS_CALCULATOR);
		add(StatType.CRITICAL_RATE, PHYSICAL_CRITICAL_RATE_BONUS_CALCULATOR);
		add(StatType.POWER_DEFENSE, PHYSICAL_DEFENSE_BONUS_CALCULATOR);

		add(StatType.MAGIC_ATTACK, MAGICAL_ATTACK_BONUS_CALCULATOR);
		add(StatType.MAGIC_ATTACK_SPEED, MAGICAL_ATTACK_SPEED_BONUS_CALCULATOR);
		add(StatType.MCRITICAL_RATE, MAGICAL_CRITICAL_RATE_BONUS_CALCULATOR);
		add(StatType.MAGIC_DEFENSE, MAGICAL_DEFENSE_BONUS_CALCULATOR);

		add(StatType.ACCURACY_COMBAT, ATTACK_ACCURACY_BONUS_CALCULATOR);
		add(StatType.EVASION_RATE, ATTACK_EVASION_BONUS_CALCULATOR);
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

	public void add(StatType type, ActorCalculator calculator) {
		getCalculator(type).importFunctions(calculator);
	}

	public void remove(StatType type, ActorCalculator calculator) {
		getCalculator(type).removeFunctions(calculator);
	}

	/**
	 * @param the
	 *            calculator {@link StatType}
	 * @return the calculator object associated with the given <tt>type</tt>
	 */
	protected SimpleCalculator<T> getCalculator(StatType type) {
		return calculators[type.ordinal()];
	}

	/**
	 * Does the calculation of an given {@link StatType}
	 * 
	 * @param type
	 *            the type
	 * @return the value calculated
	 */
	protected double calc(StatType type) {
		final T ctx = createContext();
		return getCalculator(type).calculate(ctx);
	}

	public void updateCalculators() {
		
	}
	
	protected abstract T createContext();
}
