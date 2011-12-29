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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An abstract service implementing basic life-cycle methods.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class AbstractService implements Service {
	/**
	 * The service logger
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Running state of a service
	 */
	protected boolean running = false;

	@Override
	public final void start() throws ServiceStartException {
		if (running)
			throw new ServiceStartException("Service is already started");
		try {
			this.doStart();
			this.running = true;
		} catch (ServiceStartException e) {
			this.running = false;
			throw e;
		}
	}

	/**
	 * Starts the service. This method is invoked internally by {@link #start()}
	 * 
	 * @throws ServiceStartException
	 *             if any error occur while starting the service
	 */
	protected void doStart() throws ServiceStartException {
	}

	@Override
	public final void stop() throws ServiceStopException {
		if (!running)
			throw new ServiceStopException("Service is not started");
		try {
			this.doStop();
		} finally {
			this.running = false;
		}
	}

	/**
	 * Stops the service. This method is invoked internally by {@link #stop()}
	 * 
	 * @throws ServiceStopException
	 *             if any error occur while stopping the service
	 */
	protected void doStop() throws ServiceStopException {
	}

	@Override
	public void restart() throws ServiceException {
		this.stop();
		this.start();
	}

	@Override
	public boolean isStarted() {
		return running;
	}

	@Override
	public boolean isStopped() {
		return !running;
	}

	@Override
	public final Class<? extends Service>[] getDependencies() {
		final Depends deps = this.getClass().getAnnotation(Depends.class);
		if (deps == null)
			return null;
		return deps.value();
	}

	/**
	 * Service dependency metadata
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface Depends {
		/**
		 * @return the service's dependency
		 */
		Class<? extends Service>[] value();
	}
}
