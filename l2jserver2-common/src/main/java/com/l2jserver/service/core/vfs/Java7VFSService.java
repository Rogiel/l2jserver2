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
package com.l2jserver.service.core.vfs;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.service.AbstractConfigurableService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * Implementation of {@link VFSService} using default Java7 APIs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Java7VFSService extends
		AbstractConfigurableService<Java7VFSConfiguration> implements
		VFSService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The root {@link Path} of the server data
	 */
	private Path root;
	/**
	 * The root for the "data" folder
	 */
	private Path dataRoot;

	/**
	 * Creates a new instance
	 */
	@Inject
	public Java7VFSService() {
		super(Java7VFSConfiguration.class);
	}

	@Override
	protected void doStart() throws ServiceStartException {
		root = config.getRoot().toAbsolutePath();
		log.debug("Root path is {}", root);

		dataRoot = root.resolve(config.getDataPath());
		log.debug("Data root path is {}", root);
	}

	@Override
	public Path resolve(String path) {
		log.debug("Resolving file {}", path);
		return root.resolve(path);
	}

	@Override
	public Path resolveDataFile(String path) {
		log.debug("Resolving data file {}", path);
		return dataRoot.resolve(path);
	}

	@Override
	protected void doStop() throws ServiceStopException {
		root = null;
		dataRoot = null;
	}
}
