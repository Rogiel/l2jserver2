/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.service.logging.LoggingService;

/**
 * The {@link ServiceManager} is responsible for starting and stopping services
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ServiceManager {
	/**
	 * The logger
	 */
	private final Logger logger;
	/**
	 * The Guice Injector
	 */
	private final Injector injector;

	@Inject
	public ServiceManager(Injector injector) {
		this.injector = injector;
		final LoggingService service = injector
				.getInstance(LoggingService.class);
		try {
			service.start();
		} catch (ServiceStartException e) {
			throw new RuntimeException(e);
		}
		logger = LoggerFactory.getLogger(ServiceManager.class);
	}

	public <T extends Service> T start(Class<T> serviceClass)
			throws ServiceStartException {
		final T service = injector.getInstance(serviceClass);
		if (service == null)
			return null;
		try {
			logger.info("{}: Starting service...",
					serviceClass.getCanonicalName());
			service.start();
			logger.info("{}: Service started!", serviceClass.getCanonicalName());
			return service;
		} catch (ServiceStartException e) {
			logger.error("{}: Error starting service: {}",
					serviceClass.getCanonicalName(), e.getCause());
			throw e;
		}
	}

	public void stop(Class<? extends Service> serviceClass)
			throws ServiceStopException {
		final Service service = injector.getInstance(serviceClass);
		if (service == null)
			return;
		try {
			logger.info("{0}: Stopping service...",
					serviceClass.getCanonicalName());
			service.stop();
			logger.info("{0}: Service stopped!",
					serviceClass.getCanonicalName());
		} catch (ServiceStopException e) {
			logger.error("{0}: Error stopping service: {1}",
					serviceClass.getCanonicalName(), e.getCause());
			throw e;
		}
	}

	public <T extends Service> T restart(Class<T> serviceClass)
			throws ServiceStartException, ServiceStopException {
		final T service = injector.getInstance(serviceClass);
		if (service == null)
			return null;
		try {
			logger.info("{0}: Restaring service...",
					serviceClass.getCanonicalName());
			service.stop();
			service.start();
			logger.info("{0}: Service restarted!",
					serviceClass.getCanonicalName());
			return service;
		} catch (ServiceStartException e) {
			logger.error("{0}: Error starting service: {1}",
					serviceClass.getCanonicalName(), e.getCause());
			throw e;
		} catch (ServiceStopException e) {
			logger.error("{0}: Error stopping service: {1}",
					serviceClass.getCanonicalName(), e.getCause());
			throw e;
		}
	}
}
