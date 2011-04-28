package com.l2jserver.service.logging;

import java.util.logging.LogManager;

import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

public class JdkLoggingService implements LoggingService {
	@Override
	public void start() throws ServiceStartException {
		// TODO Auto-generated method stub

	}

	@Override
	public Logger getLogger(Class<?> clazz) {
		return new JdkLogger(LogManager.getLogManager().getLogger(
				clazz.getName()));
	}

	@Override
	public void stop() throws ServiceStopException {
		// TODO Auto-generated method stub

	}
}
