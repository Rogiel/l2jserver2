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

import java.io.File;
import java.net.URI;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemManager;
import org.apache.commons.vfs.impl.DefaultFileSystemManager;
import org.apache.commons.vfs.impl.StandardFileSystemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;

/**
 * Default implementation for VFS system
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class VFSServiceImpl extends AbstractService implements VFSService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The CommonsVFS {@link FileSystemManager}
	 */
	private DefaultFileSystemManager manager;

	@Override
	protected void doStart() throws ServiceStartException {
		manager = new StandardFileSystemManager();
		try {
			manager.init();
			manager.setBaseFile(new File("./"));
		} catch (FileSystemException e) {
			manager = null;
			throw new ServiceStartException(e);
		}
	}

	@Override
	public FileObject resolve(URI uri) {
		return resolve(uri.toString());
	}

	@Override
	public FileObject resolve(String uri) {
		log.debug("Resolving file {}", uri);
		try {
			return manager.resolveFile(uri);
		} catch (FileSystemException e) {
			log.error("Error resolving file", e);
			return null;
		}
	}

	@Override
	protected void doStop() throws ServiceStopException {
		try {
			manager.getBaseFile().close();
		} catch (FileSystemException e) {
			throw new ServiceStopException(e);
		} finally {
			manager = null;
		}
	}
}
