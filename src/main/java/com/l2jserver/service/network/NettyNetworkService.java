package com.l2jserver.service.network;

import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ServerChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.Lineage2PipelineFactory;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.configuration.ConfigurationService;

public class NettyNetworkService extends AbstractService implements NetworkService {
	private final NetworkConfiguration config;
	private final Injector injector;
	private ServerBootstrap server;
	private ServerChannel channel;

	@Inject
	public NettyNetworkService(ConfigurationService configService,
			Injector injector) {
		this.config = configService.get(NetworkConfiguration.class);
		this.injector = injector;
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
