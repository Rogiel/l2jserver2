package com.l2jserver.service.database;

import com.google.inject.Inject;
import com.l2jserver.service.configuration.ConfigurationService;

public class MySQL5DatabaseService implements DatabaseService {
	private final MySQLDatabaseConfiguration config;

	@Inject
	public MySQL5DatabaseService(ConfigurationService configService) {
		config = configService.get(MySQLDatabaseConfiguration.class);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
}
