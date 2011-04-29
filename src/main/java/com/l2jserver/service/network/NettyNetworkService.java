package com.l2jserver.service.network;

import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ServerChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.google.inject.Inject;
import com.l2jserver.service.configuration.ConfigurationService;

public class NettyNetworkService implements NetworkService {
	private final NetworkConfiguration config;
	private ServerBootstrap server;
	private ServerChannel channel;

	@Inject
	public NettyNetworkService(ConfigurationService configService) {
		this.config = configService.get(NetworkConfiguration.class);
	}

	@Override
	public void start() {
		server = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
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
