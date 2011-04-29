package com.l2jserver.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.l2jserver.service.network.NettyNetworkService;
import com.l2jserver.service.network.NetworkService;

public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(NetworkService.class).to(NettyNetworkService.class).in(
				Scopes.SINGLETON);
	}
}
