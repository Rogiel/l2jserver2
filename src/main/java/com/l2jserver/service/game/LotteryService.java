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
package com.l2jserver.service.game;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This service handles lottery. Bets and results are done trough this service.
 * Please note that implementations can be complete random or they can use an
 * algorithm that manipulate results so that an character is more likely to win.
 * Even if the algorithm is manipulating results, it should not try to
 * discriminate players and chances of winning must be equal per ticket.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface LotteryService extends Service {
	/**
	 * Bets an certain number sequence.
	 * 
	 * @param character
	 *            the character betting
	 * @param n1
	 *            the first number
	 * @param n2
	 *            the second number
	 * @param n3
	 *            the third number
	 * @param n4
	 *            the fourth number
	 * @param n5
	 *            the fifth number
	 */
	void bet(L2Character character, int n1, int n2, int n3, int n4, int n5);

	/**
	 * @return the winning lottery ticket prize
	 */
	int getPrize();

	/**
	 * Redeem a winning lottery ticket prize
	 * 
	 * @param ticket
	 *            the winning ticket
	 */
	void redeemPrize(Item ticket);
}
