package com.l2jserver.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.configuration.ProxyConfigurationService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.scripting.ScriptingServiceImpl;
import com.l2jserver.service.game.template.StaticTemplateService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.WorldServiceImpl;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.game.world.event.WorldEventDispatcherImpl;
import com.l2jserver.service.logging.Log4JLoggingService;
import com.l2jserver.service.logging.LoggingService;
import com.l2jserver.service.network.NettyNetworkService;
import com.l2jserver.service.network.NetworkService;

public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ServiceManager.class).in(Scopes.SINGLETON);
		bind(LoggingService.class).to(Log4JLoggingService.class).in(
				Scopes.SINGLETON);
		bind(ConfigurationService.class).to(ProxyConfigurationService.class)
				.in(Scopes.SINGLETON);
		bind(DatabaseService.class).to(MySQLDatabaseService.class).in(
				Scopes.SINGLETON);
		
		bind(NetworkService.class).to(NettyNetworkService.class).in(
				Scopes.SINGLETON);
		bind(ScriptingService.class).to(ScriptingServiceImpl.class).in(
				Scopes.SINGLETON);
		bind(TemplateService.class).to(StaticTemplateService.class).in(
				Scopes.SINGLETON);

		bind(WorldService.class).to(WorldServiceImpl.class)
				.in(Scopes.SINGLETON);
		bind(WorldEventDispatcher.class).to(WorldEventDispatcherImpl.class).in(
				Scopes.SINGLETON);
	}
}
