package script.template;/*
 * This file is part of aion-emu <aion-emu.com>.
 *
 * aion-emu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-emu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.lang.reflect.Modifier;
import java.util.Set;

import com.google.inject.Inject;
import com.l2jserver.model.template.Template;
import com.l2jserver.service.game.scripting.classlistener.Loader;
import com.l2jserver.service.game.scripting.classlistener.Unloader;
import com.l2jserver.service.game.template.StaticTemplateService;
import com.l2jserver.service.game.template.TemplateService;
import com.l2jserver.util.ClassUtils;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Utility class that loads all Template's in classPath of this script context.<br>
 * Template should be public, not abstract, not interface, must have default
 * no-arg public constructor.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TemplateLoader implements Loader, Unloader {
	private final StaticTemplateService templateService;

	@Inject
	public TemplateLoader(TemplateService templateService) {
		this.templateService = (StaticTemplateService) templateService;
	}

	@Override
	public void load(Class<?>[] classes) {
		for (final Class<? extends Template> template : getSuitableClasses(classes)) {
			templateService.addTemplate(template);
			System.out.println("Loading template: " + template);
		}
	}

	@Override
	public void unload(Class<?>[] classes) {
		for (final Class<? extends Template> template : getSuitableClasses(classes)) {
			System.out.println("Unloading template: " + template);
		}
	}

	/**
	 * Returns list of suitable Template classes to load/unload
	 * 
	 * @return list of Template classes to load/unload
	 */
	@SuppressWarnings({ "unchecked" })
	private static Set<Class<? extends Template>> getSuitableClasses(
			Class<?>[] classes) {
		final Set<Class<? extends Template>> suitable = CollectionFactory
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

			suitable.add((Class<? extends Template>) clazz);
		}

		return suitable;
	}
}
