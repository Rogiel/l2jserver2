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
package com.l2jserver.model.server.attack;

import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Actor;

/**
 * Calculator used to calculate physical damage on each hit.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PhysicalAttackCalculator extends AttackCalculator {
	public PhysicalAttackCalculator() {
		super(new AttackCalculatorFunction(0x000, AttackCalculatorType.DAMAGE) {
			@Override
			public double calculate(Actor attacker, Actor target, double value) {
//				// final boolean isPvP = (attacker instanceof L2Playable) &&
//				// (target instanceof L2Playable);
//				// TODO soulshot charge
//				boolean soulshot = false;
//
//				double damage = attacker.getStats().getPhysicalAttack();
//				double defense = target.getStats().getPhysicalDefense();
//				// damage += calcValakasAttribute(attacker, target, skill);
//				// if (attacker instanceof NPC) {
//				// if (((NPC) attacker)._soulshotcharged) {
//				// ss = true;
//				// } else
//				// ss = false;
//				// ((L2Npc) attacker)._soulshotcharged = false;
//				// }
//				// TODO implement pvp
//				// Def bonusses in PvP fight
//				// if (isPvP) {
//				// if (skill == null)
//				// defence *= target.calcStat(Stats.PVP_PHYSICAL_DEF, 1,
//				// null, null);
//				// else
//				// defence *= target.calcStat(Stats.PVP_PHYS_SKILL_DEF, 1,
//				// null, null);
//				// }
//
//				// TODO implement shield
//				// switch (shld) {
//				// case SHIELD_DEFENSE_SUCCEED:
//				// if (!Config.ALT_GAME_SHIELD_BLOCKS)
//				// defense += target.getShldDef();
//				// break;
//				// case SHIELD_DEFENSE_PERFECT_BLOCK: // perfect block
//				// return 1.;
//				// }
//
//				if (soulshot)
//					damage *= 2;
//				// if (skill != null) {
//				// double skillpower = skill.getPower(attacker, target, isPvP);
//				// float ssboost = skill.getSSBoost();
//				// if (ssboost <= 0)
//				// damage += skillpower;
//				// else if (ssboost > 0) {
//				// if (ss) {
//				// skillpower *= ssboost;
//				// damage += skillpower;
//				// } else
//				// damage += skillpower;
//				// }
//				// }
//
//				// defense modifier depending of the attacker weapon
//				//ItemTemplate weapon = attacker.getActiveWeaponItem();
//				boolean isBow = false;
//				if (weapon != null/* && !attacker.isTransformed() */) {
//					switch (null) {
//					case BOW:
//						isBow = true;
//						stat = StatType.BOW_WPN_VULN;
//						break;
//					case CROSSBOW:
//						isBow = true;
//						stat = StatType.CROSSBOW_WPN_VULN;
//						break;
//					case BLUNT:
//						stat = StatType.BLUNT_WPN_VULN;
//						break;
//					case DAGGER:
//						stat = StatType.DAGGER_WPN_VULN;
//						break;
//					case DUAL:
//						stat = StatType.DUAL_WPN_VULN;
//						break;
//					case DUALFIST:
//						stat = StatType.DUALFIST_WPN_VULN;
//						break;
//					case ETC:
//						stat = StatType.ETC_WPN_VULN;
//						break;
//					case FIST:
//						stat = StatType.FIST_WPN_VULN;
//						break;
//					case POLE:
//						stat = StatType.POLE_WPN_VULN;
//						break;
//					case SWORD:
//						stat = StatType.SWORD_WPN_VULN;
//						break;
//					case BIGSWORD:
//						stat = StatType.BIGSWORD_WPN_VULN;
//						break;
//					case BIGBLUNT:
//						stat = StatType.BIGBLUNT_WPN_VULN;
//						break;
//					case DUALDAGGER:
//						stat = StatType.DUALDAGGER_WPN_VULN;
//						break;
//					case RAPIER:
//						stat = StatType.RAPIER_WPN_VULN;
//						break;
//					case ANCIENTSWORD:
//						stat = StatType.ANCIENT_WPN_VULN;
//						break;
//					/*
//					 * case PET: stat = Stats.PET_WPN_VULN; break;
//					 */
//					}
//				}
//
//				// for summon use pet weapon vuln, since they cant hold weapon
//				// if (attacker instanceof L2SummonInstance)
//				// stat = Stats.PET_WPN_VULN;
//
//				// if (crit) {
//				// // Finally retail like formula
//				// damage = 2
//				// * attacker.calcStat(Stats.CRITICAL_DAMAGE, 1,
//				// target, skill)
//				// * target.calcStat(Stats.CRIT_VULN,
//				// target.getTemplate().baseCritVuln, target,
//				// null) * (70 * damage / defense);
//				// // Crit dmg add is almost useless in normal hits...
//				// damage += (attacker.calcStat(Stats.CRITICAL_DAMAGE_ADD, 0,
//				// target, skill) * 70 / defense);
//				// } else
//				// damage = 70 * damage / defense;
//
//				if (stat != null) {
//					// get the vulnerability due to skills (buffs, passives,
//					// toggles, etc)
//					damage = target.calcStat(stat, damage, target, null);
//					/*
//					 * if (target instanceof L2Npc) { // get the natural
//					 * vulnerability for the template damage *= ((L2Npc)
//					 * target).getTemplate().getVulnerability(stat); }
//					 */
//				}
//
//				// Weapon random damage
//				damage *= attacker.getRandomDamageMultiplier();
//
//				// damage += Rnd.nextDouble() * damage / 10;
//				// damage += _rnd.nextDouble()*
//				// attacker.getRandomDamage(target);
//				// }
//				if (shld > 0 && Config.ALT_GAME_SHIELD_BLOCKS) {
//					damage -= target.getShldDef();
//					if (damage < 0)
//						damage = 0;
//				}
//
//				if (target instanceof L2Npc) {
//					switch (((L2Npc) target).getTemplate().getRace()) {
//					case BEAST:
//						damage *= attacker.getPAtkMonsters(target);
//						break;
//					case ANIMAL:
//						damage *= attacker.getPAtkAnimals(target);
//						break;
//					case PLANT:
//						damage *= attacker.getPAtkPlants(target);
//						break;
//					case DRAGON:
//						damage *= attacker.getPAtkDragons(target);
//						break;
//					case BUG:
//						damage *= attacker.getPAtkInsects(target);
//						break;
//					case GIANT:
//						damage *= attacker.getPAtkGiants(target);
//						break;
//					case MAGICCREATURE:
//						damage *= attacker.getPAtkMagicCreatures(target);
//						break;
//					default:
//						// nothing
//						break;
//					}
//				}
//
//				if (damage > 0 && damage < 1) {
//					damage = 1;
//				} else if (damage < 0) {
//					damage = 0;
//				}
//
//				// Dmg bonusses in PvP fight
//				if (isPvP) {
//					if (skill == null)
//						damage *= attacker.calcStat(Stats.PVP_PHYSICAL_DMG, 1,
//								null, null);
//					else
//						damage *= attacker.calcStat(Stats.PVP_PHYS_SKILL_DMG,
//								1, null, null);
//				}
//
//				// Physical skill dmg boost
//				if (skill != null)
//					damage *= attacker.calcStat(Stats.PHYSICAL_SKILL_POWER, 1,
//							null, null);
//
//				damage *= calcElemental(attacker, target, skill);
//				if (target instanceof L2Attackable) {
//					if (isBow) {
//						if (skill != null)
//							damage *= attacker.calcStat(
//									Stats.PVE_BOW_SKILL_DMG, 1, null, null);
//						else
//							damage *= attacker.calcStat(Stats.PVE_BOW_DMG, 1,
//									null, null);
//					} else
//						damage *= attacker.calcStat(Stats.PVE_PHYSICAL_DMG, 1,
//								null, null);
//					if (!target.isRaid()
//							&& !target.isRaidMinion()
//							&& target.getLevel() >= Config.MIN_NPC_LVL_DMG_PENALTY
//							&& attacker.getActingPlayer() != null
//							&& (target.getLevel() - attacker.getActingPlayer()
//									.getLevel()) >= 2) {
//						int lvlDiff = target.getLevel()
//								- attacker.getActingPlayer().getLevel() - 1;
//						if (skill != null) {
//							if (lvlDiff > Config.NPC_SKILL_DMG_PENALTY.size())
//								damage *= Config.NPC_SKILL_DMG_PENALTY
//										.get(Config.NPC_SKILL_DMG_PENALTY
//												.size());
//							else
//								damage *= Config.NPC_SKILL_DMG_PENALTY
//										.get(lvlDiff);
//						} else if (crit) {
//							if (lvlDiff > Config.NPC_CRIT_DMG_PENALTY.size())
//								damage *= Config.NPC_CRIT_DMG_PENALTY
//										.get(Config.NPC_CRIT_DMG_PENALTY.size());
//							else
//								damage *= Config.NPC_CRIT_DMG_PENALTY
//										.get(lvlDiff);
//						} else {
//							if (lvlDiff > Config.NPC_DMG_PENALTY.size())
//								damage *= Config.NPC_DMG_PENALTY
//										.get(Config.NPC_DMG_PENALTY.size());
//							else
//								damage *= Config.NPC_DMG_PENALTY.get(lvlDiff);
//						}
//					}
//				}
//
//				return damage;
				return 10;
			}
		}, new AttackCalculatorFunction(Integer.MAX_VALUE,
				AttackCalculatorType.DAMAGE) {
			@Override
			public double calculate(Actor attacker, Actor target, double value) {
				if (value <= 0)
					return 1;
				return value;
			}
		});
	}
}
