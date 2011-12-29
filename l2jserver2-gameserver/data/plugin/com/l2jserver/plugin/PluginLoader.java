package com.l2jserver.plugin;

import java.lang.reflect.Modifier;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.template.Template;
import com.l2jserver.service.game.scripting.classlistener.Loader;
import com.l2jserver.service.game.scripting.classlistener.Unloader;
import com.l2jserver.util.ClassUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Utility class that loads all Plugins in classPath of this script context.<br>
 * Plugin should be public, not abstract, not interface, must have default
 * constructor annotated with @Inject.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PluginLoader implements Loader, Unloader {
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory
			.getLogger(PluginLoader.class);

	@Inject
	public PluginLoader() {
	}

	@Override
	public void load(Class<?>[] classes) {

	}

	@Override
	public void unload(Class<?>[] classes) {

	}

	/**
	 * Returns list of suitable Template classes to load/unload
	 * 
	 * @param classes
	 *            loaded classes
	 * 
	 * @return list of Template classes to load/unload
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private static Set<Class<? extends Template>> getSuitableClasses(
			Class<?>[] classes) {
		final Set<Class<? extends Template>> suitable = CollectionFactory
				.newSet();
		for (Class<?> clazz : classes) {
			if (!ClassUtils.isSubclass(clazz, Template.class))
				continue;
			if (Modifier.isAbstract(clazz.getModifiers())
					|| Modifier.isInterface(clazz.getModifiers()))
				continue;
			if (!Modifier.isPublic(clazz.getModifiers()))
				continue;
			if (clazz.isAnnotationPresent(DisabledPlugin.class))
				continue;

			suitable.add((Class<? extends Template>) clazz);
		}

		return suitable;
	}
}
