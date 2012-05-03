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
package com.l2jserver.service.network;

import org.jboss.netty.channel.ChannelPipelineFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.Lineage2PipelineFactory;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.core.logging.LoggingService;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.network.keygen.BlowfishKeygenService;

/**
 * Netty network service implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, ThreadService.class,
		BlowfishKeygenService.class, WorldService.class })
public class NettyNetworkService extends
		AbstractNettyNetworkService {
	/**
	 * @param injector
	 *            the {@link Guice} {@link Injector}
	 * @param threadService
	 *            the {@link ThreadService}
	 */
	@Inject
	public NettyNetworkService(Injector injector, ThreadService threadService) {
		super(injector, threadService);
	}
	
	@Override
	protected ChannelPipelineFactory createPipelineFactory(Injector injector) {
		return new Lineage2PipelineFactory(injector, this);
	}
}
