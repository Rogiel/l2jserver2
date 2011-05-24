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
package com.l2jserver.service.game.scripting.impl;

import java.net.URI;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

/**
 * This class allows us to compile sources that are located only in memory.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class JavaSourceFromByteArray extends SimpleJavaFileObject {
	/**
	 * Source code of the class
	 */
	private final byte[] code;

	/**
	 * Creates new object that contains sources of java class
	 * 
	 * @param className
	 *            class name of class
	 * @param code
	 *            source code of class
	 */
	public JavaSourceFromByteArray(String className, byte[] code) {
		super(URI.create("string:///" + className.replace('.', '/')
				+ JavaFileObject.Kind.SOURCE.extension),
				JavaFileObject.Kind.SOURCE);
		this.code = code;
	}

	/**
	 * Creates new object that contains sources of java class
	 * 
	 * @param className
	 *            class name of class
	 * @param code
	 *            source code of class
	 */
	public JavaSourceFromByteArray(String className, byte[] code,
			JavaFileObject.Kind kind) {
		super(URI.create("string:///" + className.replace('.', '/')
				+ JavaFileObject.Kind.SOURCE.extension), kind);
		this.code = code;
	}

	/**
	 * Returns class source code
	 * 
	 * @param ignoreEncodingErrors
	 *            not used
	 * @return class source code
	 */
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return new String(code);
	}
}
