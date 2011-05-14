package com.l2jserver.service.logging;

import org.apache.log4j.BasicConfigurator;

import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStopException;

/**
 * Logging service implementation for Log4J
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Log4JLoggingService extends AbstractService implements
		LoggingService {
	@Override
	public void stop() throws ServiceStopException {
		BasicConfigurator.configure();
	}
}
