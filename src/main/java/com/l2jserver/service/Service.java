package com.l2jserver.service;

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
	 * @throws ServiceStartException
	 *             if an error occurred
	 */
	void stop() throws ServiceStopException;
}
