package com.l2jserver.service;

/**
 * An abstract service implementing basic life-cycle methods.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class AbstractService implements Service {
	@Override
	public void start() throws ServiceStartException {
	}

	@Override
	public void stop() throws ServiceStopException {
	}

	@Override
	public void restart() throws ServiceException {
		this.stop();
		this.start();
	}
}
