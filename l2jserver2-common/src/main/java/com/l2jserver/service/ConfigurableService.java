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

/**
 * Marks whether an service can be configured or not
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the configuration interface type
 */
public interface ConfigurableService<T extends ServiceConfiguration> extends
		Service {
	/**
	 * @return the configuration interface used by this service
	 */
	Class<T> getConfigurationInterface();

	/**
	 * Please note that this method will only be set at {@link Service#start()}.
	 * Once {@link Service#stop()} is called, the configuration will be set to
	 * <code>null</code>.
	 * 
	 * @param configuration
	 *            the service configuration instance
	 */
	void setConfiguration(T configuration);
}
