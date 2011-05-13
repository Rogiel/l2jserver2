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
