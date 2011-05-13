package com.l2jserver.service.logging;

import org.apache.log4j.BasicConfigurator;

import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStopException;

public class Log4JLoggingService extends AbstractService implements
		LoggingService {
	@Override
	public void stop() throws ServiceStopException {
		BasicConfigurator.configure();
	}
}
