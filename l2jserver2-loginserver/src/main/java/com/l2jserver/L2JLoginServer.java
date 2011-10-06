package com.l2jserver;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class L2JLoginServer {
	private final Injector injector = Guice
			.createInjector(new LoginServerModule());

	public Injector getInjector() {
		return injector;
	}
}
