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
import com.l2jserver.model.world.actor.calculator.ActorCalculator;
import com.l2jserver.model.world.actor.stat.ActorStats;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.model.world.npc.calculator.NPCCalculator;
import com.l2jserver.model.world.npc.calculator.NPCCalculatorContext;
import com.l2jserver.model.world.npc.calculator.NPCFormula;
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
 * {@link SimpleCalculator#importFunctions(SimpleCalculator) imported} and their
 * functions are added to this class calculator. Once the skill effect has past
 * away, all the functions that were imported are now
 * {@link SimpleCalculator#removeFunctions(SimpleCalculator) removed} and the
 * calculator return to its original state.
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
	private static final NPCFormula BASE_HP_FORMULA = new NPCBaseHPCalculator();
	/**
	 * The calculator for base maximum MP
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_MP_FORMULA = new NPCBaseMPCalculator();

	/**
	 * The calculator for base intelligence
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_INT_FORMULA = new NPCBaseIntelligenceCalculator();
	/**
	 * The calculator for base strength
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_STR_FORMULA = new NPCBaseStrengthCalculator();
	/**
	 * The calculator for base concentration
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_CON_FORMULA = new NPCBaseConcentrationCalculator();
	/**
	 * The calculator for base mentality
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_MEN_FORMULA = new NPCBaseMentalityCalculator();
	/**
	 * The calculator for base dexterity
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_DEX_FORMULA = new NPCBaseDexterityCalculator();
	/**
	 * The calculator for base witness
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_WIT_FORMULA = new NPCBaseWitnessCalculator();

	/**
	 * The calculator for base run speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_RUN_SPEED_FORMULA = new NPCBaseRunSpeedCalculator();
	/**
	 * The calculator for base walk speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_WALK_SPEED_FORMULA = new NPCBaseWalkSpeedCalculator();

	/**
	 * The calculator base physical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_PHYSICAL_ATTACK_FORMULA = new NPCBasePhysicalAttackCalculator();
	/**
	 * The calculator base physical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_PHYSICAL_ATTACK_SPEED_FORMULA = new NPCBasePhysicalAttackSpeedCalculator();
	/**
	 * The calculator base physical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_PHYSICAL_CRITICAL_RATE_FORMULA = new NPCBasePhysicalCriticalRateCalculator();
	/**
	 * The calculator base physical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_PHYSICAL_DEFENSE_FORMULA = new NPCBasePhysicalDefenseCalculator();

	/**
	 * The calculator base magical attack
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_MAGICAL_ATTACK_FORMULA = new NPCBaseMagicalAttackCalculator();
	/**
	 * The calculator base magical attack speed
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_MAGICAL_ATTACK_SPEED_FORMULA = new NPCBaseMagicalAttackSpeedCalculator();
	/**
	 * The calculator base magical attack critical rate
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_MAGICAL_CRITICAL_RATE_FORMULA = new NPCBaseMagicalCriticalRateCalculator();
	/**
	 * The calculator base magical defense
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_MAGICAL_DEFENSE_FORMULA = new NPCBaseMagicalDefenseCalculator();

	/**
	 * The calculator base evasion
	 * <p>
	 * <u>This calculator does not store any state and thus is safe to be
	 * shared.</u>
	 */
	private static final NPCFormula BASE_ATTACK_EVASION_FORMULA = new NPCBaseAttackEvasionCalculator();

	/**
	 * The NPC
	 */
	private final NPC npc;

	private static final NPCCalculator calculator = new NPCCalculator(
			StatType.class);

	/**
	 * Creates a new {@link NPCStats} and adds default calculators
	 * 
	 * @param npc
	 *            the npc
	 */
	@SuppressWarnings("unchecked")
	public NPCStats(NPC npc) {
		super();
		this.npc = npc;

		calculator.addNoSort(BASE_HP_FORMULA, BASE_MP_FORMULA);

		calculator.addNoSort(BASE_INT_FORMULA, BASE_STR_FORMULA,
				BASE_CON_FORMULA, BASE_MEN_FORMULA, BASE_DEX_FORMULA,
				BASE_WIT_FORMULA);

		calculator.addNoSort(BASE_RUN_SPEED_FORMULA,
				BASE_WALK_SPEED_FORMULA);

		calculator.addNoSort(BASE_PHYSICAL_ATTACK_FORMULA,
				BASE_PHYSICAL_ATTACK_SPEED_FORMULA,
				BASE_PHYSICAL_CRITICAL_RATE_FORMULA,
				BASE_PHYSICAL_DEFENSE_FORMULA);

		calculator.addNoSort(BASE_MAGICAL_ATTACK_FORMULA,
				BASE_MAGICAL_ATTACK_SPEED_FORMULA,
				BASE_MAGICAL_CRITICAL_RATE_FORMULA,
				BASE_MAGICAL_DEFENSE_FORMULA);

		calculator.addNoSort(BASE_ATTACK_EVASION_FORMULA);
	}

	@Override
	protected NPCCalculatorContext createContext() {
		return new NPCCalculatorContext(npc);
	}

	@Override
	protected ActorCalculator getCalculator() {
		return calculator;
	}
}
