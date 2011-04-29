package com.l2jserver;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class L2JGameServer {
	private final Injector injector = Guice
			.createInjector(new GameServerModule());

	public Injector getInjector() {
		return injector;
	}
}
