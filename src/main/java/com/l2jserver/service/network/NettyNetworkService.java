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

import java.util.Set;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ServerChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Slf4JLoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.Lineage2PipelineFactory;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.logging.LoggingService;
import com.l2jserver.service.network.keygen.BlowfishKeygenService;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Netty network service implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, BlowfishKeygenService.class,
		WorldService.class })
public class NettyNetworkService extends AbstractService implements
		NetworkService {
	/**
	 * The {@link WorldService} instance
	 */
	private final WorldService worldService;

	/**
	 * The network configuration object
	 */
	private final NetworkConfiguration config;
	/**
	 * The Google Guice {@link Injector}
	 */
	private final Injector injector;

	/**
	 * The server bootstrap
	 */
	private ServerBootstrap server;
	/**
	 * The server channel
	 */
	private ServerChannel channel;
	/**
	 * The client list. This list all active clients in the server
	 */
	private Set<Lineage2Connection> clients = CollectionFactory
			.newSet(Lineage2Connection.class);

	@Inject
	public NettyNetworkService(ConfigurationService configService,
			Injector injector, WorldService worldService) {
		this.config = configService.get(NetworkConfiguration.class);
		this.injector = injector;
		this.worldService = worldService;
		InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
	}

	@Override
	protected void doStart() {
		server = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		server.setPipelineFactory(new Lineage2PipelineFactory(injector, this,
				worldService));
		channel = (ServerChannel) server.bind(config.getListenAddress());
	}

	@Override
	public void register(final Lineage2Connection client) {
		clients.add(client);
		client.getChannel().getCloseFuture()
				.addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future)
							throws Exception {
						unregister(client);
					}
				});
	}

	@Override
	public void unregister(Lineage2Connection client) {
		clients.remove(client);
	}

	@Override
	public Lineage2Connection discover(CharacterID character) {
		for (final Lineage2Connection client : clients) {
			if (character.equals(client.getCharacterID()))
				return client;
		}
		return null;
	}

	@Override
	public void cleanup() {
		// TODO
	}

	@Override
	protected void doStop() {
		try {
			channel.close().awaitUninterruptibly();
		} finally {
			server = null;
			channel = null;
		}
	}
}
