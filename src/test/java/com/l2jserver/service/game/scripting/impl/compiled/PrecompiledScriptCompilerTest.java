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

import org.apache.commons.io.FileUtils;

import com.l2jserver.service.game.scripting.CompilationResult;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class PrecompiledScriptCompilerTest {
	/**
	 * Test method for
	 * {@link com.l2jserver.service.game.scripting.impl.compiled.PrecompiledScriptCompiler#compile(java.lang.Iterable)}
	 * .
	 */
	// @Test
	public void testCompileIterableOfFile() {
		final PrecompiledScriptCompiler compiler = new PrecompiledScriptCompiler();
		@SuppressWarnings("unchecked")
		final CompilationResult result = compiler.compile(FileUtils.listFiles(
				new File("target/scripts/script/template"),
				new String[] { "class" }, true));
		System.out.println(result.getCompiledClasses()[0]);
	}
}
