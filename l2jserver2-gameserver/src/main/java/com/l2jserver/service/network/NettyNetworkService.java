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

import java.util.Set;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ServerChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Slf4JLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.Lineage2PipelineFactory;
import com.l2jserver.game.net.packet.ServerPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.game.world.WorldService;
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
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

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
	private Set<Lineage2Client> clients = CollectionFactory.newSet();

	/**
	 * @param configService
	 *            the configuration service
	 * @param injector
	 *            the {@link Guice} {@link Injector}
	 * @param worldService
	 *            the world service
	 */
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
	public void register(final Lineage2Client client) {
		Preconditions.checkNotNull(client, "client");

		log.debug("Registering client: {}", client);

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
	public void unregister(Lineage2Client client) {
		Preconditions.checkNotNull(client, "client");

		log.debug("Unregistering client: {}", client);
		clients.remove(client);
	}

	@Override
	public Lineage2Client discover(CharacterID character) {
		Preconditions.checkNotNull(character, "character");

		log.debug("Discovering client object for {}", character);

		for (final Lineage2Client client : clients) {
			if (character.equals(client.getCharacterID()))
				return client;
		}
		return null;
	}

	@Override
	public void broadcast(ServerPacket packet) {
		Preconditions.checkNotNull(packet, "packet");

		log.debug("Broadcasting {} packet to all connected clients", packet);

		channel.write(packet);
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
