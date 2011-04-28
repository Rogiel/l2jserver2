package com.l2jserver.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.configuration.ProxyConfigurationService;
import com.l2jserver.service.database.DB4ODatabaseService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.network.NettyNetworkService;
import com.l2jserver.service.network.NetworkService;

public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ConfigurationService.class).to(ProxyConfigurationService.class)
				.in(Scopes.SINGLETON);
		bind(DatabaseService.class).to(DB4ODatabaseService.class).in(
				Scopes.SINGLETON);

		bind(NetworkService.class).to(NettyNetworkService.class).in(
				Scopes.SINGLETON);
	}
}
