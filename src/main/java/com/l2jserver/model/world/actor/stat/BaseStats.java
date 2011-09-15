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

import com.l2jserver.model.world.Actor;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum BaseStats {
	/**
	 * Strength multipliers
	 */
	STR(0.29, 0.3, 0.31, 0.32, 0.34, 0.35, 0.36, 0.37, 0.39, 0.4, 0.42, 0.43,
			0.45, 0.46, 0.48, 0.5, 0.51, 0.53, 0.55, 0.57, 0.59, 0.61, 0.63,
			0.66, 0.68, 0.71, 0.73, 0.76, 0.78, 0.81, 0.84, 0.87, 0.9, 0.94,
			0.97, 1.01, 1.04, 1.08, 1.12, 1.16, 1.2, 1.24, 1.29, 1.33, 1.38,
			1.43, 1.48, 1.54, 1.59, 1.65, 1.71, 1.77, 1.83, 1.9, 1.97, 2.04,
			2.11, 2.19, 2.27, 2.35, 2.43, 2.52, 2.61, 2.71, 2.8, 2.91, 3.01,
			3.12, 3.23, 3.35, 3.47, 3.59, 3.72, 3.86, 3.99, 4.14, 4.29, 4.44,
			4.6, 4.77, 4.94, 5.12, 5.3, 5.49, 5.69, 5.89, 6.11, 6.33, 6.55,
			6.79, 7.03, 7.29, 7.55, 7.82, 8.1, 8.39, 8.7, 9.01, 9.33, 9.67),

	/**
	 * Intelligence multipliers
	 */
	INT(0.54, 0.55, 0.56, 0.57, 0.58, 0.59, 0.61, 0.62, 0.63, 0.64, 0.65, 0.67,
			0.68, 0.69, 0.71, 0.72, 0.74, 0.75, 0.77, 0.78, 0.8, 0.81, 0.83,
			0.85, 0.86, 0.88, 0.9, 0.92, 0.94, 0.95, 0.97, 0.99, 1.01, 1.03,
			1.05, 1.07, 1.1, 1.12, 1.14, 1.16, 1.19, 1.21, 1.23, 1.26, 1.28,
			1.31, 1.34, 1.36, 1.39, 1.42, 1.45, 1.47, 1.5, 1.53, 1.57, 1.6,
			1.63, 1.66, 1.69, 1.73, 1.76, 1.8, 1.83, 1.87, 1.91, 1.95, 1.99,
			2.02, 2.07, 2.11, 2.15, 2.19, 2.24, 2.28, 2.33, 2.37, 2.42, 2.47,
			2.52, 2.57, 2.62, 2.67, 2.73, 2.78, 2.84, 2.89, 2.95, 3.01, 3.07,
			3.13, 3.19, 3.26, 3.32, 3.39, 3.46, 3.53, 3.6, 3.67, 3.74, 3.82),

	/**
	 * Dexterity multipliers
	 */
	DEX(0.84, 0.85, 0.86, 0.86, 0.87, 0.88, 0.89, 0.9, 0.9, 0.91, 0.92, 0.93,
			0.94, 0.94, 0.95, 0.96, 0.97, 0.98, 0.99, 1, 1.01, 1.01, 1.02,
			1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.1, 1.11, 1.12, 1.13,
			1.14, 1.15, 1.16, 1.17, 1.18, 1.19, 1.2, 1.21, 1.22, 1.24, 1.25,
			1.26, 1.27, 1.28, 1.29, 1.3, 1.32, 1.33, 1.34, 1.35, 1.36, 1.38,
			1.39, 1.4, 1.41, 1.43, 1.44, 1.45, 1.47, 1.48, 1.49, 1.51, 1.52,
			1.53, 1.55, 1.56, 1.57, 1.59, 1.6, 1.62, 1.63, 1.65, 1.66, 1.68,
			1.69, 1.71, 1.72, 1.74, 1.75, 1.77, 1.78, 1.8, 1.82, 1.83, 1.85,
			1.87, 1.88, 1.9, 1.92, 1.93, 1.95, 1.97, 1.99, 2, 2.02, 2.04),

	/**
	 * Witness multipliers
	 */
	WIT(0.39, 0.4, 0.42, 0.44, 0.46, 0.48, 0.51, 0.53, 0.56, 0.58, 0.61, 0.64,
			0.68, 0.71, 0.75, 0.78, 0.82, 0.86, 0.91, 0.95, 1, 1.05, 1.1, 1.16,
			1.22, 1.28, 1.34, 1.41, 1.48, 1.55, 1.63, 1.71, 1.8, 1.89, 1.98,
			2.08, 2.18, 2.29, 2.41, 2.53, 2.65, 2.79, 2.93, 3.07, 3.23, 3.39,
			3.56, 3.73, 3.92, 4.12, 4.32, 4.54, 4.76, 5, 5.25, 5.52, 5.79,
			6.08, 6.39, 6.7, 7.04, 7.39, 7.76, 8.15, 8.56, 8.99, 9.43, 9.91,
			10.4, 10.92, 11.47, 12.04, 12.64, 13.27, 13.94, 14.64, 15.37,
			16.14, 16.94, 17.79, 18.68, 19.61, 20.59, 21.62, 22.7, 23.84,
			25.03, 26.28, 27.6, 28.98, 30.43, 31.95, 33.55, 35.22, 36.98,
			38.83, 40.77, 42.81, 44.95, 47.2),

	/**
	 * Concentration multipliers
	 */
	CON(0.45, 0.46, 0.47, 0.48, 0.5, 0.51, 0.53, 0.54, 0.56, 0.58, 0.59, 0.61,
			0.63, 0.65, 0.67, 0.69, 0.71, 0.73, 0.75, 0.77, 0.8, 0.82, 0.85,
			0.87, 0.9, 0.93, 0.95, 0.98, 1.01, 1.04, 1.07, 1.1, 1.14, 1.17,
			1.21, 1.24, 1.28, 1.32, 1.36, 1.4, 1.44, 1.48, 1.53, 1.58, 1.62,
			1.67, 1.72, 1.77, 1.83, 1.88, 1.94, 2, 2.06, 2.12, 2.18, 2.25,
			2.31, 2.38, 2.45, 2.53, 2.6, 2.68, 2.76, 2.84, 2.93, 3.02, 3.11,
			3.2, 3.3, 3.4, 3.5, 3.6, 3.71, 3.82, 3.94, 4.06, 4.18, 4.3, 4.43,
			4.56, 4.7, 4.84, 4.99, 5.14, 5.29, 5.45, 5.61, 5.78, 5.96, 6.13,
			6.32, 6.51, 6.7, 6.9, 7.11, 7.33, 7.54, 7.77, 8, 8.24),

	/**
	 * Mentality multipliers
	 */
	MEN(1, 1.01, 1.02, 1.03, 1.04, 1.05, 1.06, 1.07, 1.08, 1.09, 1.11, 1.12,
			1.13, 1.14, 1.15, 1.16, 1.17, 1.19, 1.2, 1.21, 1.22, 1.23, 1.25,
			1.26, 1.27, 1.28, 1.3, 1.31, 1.32, 1.34, 1.35, 1.36, 1.38, 1.39,
			1.4, 1.42, 1.43, 1.45, 1.46, 1.48, 1.49, 1.5, 1.52, 1.53, 1.55,
			1.57, 1.58, 1.6, 1.61, 1.63, 1.65, 1.66, 1.68, 1.7, 1.71, 1.73,
			1.75, 1.76, 1.78, 1.8, 1.82, 1.84, 1.85, 1.87, 1.89, 1.91, 1.93,
			1.95, 1.97, 1.99, 2.01, 2.03, 2.05, 2.07, 2.09, 2.11, 2.13, 2.15,
			2.17, 2.2, 2.22, 2.24, 2.26, 2.29, 2.31, 2.33, 2.35, 2.38, 2.4,
			2.43, 2.45, 2.47, 2.5, 2.52, 2.55, 2.58, 2.6, 2.63, 2.65, 2.68);

	/**
	 * Bonus array for the base stat. The key represents the value of the
	 * attribute.
	 */
	public final double[] bonus;

	/**
	 * @param bonus
	 *            the bonus multipliers
	 */
	BaseStats(double... bonus) {
		this.bonus = bonus;
	}

	/**
	 * Calculates the bonus
	 * 
	 * @param n
	 *            the level
	 * @return the bonus for the given level
	 */
	public double calculateBonus(int n) {
		return bonus[n];
	}

	/**
	 * Calculates the bonus
	 * 
	 * @param actor
	 *            the actor
	 * @return the bonus for the given actor
	 */
	public double calculateBonus(Actor actor) {
		return calculateBonus(actor.getStats());
	}

	/**
	 * @param stats
	 *            the actor stats
	 * @return the calculated bonus
	 */
	public double calculateBonus(ActorStats<?> stats) {
		switch (this) {
		case CON:
			return calculateBonus(stats.getConcentration());
		case DEX:
			return calculateBonus(stats.getDexterity());
		case INT:
			return calculateBonus(stats.getIntelligence());
		case MEN:
			return calculateBonus(stats.getMentality());
		case STR:
			return calculateBonus(stats.getStrength());
		case WIT:
			return calculateBonus(stats.getWitness());
		}
		return 0;
	}
}
