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
package com.l2jserver.service.game.scripting.impl.ecj;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * This class represents URL Stream handler that accepts
 * {@value #HANDLER_PROTOCOL} protocol
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class EclipseCompilerVirtualClassURLStreamHandler extends URLStreamHandler {

	/**
	 * Script Handler protocol for classes compiled from source
	 */
	public static final String HANDLER_PROTOCOL = "aescript://";

	/**
	 * Script class loader that loaded those classes
	 */
	private final EclipseCompilerScriptClassLoader cl;

	/**
	 * Creates new instance of url stream handler with given classloader
	 * 
	 * @param cl
	 *            ScriptClassLoaderImpl that was used to load compiled class
	 */
	public EclipseCompilerVirtualClassURLStreamHandler(EclipseCompilerScriptClassLoader cl) {
		this.cl = cl;
	}

	/**
	 * Opens new URL connection for URL
	 * 
	 * @param u
	 *            url
	 * @return Opened connection
	 * @throws IOException
	 *             never thrown
	 */
	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		return new EclipseCompilerVirtualClassURLConnection(u, cl);
	}
}
