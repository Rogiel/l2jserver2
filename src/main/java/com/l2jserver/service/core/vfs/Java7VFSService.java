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
package com.l2jserver.service.core.vfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.configuration.ConfigurationService;

/**
 * Implementation of {@link VFSService} using default Java7 APIs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Java7VFSService extends AbstractService implements VFSService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final Java7VFSConfiguration config;

	/**
	 * The {@link FileSystem} implementation
	 */
	private FileSystem fs;
	/**
	 * The root {@link Path} of the server data
	 */
	private Path root;

	/**
	 * Configuration interface for {@link Java7VFSService}.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface Java7VFSConfiguration extends VFSConfiguration {
	}

	@Inject
	protected Java7VFSService(final ConfigurationService configService) {
		this.config = configService.get(Java7VFSConfiguration.class);
	}

	@Override
	protected void doStart() throws ServiceStartException {
		try {
			fs = FileSystems.getFileSystem(new URI("file:///"));
		} catch (URISyntaxException e) {
			throw new ServiceStartException(e);
		}
		root = config.getRoot().toAbsolutePath();
		log.debug("Root path is {}", root);
	}

	@Override
	public Path resolve(String path) {
		log.debug("Resolving file {}", path);
		return root.resolve(path);
	}

	@Override
	protected void doStop() throws ServiceStopException {
		try {
			fs.close();
		} catch (IOException e) {
			throw new ServiceStopException(e);
		} finally {
			fs = null;
		}
	}
}
