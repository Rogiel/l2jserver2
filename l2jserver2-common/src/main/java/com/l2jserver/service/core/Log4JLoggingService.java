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
package com.l2jserver.service.core;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.l2jserver.service.AbstractConfigurableService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * Logging service implementation for Log4J
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Log4JLoggingService extends
		AbstractConfigurableService<LoggingServiceConfiguration> implements
		LoggingService {
	/**
	 * The root logger
	 */
	private Logger rootLogger;

	/**
	 * Creates a new instance
	 */
	public Log4JLoggingService() {
		super(LoggingServiceConfiguration.class);
	}

	@Override
	protected void doStart() throws ServiceStartException {
		final Layout layout = new PatternLayout(
				"[%p %d] %c{1} - %m%n");
		rootLogger = Logger.getRootLogger();

		rootLogger.removeAllAppenders();
		// rootLogger.setLevel(config.getLoggersNode().);
		rootLogger.addAppender(new ConsoleAppender(layout, "System.err"));

		final NodeList nodes = config.getLoggersNode().getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			final Node node = nodes.item(i);
			if (!"logger".equals(node.getNodeName()))
				continue;
			final NamedNodeMap attributes = node.getAttributes();
			final Logger logger = Logger.getLogger(attributes.getNamedItem(
					"name").getNodeValue());
			logger.setLevel(Level.toLevel(attributes.getNamedItem("level")
					.getNodeValue()));
		}
	}

	@Override
	protected void doStop() throws ServiceStopException {
		BasicConfigurator.resetConfiguration();
	}
}
