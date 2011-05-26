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
package com.l2jserver.model.world.character.calculator;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterMoveType;
import com.l2jserver.model.world.actor.stat.BaseStats;
import com.l2jserver.util.calculator.AbstractFunction;
import com.l2jserver.util.calculator.CalculatorContext;

/**
 * Calculates the character base HP regeneration
 * 
 * <pre>
 * TODO base hp regen
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class BaseHPRegenerationCalculator extends CharacterCalculator {
	/**
	 * Retail value is 100
	 */
	public static final double HP_REGEN_MULTIPLIER = 100;

	@SuppressWarnings("unchecked")
	public BaseHPRegenerationCalculator() {
		super(new AbstractFunction<L2Character>(0x000) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				// TODO set base hp regen
				ctx.result = 0;

				// initial value is changed here
				ctx.result += (c.getLevel() > 10) ? ((c.getLevel() - 1) / 10.0)
						: 0.5;

				// Add CON bonus
				ctx.result *= ((100.0 - 11 + c.getLevel()) / 100.0)
						* BaseStats.CON.calculateBonus(c.getStats()
								.getConcentration());

				if (ctx.result < 0)
					ctx.result = 0;

			}
		}, new AbstractFunction<L2Character>(Integer.MAX_VALUE) {
			@Override
			public void calculate(L2Character c, CalculatorContext ctx) {
				double hpRegenMultiplier = HP_REGEN_MULTIPLIER;
				double hpRegenBonus = 0;

				// TODO SevenSigns Festival modifier
				// if (SevenSignsFestival.getInstance().isFestivalInProgress()
				// && player.isFestivalParticipant())
				// hpRegenMultiplier *= calcFestivalRegenModifier(player);
				// else {
				// double siegeModifier = calcSiegeRegenModifer(player);
				// if (siegeModifier > 0)
				// hpRegenMultiplier *= siegeModifier;
				// }

				// TODO clan hall regen
				// if (player.isInsideZone(L2Character.ZONE_CLANHALL)
				// && player.getClan() != null
				// && player.getClan().getHasHideout() > 0) {
				// L2ClanHallZone zone = ZoneManager.getInstance().getZone(
				// player, L2ClanHallZone.class);
				// int posChIndex = zone == null ? -1 : zone.getClanHallId();
				// int clanHallIndex = player.getClan().getHasHideout();
				// if (clanHallIndex > 0 && clanHallIndex == posChIndex) {
				// ClanHall clansHall = ClanHallManager.getInstance()
				// .getClanHallById(clanHallIndex);
				// if (clansHall != null)
				// if (clansHall.getFunction(ClanHall.FUNC_RESTORE_HP) != null)
				// hpRegenMultiplier *= 1 + (double) clansHall
				// .getFunction(ClanHall.FUNC_RESTORE_HP)
				// .getLvl() / 100;
				// }
				// }

				// TODO castle regen
				// if (player.isInsideZone(L2Character.ZONE_CASTLE)
				// && player.getClan() != null
				// && player.getClan().getHasCastle() > 0) {
				// L2CastleZone zone = ZoneManager.getInstance().getZone(
				// player, L2CastleZone.class);
				// int posCastleIndex = zone == null ? -1 : zone.getCastleId();
				// int castleIndex = player.getClan().getHasCastle();
				// if (castleIndex > 0 && castleIndex == posCastleIndex) {
				// Castle castle = CastleManager.getInstance()
				// .getCastleById(castleIndex);
				// if (castle != null)
				// if (castle.getFunction(Castle.FUNC_RESTORE_HP) != null)
				// hpRegenMultiplier *= 1 + (double) castle
				// .getFunction(Castle.FUNC_RESTORE_HP)
				// .getLvl() / 100;
				// }
				// }

				// TODO fort regen
				// if (player.isInsideZone(L2Character.ZONE_FORT)
				// && player.getClan() != null
				// && player.getClan().getHasFort() > 0) {
				// L2FortZone zone = ZoneManager.getInstance().getZone(player,
				// L2FortZone.class);
				// int posFortIndex = zone == null ? -1 : zone.getFortId();
				// int fortIndex = player.getClan().getHasFort();
				// if (fortIndex > 0 && fortIndex == posFortIndex) {
				// Fort fort = FortManager.getInstance().getFortById(
				// fortIndex);
				// if (fort != null)
				// if (fort.getFunction(Fort.FUNC_RESTORE_HP) != null)
				// hpRegenMultiplier *= 1 + (double) fort
				// .getFunction(Fort.FUNC_RESTORE_HP)
				// .getLvl() / 100;
				// }
				// }

				// TODO Mother Tree effect is calculated at last
				// if (player.isInsideZone(L2Character.ZONE_MOTHERTREE)) {
				// L2MotherTreeZone zone = ZoneManager.getInstance().getZone(
				// player, L2MotherTreeZone.class);
				// int hpBonus = zone == null ? 0 : zone.getHpRegenBonus();
				// hpRegenBonus += hpBonus;
				// }

				// Calculate Movement bonus
				// if (player.isSitting())
				// hpRegenMultiplier *= 1.5; // Sitting
				// else
				if (c.isIdle())
					hpRegenMultiplier *= 1.1; // Staying
				else if (c.getMoveType() == CharacterMoveType.RUN)
					hpRegenMultiplier *= 0.7; // Running

				ctx.result = ctx.result * hpRegenMultiplier + hpRegenBonus;
			}
		});
	}
}
