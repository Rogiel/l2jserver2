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
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;

public class ScriptingServiceImplTest {
	private final Injector injector = Guice
			.createInjector(new AbstractModule() {
				@Override
				protected void configure() {
					bind(ScriptingService.class).to(ScriptingServiceImpl.class)
							.in(Scopes.SINGLETON);
				}
			});
	private final ScriptingService service = injector
			.getInstance(ScriptingService.class);

	@Test
	public void testLoading() throws Exception {
		final List<ScriptContext> contexts = service.load(new File(
				"src/test/resources/scripting/testcase.xml"));
		Assert.assertEquals(1, contexts.size());
	}

	@Test
	public void testCreatingInstance() throws Exception {
		final List<ScriptContext> contexts = service.load(new File(
				"src/test/resources/scripting/testcase.xml"));
		Assert.assertEquals(1, contexts.size());
		final ScriptContext context = contexts.get(0);
		Class<?> clazz = context.getClassLoader().loadClass(
				"test.ScriptingCompilerTest");
		Assert.assertNotNull(clazz.newInstance());
		Assert.assertNotNull(clazz);
		Assert.assertEquals("test.ScriptingCompilerTest", clazz.getName());
	}
}
