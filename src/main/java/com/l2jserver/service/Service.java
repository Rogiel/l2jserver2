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
}
