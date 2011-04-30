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
		Class<?> clazz = context.getClassLoader().loadClass("test.ScriptingCompilerTest");
		Assert.assertNotNull(clazz);
		Assert.assertEquals("ScriptingCompilerTest", clazz.getSimpleName());
	}
}
