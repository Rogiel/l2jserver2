package com.l2jserver.service;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.configuration.XMLConfigurationService;
import com.l2jserver.service.core.Log4JLoggingService;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.LoginServerJDBCDatabaseService;

public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ServiceManager.class).in(Scopes.SINGLETON);
		bind(LoggingService.class).to(Log4JLoggingService.class).in(
				Scopes.SINGLETON);
		bind(ConfigurationService.class).to(XMLConfigurationService.class)
				.in(Scopes.SINGLETON);
		bind(DatabaseService.class).to(LoginServerJDBCDatabaseService.class)
				.in(Scopes.SINGLETON);
	}
}
