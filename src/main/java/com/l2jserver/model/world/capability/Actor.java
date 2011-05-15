package com.l2jserver.model.world.capability;

import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.model.world.actor.ActorEffects;
import com.l2jserver.model.world.actor.ActorEvent;
import com.l2jserver.model.world.actor.ActorListener;
import com.l2jserver.model.world.actor.ActorSkillContainer;

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
