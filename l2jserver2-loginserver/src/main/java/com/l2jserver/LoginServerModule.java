package com.l2jserver;

import com.google.inject.AbstractModule;
import com.l2jserver.service.ServiceModule;

public class LoginServerModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new ServiceModule());
	}
}
