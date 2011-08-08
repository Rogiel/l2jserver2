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

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.server.AttackHit;
import com.l2jserver.model.server.attack.AttackCalculator;
import com.l2jserver.model.server.attack.AttackCalculator.AttackCalculatorType;
import com.l2jserver.model.server.attack.AttackCalculatorContext;
import com.l2jserver.model.server.attack.PhysicalAttackCalculator;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.actor.event.ActorAttackHitEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.core.threading.AsyncFuture;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ ThreadService.class })
public class AttackServiceImpl extends AbstractService implements AttackService {
	/**
	 * Calculator used to compute physical attacks
	 */
	private static final AttackCalculator PHYSICAL_ATTACK_CALCULATOR = new PhysicalAttackCalculator();

	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link ThreadService} is used to schedule asynchronous attacks
	 */
	private final ThreadService threadService;
	/**
	 * The {@link NPCService}
	 */
	private final NPCService npcService;

	/**
	 * The {@link WorldEventDispatcher} is used to dispatch attack events to the
	 * world
	 */
	private final WorldEventDispatcher eventDispatcher;

	@Inject
	public AttackServiceImpl(ThreadService threadService,
			NPCService npcService, WorldEventDispatcher eventDispatcher) {
		this.threadService = threadService;
		this.npcService = npcService;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public AsyncFuture<AttackHit> attack(Actor attacker, Actor target) {
		Preconditions.checkNotNull(attacker, "attacker");
		Preconditions.checkNotNull(target, "target");
		Preconditions.checkArgument(!attacker.equals(target),
				"attacker must not be equal to target");
		log.debug("{} starting attack to {}", attacker, target);
		return threadService.async(new AttackCallable(attacker, target));
	}

	/**
	 * {@link Callable} implementation used to execute attacks.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class AttackCallable implements Callable<AttackHit> {
		/**
		 * The attacker
		 */
		private final Actor attacker;
		/**
		 * The target
		 */
		private final Actor target;

		public AttackCallable(Actor attacker, Actor target) {
			this.attacker = attacker;
			this.target = target;
		}

		@Override
		public AttackHit call() throws Exception {
			final double hp = target.getHP();
			final double damage = PHYSICAL_ATTACK_CALCULATOR.calculate(
					AttackCalculatorType.DAMAGE, new AttackCalculatorContext(
							attacker, target));
			final double dealDamage = (hp < damage ? hp : damage);
			// TODO calculate miss
			// TODO calculate critical
			// TODO calculate soulshot
			
			log.debug("Attack dealt {} damage, but only {} is effective", damage, dealDamage);

			// reduce target life
			target.setHP(target.getHP() - dealDamage);

			final AttackHit hit = new AttackHit(attacker, target, damage);
			eventDispatcher.dispatch(new ActorAttackHitEvent(hit));

			if (target.getHP() <= 0) {
				log.debug("{} hitpoins reached zero, killing object", target);
				if (target instanceof NPC)
					npcService.die((NPC) target, attacker);
			}
			return hit;
		}
	}
}
