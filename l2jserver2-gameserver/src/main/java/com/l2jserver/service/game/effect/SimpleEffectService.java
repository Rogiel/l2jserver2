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
package com.l2jserver.service.game.effect;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;
import com.l2jserver.model.template.effect.EffectTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.ActorEffectContainer;
import com.l2jserver.model.world.actor.effect.Effect;
import com.l2jserver.model.world.actor.effect.TemporalEffect;
import com.l2jserver.model.world.actor.effect.TemporalEffect.TemporalEffectAction;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.core.threading.ThreadPool;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * An simple {@link EffectService} implementation that uses a fixed tick time to
 * calculate effects.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SimpleEffectService extends AbstractService implements
		EffectService {
	/**
	 * The thread service that will create {@link ThreadPool}s
	 */
	private final ThreadService threadService;

	/**
	 * The {@link ThreadPool} that will execute the effect code
	 */
	private ThreadPool pool;
	/**
	 * All active effects
	 */
	private List<Actor> actors;

	/**
	 * @param threadService the thread service
	 */
	@Inject
	private SimpleEffectService(ThreadService threadService) {
		this.threadService = threadService;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		final int threads = Runtime.getRuntime().availableProcessors();
		
		pool = threadService.createThreadPool("EffectService", threads);
		for (int i = 0; i < threads; i++) {
			pool.async(10, TimeUnit.MILLISECONDS, 10, new Runnable() {
				@Override
				public void run() {
					for (final Actor actor : actors) {
						final ActorEffectContainer container = actor
								.getEffects();
						synchronized (actor) {
							for (final Effect effect : container) {
								if (effect instanceof TemporalEffect) {
									TemporalEffectAction action = ((TemporalEffect) effect)
											.continueEffect();
									switch (action) {
									case CANCEL:
										((TemporalEffect) effect)
												.cancelEffect();
										container.removeEffect(effect);
										break;
									}
								} else {
									container.removeEffect(effect);
								}
							}
						}
					}
				}
			});
		}
		actors = CollectionFactory.newList();
	}

	@Override
	public Effect addEffect(Actor actor, EffectTemplate effectTemplate)
			throws L2Exception {
		final Effect effect = effectTemplate.createEffect(actor);
		effect.applyEffect();
		if (effect instanceof TemporalEffect) {
			actor.getEffects().addEffect(effect);
			return effect;
		}
		// non-temporal effect returns null
		return null;
	}

	@Override
	public void removeEffect(Actor actor, Effect effect) {
		
	}

	@Override
	protected void doStop() throws ServiceStopException {
		pool.dispose();
	}
}
