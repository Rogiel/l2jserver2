package com.l2jserver.service;

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
