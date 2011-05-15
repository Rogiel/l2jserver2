
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
package script.template;

import java.lang.reflect.Modifier;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.template.Template;
import com.l2jserver.service.game.scripting.classlistener.Loader;
import com.l2jserver.service.game.scripting.classlistener.Unloader;
import com.l2jserver.service.game.template.ScriptTemplateService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.util.ClassUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Utility class that loads all Template's in classPath of this script context.<br>
 * Template should be public, not abstract, not interface, must have default
 * constructor annotated with @Inject.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TemplateLoader implements Loader, Unloader {
	private static final Logger log = LoggerFactory
			.getLogger(TemplateLoader.class);

	private final ScriptTemplateService templateService;

	@Inject
	public TemplateLoader(TemplateService templateService) {
		this.templateService = (ScriptTemplateService) templateService;
	}

	@Override
	public void load(Class<?>[] classes) {
		log.debug("Loading templates from {} classes", classes.length);
		for (final Class<? extends Template<?>> template : getSuitableClasses(classes)) {
			log.debug("Found loadable template class: {}", template);
			templateService.addTemplate(template);
		}
	}

	@Override
	public void unload(Class<?>[] classes) {
		log.debug("Unloading templates from {} classes", classes.length);
		for (final Class<? extends Template<?>> template : getSuitableClasses(classes)) {
			log.debug("Found unloadable template class: {}", template);
			// TODO unloading
		}
	}

	/**
	 * Returns list of suitable Template classes to load/unload
	 * 
	 * @return list of Template classes to load/unload
	 */
	@SuppressWarnings({ "unchecked" })
	private static Set<Class<? extends Template<?>>> getSuitableClasses(
			Class<?>[] classes) {
		final Set<Class<? extends Template<?>>> suitable = CollectionFactory
				.newSet(null);
		for (Class<?> clazz : classes) {
			if (!ClassUtils.isSubclass(clazz, Template.class))
				continue;
			if (Modifier.isAbstract(clazz.getModifiers())
					|| Modifier.isInterface(clazz.getModifiers()))
				continue;
			if (!Modifier.isPublic(clazz.getModifiers()))
				continue;
			if (clazz.isAnnotationPresent(DisabledTemplate.class))
				continue;

			suitable.add((Class<? extends Template<?>>) clazz);
		}

		return suitable;
	}
}
