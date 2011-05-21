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

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.util.ClassUtils;
import com.l2jserver.util.factory.CollectionFactory;

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

	private final Set<Service> knownServices = CollectionFactory.newSet();

	@Inject
	public ServiceManager(Injector injector) {
		this.injector = injector;
		final LoggingService service = injector
				.getInstance(LoggingService.class);
		knownServices.add(service);
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
		if (service.isStarted())
			return service;
		knownServices.add(service);
		try {
			startDependencies(service.getDependencies());
			logger.info("{}: Starting service...",
					serviceClass.getCanonicalName());
			service.start();
			logger.info("{}: Service started!", serviceClass.getCanonicalName());
			return service;
		} catch (ServiceStartException e) {
			logger.error("{}: Error starting service: {}",
					serviceClass.getCanonicalName(), e);
			throw e;
		}
	}

	private void startDependencies(Class<? extends Service>[] dependencies)
			throws ServiceStartException {
		if (dependencies == null)
			return;
		if (dependencies.length == 0)
			return;
		for (final Class<? extends Service> serviceClass : dependencies) {
			this.start(serviceClass);
		}
	}

	public void stop(Class<? extends Service> serviceClass)
			throws ServiceStopException {
		final Service service = injector.getInstance(serviceClass);
		if (service == null)
			return;
		if (service.isStopped())
			return;
		knownServices.add(service);
		try {
			logger.info("{0}: Stopping service...",
					serviceClass.getCanonicalName());
			stopDependencies(service);
			service.stop();
			logger.info("{0}: Service stopped!",
					serviceClass.getCanonicalName());
		} catch (ServiceStopException e) {
			logger.error("{0}: Error stopping service: {1}",
					serviceClass.getCanonicalName(), e.getCause());
			throw e;
		}
	}

	private void stopDependencies(Service service) throws ServiceStopException {
		final Set<Class<? extends Service>> dependencies = createStopDependencies(
				null, service);
		for (final Class<? extends Service> dependency : dependencies) {
			this.stop(dependency);
		}
	}

	private Set<Class<? extends Service>> createStopDependencies(
			Set<Class<? extends Service>> depends, Service serviceClass) {
		if (depends == null)
			depends = CollectionFactory.newSet();
		for (final Service service : knownServices) {
			if (service.getDependencies() == null
					|| service.getDependencies().length == 0)
				continue;
			for (final Class<? extends Service> dependency : service
					.getDependencies()) {
				if (!ClassUtils.isSubclass(service.getClass(), dependency))
					continue;
				depends.add(dependency);
				createStopDependencies(depends,
						injector.getInstance(dependency));
			}
		}
		return depends;
	}

	public <T extends Service> T restart(Class<T> serviceClass)
			throws ServiceException {
		final T service = injector.getInstance(serviceClass);
		if (service == null)
			return null;
		if (service.isStopped())
			throw new ServiceStopException("Service is already stopped");
		knownServices.add(service);
		try {
			logger.info("{0}: Restaring service...",
					serviceClass.getCanonicalName());
			service.restart();
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
		} catch (ServiceException e) {
			logger.error("{0}: Error restarting service: {1}",
					serviceClass.getCanonicalName(), e.getCause());
			throw e;
		}
	}
}
