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
package com.l2jserver.service.game.scripting;

import java.io.File;

/**
 * This interface reperesents common functionality list that should be available
 * for any commpiler that is going to be used with scripting engine. For
 * instance, groovy can be used, hoever it produces by far not the best bytecode
 * so by default javac from sun is used.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ScriptCompiler {
	/**
	 * Sets parent class loader for this compiler.<br>
	 * <br>
	 * <font color="red">Warning, for now only</font>
	 * 
	 * @param classLoader
	 *            ScriptClassLoader that will be used as parent
	 */
	void setParentClassLoader(ScriptClassLoader classLoader);

	/**
	 * List of jar files that are required for compilation
	 * 
	 * @param files
	 *            list of jar files
	 */
	void setLibraries(Iterable<File> files);

	/**
	 * Compiles single class that is represented as string
	 * 
	 * @param className
	 *            class name
	 * @param sourceCode
	 *            class source code
	 * @return {@link CompilationResult}
	 */
	CompilationResult compile(String className, byte[] sourceCode);

	/**
	 * Compiles classes that are represented as strings
	 * 
	 * @param className
	 *            class names
	 * @param sourceCode
	 *            class sources
	 * @return {@link CompilationResult}
	 * @throws IllegalArgumentException
	 *             if number of class names != number of sources
	 */
	CompilationResult compile(String[] className, byte[][] sourceCode)
			throws IllegalArgumentException;

	/**
	 * Compiles list of files
	 * 
	 * @param compilationUnits
	 *            list of files
	 * @return {@link CompilationResult}
	 */
	CompilationResult compile(Iterable<File> compilationUnits);

	/**
	 * Returns array of supported file types. This files will be treated as
	 * source files.
	 * 
	 * @return array of supported file types.
	 */
	String[] getSupportedFileTypes();
}
