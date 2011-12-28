/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service;

import javolution.lang.Configurable;

/**
 * Implements basic management for configurable services
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the service configuration type
 */
public class AbstractConfigurableService<T extends ServiceConfiguration>
		extends AbstractService implements ConfigurableService<T> {
	/**
	 * The service configuration
	 */
	protected T config;
	/**
	 * The service configuration class
	 */
	private final Class<T> configType;

	/**
	 * @param configType
	 *            the service configuration class
	 */
	public AbstractConfigurableService(Class<T> configType) {
		this.configType = configType;
	}

	/**
	 * Transparently implements
	 * {@link ConfigurableService#getConfigurationInterface()} without
	 * implementing the interface
	 * 
	 * @return the configuration interface set at {@link Configurable}
	 *         annotation, if present.
	 */
	@Override
	public Class<T> getConfigurationInterface() {
		return configType;
	}

	@Override
	public void setConfiguration(T configuration) {
		config = configuration;
	}
}
