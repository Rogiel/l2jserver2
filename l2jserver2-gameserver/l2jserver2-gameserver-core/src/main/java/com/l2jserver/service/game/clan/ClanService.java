/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.clan;

import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.Service;

/**
 * This service is responsible for managing {@link Clan clans}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ClanService extends Service {
	/**
	 * Joins the <tt>character</tt> on the given <tt>clan</tt>
	 * 
	 * @param clan
	 *            the clan
	 * @param character
	 *            the character (joinee)
	 * @param inviter
	 *            the character inviting <tt>character</tt> to this
	 *            <tt>clan</tt>, if any.
	 */
	void join(Clan clan, L2Character character, L2Character inviter);

	/**
	 * Removes the <tt>character</tt> from the given <tt>clan</tt>
	 * 
	 * @param clan
	 *            the clan
	 * @param character
	 *            the character (leaver)
	 */
	void leave(Clan clan, L2Character character);
}
