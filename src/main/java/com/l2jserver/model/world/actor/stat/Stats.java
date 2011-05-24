package com.l2jserver.model.world.actor.stat;

import java.util.List;
import java.util.Map;

import com.l2jserver.model.world.Actor;
import com.l2jserver.util.calculator.Calculator;
import com.l2jserver.util.calculator.DivisionFunction;
import com.l2jserver.util.calculator.Function;
import com.l2jserver.util.calculator.MultiplicationFunction;
import com.l2jserver.util.calculator.SetFunction;
import com.l2jserver.util.calculator.SubtractFunction;
import com.l2jserver.util.calculator.SumFunction;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This class handles the stats an can add operations to the calculator
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Stats {
	/**
	 * Attribute types for weapons
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum StatType {
		MAX_HP, MAX_MP, MAX_CP, REGENERATE_HP_RATE, REGENERATE_CP_RATE, REGENERATE_MP_RATE, RECHARGE_MP_RATE, HEAL_EFFECTIVNESS, HEAL_PROFICIENCY, HEAL_STATIC_BONUS,

		// Atk & Def
		POWER_DEFENSE, MAGIC_DEFENSE, POWER_ATTACK, MAGIC_ATTACK, PHYSICAL_SKILL_POWER, POWER_ATTACK_SPEED, MAGIC_ATTACK_SPEED, // how
																																// fast
																																// a
																																// spell
																																// is
																																// casted
		MAGIC_REUSE_RATE, // how fast spells becomes ready to reuse
		SHIELD_DEFENCE, CRITICAL_DAMAGE, CRITICAL_DAMAGE_ADD, // this is another
																// type for
																// special
																// critical
																// damage mods -
																// vicious
																// stance, crit
																// power and
																// crit damage
																// SA
		// it was totally bad since now...
		MAGIC_CRIT_DMG,

		PVP_PHYSICAL_DMG, PVP_MAGICAL_DMG, PVP_PHYS_SKILL_DMG,

		PVP_PHYSICAL_DEF, PVP_MAGICAL_DEF, PVP_PHYS_SKILL_DEF,

		PVE_PHYSICAL_DMG, PVE_PHYS_SKILL_DMG, PVE_BOW_DMG, PVE_BOW_SKILL_DMG, PVE_MAGICAL_DMG,

		// Atk & Def rates
		EVASION_RATE, P_SKILL_EVASION, CRIT_DAMAGE_EVASION, SHIELD_RATE, CRITICAL_RATE, BLOW_RATE, LETHAL_RATE, MCRITICAL_RATE, EXPSP_RATE, ATTACK_CANCEL,

		// Accuracy and range
		ACCURACY_COMBAT, POWER_ATTACK_RANGE, MAGIC_ATTACK_RANGE, POWER_ATTACK_ANGLE, ATTACK_COUNT_MAX,
		// Run speed,
		// walk & escape speed are calculated proportionally,
		// magic speed is a buff
		RUN_SPEED, WALK_SPEED,

		//
		// Player-only stats
		//
		STAT_STR, STAT_CON, STAT_DEX, STAT_INT, STAT_WIT, STAT_MEN,

		//
		// Special stats, share one slot in Calculator
		//

		// stats of various abilities
		BREATH, FALL, LIMIT_HP, // non-displayed hp limit
		//
		AGGRESSION, // locks a mob on tank caster
		BLEED, // by daggers, like poison
		POISON, // by magic, hp dmg over time
		STUN, // disable move/ATTACK for a period of time
		ROOT, // disable movement, but not ATTACK
		MOVEMENT, // slowdown movement, debuff
		CONFUSION, // mob changes target, opposite to aggression/hate
		SLEEP, // sleep until attacked
		VALAKAS, VALAKAS_RES,
		//
		AGGRESSION_VULN, BLEED_VULN, POISON_VULN, STUN_VULN, PARALYZE_VULN, ROOT_VULN, SLEEP_VULN, CONFUSION_VULN, MOVEMENT_VULN, FIRE_RES, WIND_RES, WATER_RES, EARTH_RES, HOLY_RES, DARK_RES,
		// Skills Power
		FIRE_POWER, WATER_POWER, WIND_POWER, EARTH_POWER, HOLY_POWER, DARK_POWER, CANCEL_VULN, // Resistance
																								// for
																								// cancel
																								// type
																								// skills
		DERANGEMENT_VULN, DEBUFF_VULN, BUFF_VULN, CRIT_VULN, // Resistence to
																// Crit DMG in
																// percent.
		CRIT_ADD_VULN, // Resistence to Crit DMG in value .
		MAGIC_DAMAGE_VULN, MAGIC_SUCCESS_RES, MAGIC_FAILURE_RATE,

		AGGRESSION_PROF, BLEED_PROF, POISON_PROF, STUN_PROF, PARALYZE_PROF, ROOT_PROF, SLEEP_PROF, CONFUSION_PROF, PROF, CANCEL_PROF, DERANGEMENT_PROF, DEBUFF_PROF, CRIT_PROF,

		NONE_WPN_VULN, // Shields!!!
		SWORD_WPN_VULN, BLUNT_WPN_VULN, DAGGER_WPN_VULN, BOW_WPN_VULN, CROSSBOW_WPN_VULN, POLE_WPN_VULN, ETC_WPN_VULN, FIST_WPN_VULN, DUAL_WPN_VULN, DUALFIST_WPN_VULN, BIGSWORD_WPN_VULN, BIGBLUNT_WPN_VULN, DUALDAGGER_WPN_VULN, RAPIER_WPN_VULN, ANCIENT_WPN_VULN, PET_WPN_VULN,

		REFLECT_DAMAGE_PERCENT, REFLECT_SKILL_MAGIC, REFLECT_SKILL_PHYSIC, VENGEANCE_SKILL_MAGIC_DAMAGE, VENGEANCE_SKILL_PHYSICAL_DAMAGE, ABSORB_DAMAGE_PERCENT, TRANSFER_DAMAGE_PERCENT, ABSORB_MANA_DAMAGE_PERCENT,

		MAX_LOAD, WEIGHT_LIMIT,

		PATK_PLANTS, PATK_INSECTS, PATK_ANIMALS, PATK_MONSTERS, PATK_DRAGONS, PATK_GIANTS, PATK_MCREATURES,

		PDEF_PLANTS, PDEF_INSECTS, PDEF_ANIMALS, PDEF_MONSTERS, PDEF_DRAGONS, PDEF_GIANTS, PDEF_MCREATURES,

		ATK_REUSE, P_REUSE,

		// ExSkill :)
		INV_LIM, WH_LIM, FREIGHT_LIM, P_SELL_LIM, P_BUY_LIM, REC_D_LIM, REC_C_LIM,

		// C4 Stats
		PHYSICAL_MP_CONSUME_RATE, MAGICAL_MP_CONSUME_RATE, DANCE_MP_CONSUME_RATE, BOW_MP_CONSUME_RATE, HP_CONSUME_RATE, MP_CONSUME, SOULSHOT_COUNT,

		// T1 stats
		transformId, TALISMAN_SLOTS, CLOAK_SLOT,

		// Shield Stats
		SHIELD_DEFENCE_ANGLE,

		// Skill mastery
		SKILL_MASTERY,

		// vitality
		VITALITY_CONSUME_RATE;

		// PHYSICAL_ATTACK, MAGICAL_ATTACK, CRITICAL_RATE,
		// PHYSICAL_ATTACK_SPEED;
	}

	private final Map<StatType, List<Function<Actor>>> operations = CollectionFactory
			.newMap();

	/**
	 * Sets the result of an calculator
	 * 
	 * @param type
	 *            the attribute type
	 * @param order
	 *            the calculation order
	 * @param value
	 *            the value to set
	 */
	public void set(Stats.StatType type, int order, double value) {
		func(type, new SetFunction<Actor>(order, value));
	}

	/**
	 * Adds <tt>value</tt> to the result of an calculator
	 * 
	 * @param type
	 *            the attribute type
	 * @param order
	 *            the calculation order
	 * @param value
	 *            the value to be summed
	 */
	public void add(Stats.StatType type, int order, double value) {
		func(type, new SumFunction<Actor>(order, value));
	}

	/**
	 * Subtracts <tt>value</tt> from the result of an calculator
	 * 
	 * @param type
	 *            the attribute type
	 * @param order
	 *            the calculation order
	 * @param value
	 *            the value to be subtracted
	 */
	public void sub(Stats.StatType type, int order, double value) {
		func(type, new SubtractFunction<Actor>(order, value));
	}

	/**
	 * Multiply <tt>value</tt> the result of an calculator
	 * 
	 * @param type
	 *            the attribute type
	 * @param order
	 *            the calculation order
	 * @param value
	 *            the value to be multiplied
	 */
	public void mult(Stats.StatType type, int order, double value) {
		func(type, new MultiplicationFunction<Actor>(order, value));
	}

	/**
	 * Divides by <tt>value</tt> the result of an calculator
	 * 
	 * @param type
	 *            the attribute type
	 * @param order
	 *            the calculation order
	 * @param value
	 *            the value to be divided by
	 */
	public void div(Stats.StatType type, int order, double value) {
		func(type, new DivisionFunction<Actor>(order, value));
	}

	/**
	 * Performs an enchant operation. Note that this is not implemented yet!
	 * 
	 * @param type
	 *            the attribute type
	 * @param order
	 *            the calculation order
	 * @param value
	 *            TODO
	 */
	public void enchant(Stats.StatType type, int order, double value) {
		// TODO enchant operation for weapon
	}

	public void func(StatType type, Function<Actor> function) {
		getMap(type).add(function);
	}

	/**
	 * Returns the Order-Operation map for <tt>type</tt>
	 * 
	 * @param type
	 *            the type
	 * @return the order-operation map
	 */
	private List<Function<Actor>> getMap(Stats.StatType type) {
		List<Function<Actor>> list = operations.get(type);
		if (list == null) {
			list = CollectionFactory.newList();
			operations.put(type, list);
		}
		return list;
	}

	/**
	 * Creates the calculator object for this weapon
	 * 
	 * @param type
	 *            the type
	 * @param calculator
	 *            the calculator
	 */
	public void calculator(Stats.StatType type, Calculator<Actor> calculator) {
		final List<Function<Actor>> operations = this.operations.get(type);
		if (operations == null || operations.size() == 0)
			return;
		for (final Function<Actor> func : operations) {
			calculator.add(func);
		}
	}
}