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
package com.l2jserver.model.world.capability;

import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.model.world.actor.ActorEffects;
import com.l2jserver.model.world.actor.ActorSkillContainer;
import com.l2jserver.model.world.actor.event.ActorEvent;
import com.l2jserver.model.world.actor.event.ActorListener;

/**
 * Defines an {@link AbstractObject} that defines an Actor (NPC, player, pet,
 * etc...)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Actor extends Listenable<ActorListener, ActorEvent>,
		Spawnable, Pointable, Damagable, Attackable, Attacker, Castable,
		Caster, Levelable, Killable, Equiper, Equipable {
	/**
	 * @return the actor effects
	 */
	ActorEffects getEffects();

	/**
	 * @return the actor skills
	 */
	ActorSkillContainer getSkills();

	@Override
	ActorID<?> getID();
}
