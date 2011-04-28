package com.l2jserver.service.network;

import com.google.inject.Inject;
import com.l2jserver.service.configuration.ConfigurationService;

public class NettyNetworkService implements NetworkService {
	private final NetworkConfiguration config;

	@Inject
	public NettyNetworkService(ConfigurationService configService) {
		this.config = configService.get(NetworkConfiguration.class);
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}
}
