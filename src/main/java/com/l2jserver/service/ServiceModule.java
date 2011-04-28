package com.l2jserver.service;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new BasicServiceModule());
	}
}
