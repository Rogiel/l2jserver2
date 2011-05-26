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

import java.net.URI;

import org.apache.commons.vfs.FileObject;

import com.l2jserver.service.Service;

/**
 * The VFS service provides access to files. With this service is possible to
 * change the location of files.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface VFSService extends Service {
	/**
	 * Resolves an file. If the file cannot be resolved, null will be returned.
	 * <p>
	 * Please note that event if the file DOES NOT exists a valid object will be
	 * returned.
	 * 
	 * @param uri
	 *            the file uri
	 * @return the resolved file. Will return null if could not resolve.
	 */
	FileObject resolve(URI uri);

	/**
	 * Resolves an file. If the file cannot be resolved, null will be returned.
	 * <p>
	 * Please note that event if the file DOES NOT exists a valid object will be
	 * returned.
	 * 
	 * @param uri
	 *            the file uri as an string
	 * @return the resolved file. Will return null if could not resolve.
	 */
	FileObject resolve(String uri);
}
