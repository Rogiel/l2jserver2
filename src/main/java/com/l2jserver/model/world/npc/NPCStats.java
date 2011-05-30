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
package com.l2jserver.model.world.npc;

import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.actor.stat.ActorStats;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.model.world.npc.calculator.NPCCalculator;
import com.l2jserver.model.world.npc.calculator.NPCCalculatorContext;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseAttackEvasionCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseConcentrationCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseDexterityCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseHPCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseIntelligenceCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseMPCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseMagicalAttackCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseMagicalAttackSpeedCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseMagicalCriticalRateCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseMagicalDefenseCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseMentalityCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBasePhysicalAttackCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBasePhysicalAttackSpeedCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBasePhysicalCriticalRateCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBasePhysicalDefenseCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseRunSpeedCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseStrengthCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseWalkSpeedCalculator;
import com.l2jserver.model.world.npc.calculator.base.NPCBaseWitnessCalculator;
import com.l2jserver.util.calculator.SimpleCalculator;

/**
 * This class is responsible for calculating the real NPC stats. The real stats
 * vary from the values from the templates, also, skills and items equipped can
 * change those values. Once an buff is applied, a new calculator is
 * {@link SimpleCalculator#importFunctions(SimpleCalculator) imported} and their functions
 * are added to this class calculator. Once the skill effect has past away, all
 * the functions that were imported are now
 * {@link SimpleCalculator#removeFunctions(SimpleCalculator) removed} and the calculator
 * return to its original state.
 * <p>
 * Another important note is that calculators should perform calculations as
 * fast as possible.
 * <p>
 * <i><b>IMPORTANT</b>: NEVER TOUCH THE STATIC CALCULATORS!</i>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCStats extends ActorStats<NPCCalculatorContext> {
	/**
	 * The calculator for base maximum MP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_HP_CALCULATOR = new NPCBaseHPCalculator();
	/**
	 * The calculator for base maximum MP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_MP_CALCULATOR = new NPCBaseMPCalculator();

	/**
	 * The calculator for base intelligence
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_INT_CALCULATOR = new NPCBaseIntelligenceCalculator();
	/**
	 * The calculator for base strength
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_STR_CALCULATOR = new NPCBaseStrengthCalculator();
	/**
	 * The calculator for base concentration
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_CON_CALCULATOR = new NPCBaseConcentrationCalculator();
	/**
	 * The calculator for base mentality
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_MEN_CALCULATOR = new NPCBaseMentalityCalculator();
	/**
	 * The calculator for base dexterity
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_DEX_CALCULATOR = new NPCBaseDexterityCalculator();
	/**
	 * The calculator for base witness
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_WIT_CALCULATOR = new NPCBaseWitnessCalculator();

	/**
	 * The calculator for base run speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_RUN_SPEED_CALCULATOR = new NPCBaseRunSpeedCalculator();
	/**
	 * The calculator for base walk speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_WALK_SPEED_CALCULATOR = new NPCBaseWalkSpeedCalculator();

	/**
	 * The calculator base physical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_PHYSICAL_ATTACK_CALCULATOR = new NPCBasePhysicalAttackCalculator();
	/**
	 * The calculator base physical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_PHYSICAL_ATTACK_SPEED_CALCULATOR = new NPCBasePhysicalAttackSpeedCalculator();
	/**
	 * The calculator base physical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_PHYSICAL_CRITICAL_RATE_CALCULATOR = new NPCBasePhysicalCriticalRateCalculator();
	/**
	 * The calculator base physical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_PHYSICAL_DEFENSE_CALCULATOR = new NPCBasePhysicalDefenseCalculator();

	/**
	 * The calculator base magical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_MAGICAL_ATTACK_CALCULATOR = new NPCBaseMagicalAttackCalculator();
	/**
	 * The calculator base magical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_MAGICAL_ATTACK_SPEED_CALCULATOR = new NPCBaseMagicalAttackSpeedCalculator();
	/**
	 * The calculator base magical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_MAGICAL_CRITICAL_RATE_CALCULATOR = new NPCBaseMagicalCriticalRateCalculator();
	/**
	 * The calculator base magical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_MAGICAL_DEFENSE_CALCULATOR = new NPCBaseMagicalDefenseCalculator();

	/**
	 * The calculator base evasion
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCCalculator BASE_ATTACK_EVASION_CALCULATOR = new NPCBaseAttackEvasionCalculator();

	/**
	 * The NPC
	 */
	private final NPC npc;

	/**
	 * Creates a new {@link NPCStats} and adds default calculators
	 * 
	 * @param npc
	 *            the npc
	 */
	public NPCStats(NPC npc) {
		super();
		this.npc = npc;

		add(StatType.MAX_HP, BASE_HP_CALCULATOR);
		add(StatType.MAX_MP, BASE_MP_CALCULATOR);

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

		add(StatType.EVASION_RATE, BASE_ATTACK_EVASION_CALCULATOR);
	}

	@Override
	protected NPCCalculatorContext createContext() {
		return new NPCCalculatorContext(npc);
	}
}
