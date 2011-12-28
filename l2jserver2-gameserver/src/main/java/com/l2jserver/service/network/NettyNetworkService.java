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
import java.util.concurrent.TimeUnit;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ServerChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Slf4JLoggerFactory;
import org.jboss.netty.util.ThreadNameDeterminer;
import org.jboss.netty.util.ThreadRenamingRunnable;
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
import com.l2jserver.service.AbstractConfigurableService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.core.threading.ThreadPool;
import com.l2jserver.service.core.threading.ThreadPoolPriority;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.network.keygen.BlowfishKeygenService;
import com.l2jserver.util.ThreadPoolUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Netty network service implementation
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, ThreadService.class,
		BlowfishKeygenService.class, WorldService.class })
public class NettyNetworkService extends
		AbstractConfigurableService<NetworkServiceConfiguration> implements
		NetworkService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link ThreadService}
	 */
	private final ThreadService threadService;

	/**
	 * The Google Guice {@link Injector}
	 */
	private final Injector injector;

	/**
	 * Netty Boss {@link ThreadPool}
	 */
	private ThreadPool bossPool;
	/**
	 * Netty Worker {@link ThreadPool}
	 */
	private ThreadPool workerPool;
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
	 * @param injector
	 *            the {@link Guice} {@link Injector}
	 * @param threadService
	 *            the {@link ThreadService}
	 */
	@Inject
	public NettyNetworkService(Injector injector, ThreadService threadService) {
		super(NetworkServiceConfiguration.class);
		this.threadService = threadService;
		this.injector = injector;
		InternalLoggerFactory.setDefaultFactory(new Slf4JLoggerFactory());
	}

	@Override
	protected void doStart() {
		bossPool = threadService.createThreadPool("netty-boss", 10, 60,
				TimeUnit.SECONDS, ThreadPoolPriority.HIGH);
		workerPool = threadService.createThreadPool("netty-worker", 50, 60,
				TimeUnit.SECONDS, ThreadPoolPriority.HIGH);

		ThreadRenamingRunnable
				.setThreadNameDeterminer(new ThreadNameDeterminer() {
					@Override
					public String determineThreadName(String currentThreadName,
							String proposedThreadName) throws Exception {
						return currentThreadName;
					}
				});

		server = new ServerBootstrap(new NioServerSocketChannelFactory(
				ThreadPoolUtils.wrap(bossPool),
				ThreadPoolUtils.wrap(workerPool), 50));

		server.setPipelineFactory(new Lineage2PipelineFactory(injector, this));
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
			server.releaseExternalResources();
			bossPool.dispose();
			workerPool.dispose();
		} finally {
			server = null;
			channel = null;
			bossPool = null;
			workerPool = null;
		}
		clients.clear();
	}
}
