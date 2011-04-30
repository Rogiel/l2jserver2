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

package com.l2jserver.service.game.scripting;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.Set;

/**
 * 
 * Abstract class loader that should be extended by child classloaders. If
 * needed, this class should wrap another classloader.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ScriptClassLoader extends URLClassLoader {

	/**
	 * Just for compatibility with {@link URLClassLoader}
	 * 
	 * @param urls
	 *            list of urls
	 * @param parent
	 *            parent classloader
	 */
	public ScriptClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	/**
	 * Just for compatibility with {@link URLClassLoader}
	 * 
	 * @param urls
	 *            list of urls
	 */
	public ScriptClassLoader(URL[] urls) {
		super(urls);
	}

	/**
	 * Just for compatibility with {@link URLClassLoader}
	 * 
	 * @param urls
	 *            list of urls
	 * @param parent
	 *            parent classloader
	 * @param factory
	 *            {@link java.net.URLStreamHandlerFactory}
	 */
	public ScriptClassLoader(URL[] urls, ClassLoader parent,
			URLStreamHandlerFactory factory) {
		super(urls, parent, factory);
	}

	/**
	 * Adds library to this classloader, it shuould be jar file
	 * 
	 * @param file
	 *            jar file
	 * @throws IOException
	 *             if can't add library
	 */
	public abstract void addLibrary(File file) throws IOException;

	/**
	 * Returns unmodifiable set of class names that were loaded from libraries
	 * 
	 * @return unmodifiable set of class names that were loaded from libraries
	 */
	public abstract Set<String> getLibraryClasses();

	/**
	 * Retuns unmodifiable set of class names that were compiled
	 * 
	 * @return unmodifiable set of class names that were compiled
	 */
	public abstract Set<String> getCompiledClasses();
}
