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
import java.util.List;

import com.l2jserver.service.game.scripting.CompilationResult;
import com.l2jserver.service.game.scripting.ScriptClassLoader;
import com.l2jserver.service.game.scripting.ScriptCompiler;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Wrapper for JavaCompiler api
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PrecompiledScriptCompiler implements ScriptCompiler {
	/**
	 * Parent classloader that has to be used for this compiler
	 */
	protected ScriptClassLoader parentClassLoader;

	/**
	 * Creates a new instance
	 */
	public PrecompiledScriptCompiler() {
	}

	@Override
	public void setParentClassLoader(ScriptClassLoader classLoader) {
		parentClassLoader = classLoader;
	}

	@Override
	public void setLibraries(Iterable<File> files) {
		// do nothing
	}

	@Override
	public CompilationResult compile(String className, byte[] sourceCode) {
		throw new UnsupportedOperationException(
				"This compiler cannot compile from source");
	}

	@Override
	public CompilationResult compile(String[] className, byte[][] sourceCode)
			throws IllegalArgumentException {
		throw new UnsupportedOperationException(
				"This compiler cannot compile from source");
	}

	@Override
	public CompilationResult compile(Iterable<File> compilationUnits) {
		final PrecompiledScriptClassLoader cl = new PrecompiledScriptClassLoader(
				this.getClass().getClassLoader());
		final List<Class<?>> classes = CollectionFactory.newList();
		for (final File file : compilationUnits) {
			try {
				classes.add(cl.loadClass(file));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return new CompilationResult(classes.toArray(new Class<?>[classes
				.size()]), cl);
	}

	/**
	 * Only class files are supported by this compiler
	 * 
	 * @return "class";
	 */
	@Override
	public String[] getSupportedFileTypes() {
		return new String[] { "class" };
	}
}
