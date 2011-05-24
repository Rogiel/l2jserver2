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
package com.l2jserver.service.game.scripting.impl.javacc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.tools.JavaFileObject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.service.game.scripting.ScriptClassLoader;
import com.l2jserver.service.game.scripting.impl.BinaryClass;
import com.l2jserver.util.ClassUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This classloader is used to load script classes. <br>
 * <br>
 * Due to JavaCompiler limitations we have to keep list of available classes
 * here.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ScriptClassLoaderImpl extends ScriptClassLoader {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ScriptClassLoaderImpl.class);

	/**
	 * URL Stream handler to allow valid url generation by
	 * {@link #getResource(String)}
	 */
	private final VirtualClassURLStreamHandler urlStreamHandler = new VirtualClassURLStreamHandler(
			this);

	/**
	 * ClassFileManager that is related to this ClassLoader
	 */
	private final ClassFileManager classFileManager;

	/**
	 * Classes that were loaded from libraries. They are no parsed for any
	 * annotations, but they are needed by JavaCompiler to perform valid
	 * compilation
	 */
	private Set<String> libraryClasses = CollectionFactory.newSet();

	/**
	 * Creates new ScriptClassLoader with given ClassFileManger
	 * 
	 * @param classFileManager
	 *            classFileManager of this classLoader
	 */
	ScriptClassLoaderImpl(ClassFileManager classFileManager) {
		super(new URL[] {});
		this.classFileManager = classFileManager;
	}

	/**
	 * Creates new ScriptClassLoader with given ClassFileManger and another
	 * classLoader as parent
	 * 
	 * @param classFileManager
	 *            classFileManager of this classLoader
	 * @param parent
	 *            parent classLoader
	 */
	ScriptClassLoaderImpl(ClassFileManager classFileManager, ClassLoader parent) {
		super(new URL[] {}, parent);
		this.classFileManager = classFileManager;
	}

	/**
	 * Returns ClassFileManager that is related to this ClassLoader
	 * 
	 * @return classFileManager of this classLoader
	 */
	public ClassFileManager getClassFileManager() {
		return classFileManager;
	}

	/**
	 * AddsLibrary jar
	 * 
	 * @param file
	 *            jar file to add
	 * @throws IOException
	 */
	@Override
	public void addLibrary(File file) throws IOException {
		URL fileURL = file.toURI().toURL();
		addURL(fileURL);

		JarFile jarFile = new JarFile(file);

		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();

			String name = entry.getName();
			if (name.endsWith(".class")) {
				name = name.substring(0, name.length() - 6);
				name = name.replace('/', '.');
				libraryClasses.add(name);
			}
		}

		jarFile.close();
	}

	/**
	 * Loads class from library, parent or compiled
	 * 
	 * @param name
	 *            class to load
	 * @return loaded class
	 * @throws ClassNotFoundException
	 *             if class not found
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		BinaryClass bc = classFileManager.getCompiledClasses().get(name);
		if (bc == null) {
			return super.loadClass(name, true);
		}

		Class<?> c = bc.getDefinedClass();
		if (c == null) {
			byte[] b = bc.getBytes();
			c = super.defineClass(name, b, 0, b.length);
			bc.setDefinedClass(c);
		}
		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL getResource(String name) {

		if (!name.endsWith(".class")) {
			return super.getResource(name);
		} else {
			String newName = name.substring(0, name.length() - 6);
			newName = newName.replace('/', '.');
			if (classFileManager.getCompiledClasses().containsKey(newName)) {
				try {
					return new URL(null,
							VirtualClassURLStreamHandler.HANDLER_PROTOCOL
									+ newName, urlStreamHandler);
				} catch (MalformedURLException e) {
					log.error("Can't create url for compiled class", e);
				}
			}
		}

		return super.getResource(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getLibraryClasses() {
		return Collections.unmodifiableSet(libraryClasses);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getCompiledClasses() {
		Set<String> compiledClasses = classFileManager.getCompiledClasses()
				.keySet();
		return Collections.unmodifiableSet(compiledClasses);
	}

	/**
	 * Returns list of classes that are members of a package
	 * 
	 * @param packageName
	 *            package to search for classes
	 * @return list of classes that are package members
	 * @throws IOException
	 *             if was unable to load class
	 */
	public Set<JavaFileObject> getClassesForPackage(String packageName)
			throws IOException {
		Set<JavaFileObject> result = CollectionFactory.newSet();

		// load parent
		ClassLoader parent = getParent();
		if (parent instanceof ScriptClassLoaderImpl) {
			ScriptClassLoaderImpl pscl = (ScriptClassLoaderImpl) parent;
			result.addAll(pscl.getClassesForPackage(packageName));
		}

		// load current classloader compiled classes
		for (String cn : classFileManager.getCompiledClasses().keySet()) {
			if (ClassUtils.isPackageMember(cn, packageName)) {
				BinaryClass bc = classFileManager.getCompiledClasses().get(cn);
				result.add(bc);
			}
		}

		// load libraries
		for (String cn : libraryClasses) {
			if (ClassUtils.isPackageMember(cn, packageName)) {
				BinaryClass bc = new BinaryClass(cn);
				try {
					byte[] data = getRawClassByName(cn);
					OutputStream os = bc.openOutputStream();
					os.write(data);
				} catch (IOException e) {
					log.error("Error while loading class from package "
							+ packageName, e);
					throw e;
				}
				result.add(bc);
			}
		}

		return result;
	}

	/**
	 * Finds class with the specified name from the URL search path. Any URLs
	 * referring to JAR files are loaded and opened as needed until the class is
	 * found.
	 * 
	 * @param name
	 *            the name of the class
	 * @return the resulting class data
	 * @throws IOException
	 *             if the class could not be found
	 */
	protected byte[] getRawClassByName(String name) throws IOException {
		URL resource = findResource(name.replace('.', '/').concat(".class"));
		InputStream is = null;
		byte[] clazz = null;

		try {
			is = resource.openStream();
			clazz = IOUtils.toByteArray(is);
		} catch (IOException e) {
			log.error("Error while loading class data", e);
			throw e;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					log.error("Error while closing stream", e);
				}
			}
		}
		return clazz;
	}
}
