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
package com.l2jserver.service.game.ai;

import com.google.inject.Inject;
import com.l2jserver.model.world.Actor;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * Default implementation for {@link AIService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class, TemplateService.class, ThreadService.class,
		NetworkService.class })
public class AIServiceImpl extends AbstractService implements AIService {
	/**
	 * The {@link WorldService}
	 */
	@SuppressWarnings("unused")
	private final WorldService worldService;
	/**
	 * The {@link WorldService} event dispatcher
	 */
	@SuppressWarnings("unused")
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The {@link ThreadService}
	 */
	@SuppressWarnings("unused")
	private final ThreadService threadService;
	/**
	 * The {@link NetworkService}
	 */
	@SuppressWarnings("unused")
	private final NetworkService networkService;

	@Inject
	public AIServiceImpl(WorldService worldService,
			WorldEventDispatcher eventDispatcher, ThreadService threadService,
			NetworkService networkService) {
		this.worldService = worldService;
		this.eventDispatcher = eventDispatcher;
		this.threadService = threadService;
		this.networkService = networkService;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		
	}

	@Override
	public void walk(Actor actor, Coordinate coordinate) {
		
	}

	@Override
	protected void doStop() throws ServiceStopException {
		
	}
}
