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
 * Each Service is a provider of a given feature. Most services will want to
 * implement {@link AbstractService} class instead of this interface.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Service {
	/**
	 * Start this service
	 * 
	 * @throws ServiceStartException
	 *             if an error occurred
	 */
	void start() throws ServiceStartException;

	/**
	 * Stop this service
	 * 
	 * @throws ServiceStopException
	 *             if an error occurred
	 */
	void stop() throws ServiceStopException;

	/**
	 * Stop this service
	 * 
	 * @throws ServiceException
	 *             if an error occurred
	 */
	void restart() throws ServiceException;

	/**
	 * @return true if service is running
	 */
	boolean isStarted();

	/**
	 * @return false if service is not running
	 */
	boolean isStopped();

	/**
	 * @return the other services that the service depends on
	 */
	Class<? extends Service>[] getDependencies();
}
