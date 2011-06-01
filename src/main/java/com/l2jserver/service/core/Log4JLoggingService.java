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
package com.l2jserver.service.core;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * Logging service implementation for Log4J
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Log4JLoggingService extends AbstractService implements
		LoggingService {
	private Logger rootLogger;
	private Logger l2jLogger;
	private Logger nettyLogger;

	@Override
	protected void doStart() throws ServiceStartException {
		final Layout layout = new PatternLayout(
				"[%p %d{yyyy-MM-dd HH-mm-ss}] %c:%L - %m%n");

		BasicConfigurator.configure();
		rootLogger = Logger.getRootLogger();
		l2jLogger = Logger.getLogger("com.l2jserver");
		nettyLogger = Logger.getLogger("org.jboss.netty");

		rootLogger.removeAllAppenders();
		rootLogger.setLevel(Level.WARN);
		rootLogger.addAppender(new ConsoleAppender(layout, "System.err"));

		l2jLogger.setLevel(Level.INFO);
		nettyLogger.setLevel(Level.DEBUG);
		Logger.getLogger("com.l2jserver.model.id.object.allocator").setLevel(
				Level.WARN);
	}

	@Override
	protected void doStop() throws ServiceStopException {
		BasicConfigurator.resetConfiguration();
	}
}
