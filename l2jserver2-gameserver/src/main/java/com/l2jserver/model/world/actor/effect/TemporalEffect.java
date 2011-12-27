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
package com.l2jserver.model.world.actor.effect;

import com.l2jserver.service.game.effect.EffectService;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface TemporalEffect extends Effect {
	/**
	 * Continues the effect calculation, if necessary.
	 * 
	 * @return the next action
	 */
	TemporalEffectAction continueEffect();

	/**
	 * The result from {@link TemporalEffect#continueEffect()}, instructs the
	 * {@link EffectService} in what it should do next.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum TemporalEffectAction {
		/**
		 * Cancels the effect after this tick
		 */
		CANCEL,
		/**
		 * Continues this effect for at least one more tick
		 */
		CONTINUE;
	}

	/**
	 * Ends the effect. Deattachs the actor from any service it may have been
	 * attached due to this effect and reverts actor back to normal state.
	 */
	void cancelEffect();
}
