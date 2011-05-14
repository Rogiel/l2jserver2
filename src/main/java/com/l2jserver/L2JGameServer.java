package com.l2jserver;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * The L2JGameServer class
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class L2JGameServer {
	/**
	 * The server injector
	 */
	private final Injector injector = Guice
			.createInjector(new GameServerModule());

	/**
	 * Get the injector
	 * 
	 * @return the injector
	 */
	public Injector getInjector() {
		return injector;
	}
}
