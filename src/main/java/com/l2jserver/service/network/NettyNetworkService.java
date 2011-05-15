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
package com.l2jserver.service.network;

import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ServerChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Slf4JLoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.Lineage2PipelineFactory;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.configuration.ConfigurationService;

/**
 * Netty network service implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NettyNetworkService extends AbstractService implements
		NetworkService {
	private final NetworkConfiguration config;
	private final Injector injector;
	private ServerBootstrap server;
	private ServerChannel channel;

	@Inject
	public NettyNetworkService(ConfigurationService configService,
			Injector injector) {
		this.config = configService.get(NetworkConfiguration.class);
		this.injector = injector;
		InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
	}

	@Override
	public void start() {
		server = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		server.setPipelineFactory(new Lineage2PipelineFactory(injector));
		channel = (ServerChannel) server.bind(config.getListenAddress());
	}

	@Override
	public void stop() {
		try {
			channel.close().awaitUninterruptibly();
		} finally {
			server = null;
			channel = null;
		}
	}
}
