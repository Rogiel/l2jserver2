package com.l2jserver.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.l2jserver.service.game.world.WorldEventDispatcher;
import com.l2jserver.service.game.world.WorldEventDispatcherImpl;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.WorldServiceImpl;
import com.l2jserver.service.network.NettyNetworkService;
import com.l2jserver.service.network.NetworkService;

public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(NetworkService.class).to(NettyNetworkService.class).in(
				Scopes.SINGLETON);

		bind(WorldService.class).to(WorldServiceImpl.class)
				.in(Scopes.SINGLETON);
		bind(WorldEventDispatcher.class).to(WorldEventDispatcherImpl.class).in(
				Scopes.SINGLETON);
	}
}
