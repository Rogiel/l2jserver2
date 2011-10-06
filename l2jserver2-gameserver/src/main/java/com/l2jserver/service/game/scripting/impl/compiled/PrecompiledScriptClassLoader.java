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
package com.l2jserver.service.game.scripting.impl.compiled;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.l2jserver.service.game.scripting.ScriptClassLoader;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This classloader is used to load script classes. <br>
 * <br>
 * Due to JavaCompiler limitations we have to keep list of available classes
 * here.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PrecompiledScriptClassLoader extends ScriptClassLoader {
	/**
	 * Map of all loaded classes
	 */
	private final Map<String, Class<?>> classes = CollectionFactory.newMap();

	/**
	 * Creates new ScriptClassLoader with given ClassFileManger
	 * 
	 * @param root
	 *            the root file
	 */
	PrecompiledScriptClassLoader(final File root) {
		super(new URL[] {});
	}

	/**
	 * Creates new ScriptClassLoader with given ClassFileManger and another
	 * classLoader as parent
	 * 
	 * @param parent
	 *            parent classLoader
	 */
	PrecompiledScriptClassLoader(ClassLoader parent) {
		super(new URL[] {}, parent);
	}

	/**
	 * AddsLibrary jar
	 * 
	 * @param file
	 *            jar file to add
	 * @throws IOException
	 *             if any I/O error occur
	 */
	@Override
	public void addLibrary(File file) throws IOException {
	}

	/**
	 * Loads class from library, parent or compiled
	 * 
	 * @param file
	 *            the class file
	 * @return loaded class
	 * @throws ClassNotFoundException
	 *             if class not found
	 * @throws IOException
	 *             if any I/O error occur
	 */
	public Class<?> loadClass(File file) throws ClassNotFoundException,
			IOException {
		byte[] b = FileUtils.readFileToByteArray(file);
		Class<?> c = super.defineClass(null, b, 0, b.length);
		classes.put(c.getName(), c);
		return c;
	}

	@Override
	public synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		final File file = new File("target/templates");
		final File classFile = new File(file, name.replaceAll("\\.", "/")
				+ ".class");
		System.out.println(name + " -> " + classFile);
		if (!classFile.exists()) {
			System.out.println("super");
			return super.loadClass(name, resolve);
		} else if (classes.containsKey(name)) {
			return classes.get(name);
		} else {
			try {
				byte[] b = FileUtils.readFileToByteArray(classFile);
				System.out.println("Defining...");
				Class<?> c = super.defineClass(null, b, 0, b.length);
				classes.put(c.getName(), c);
				System.out.println(c.getName());

				return c;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getLibraryClasses() {
		return Collections.emptySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getCompiledClasses() {
		return Collections.unmodifiableSet(classes.keySet());
	}
}
