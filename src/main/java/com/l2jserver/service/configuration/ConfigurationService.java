package com.l2jserver.service.configuration;

import com.l2jserver.service.Service;

/**
 * The configuration service is responsible for reading and writing in
 * configuration storage. Each configuration is represented by an interface.
 * Implementations of this interface are implementaion specific.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ConfigurationService extends Service {
	/**
	 * Get the configuration object for <tt>config</tt>
	 * 
	 * @param <C>
	 *            the configuration type
	 * @param config
	 *            the configuration interface class
	 * @return the configuration object
	 */
	<C extends Configuration> C get(Class<C> config);
}
