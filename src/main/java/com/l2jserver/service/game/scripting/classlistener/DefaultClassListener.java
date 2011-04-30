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

package com.l2jserver.service.game.scripting.classlistener;

import java.lang.reflect.Modifier;

import com.google.inject.Injector;
import com.l2jserver.util.ClassUtils;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class DefaultClassListener implements ClassListener {
	private final Injector injector;

	public DefaultClassListener(Injector injector) {
		this.injector = injector;
	}

	@Override
	public void postLoad(Class<?>... classes) {
		doMethodInvokeLoad(classes);
	}

	@Override
	public void preUnload(Class<?>... classes) {
		doMethodInvokeUnload(classes);
	}

	/**
	 * Actually invokes method where given interface class is present.
	 * 
	 * @param classes
	 *            Classes to scan for interface
	 */
	private void doMethodInvokeLoad(Class<?>[] classes) {
		for (final Class<?> clazz : classes) {
			if (!ClassUtils.isSubclass(clazz, Loader.class))
				continue;
			if (Modifier.isAbstract(clazz.getModifiers())
					|| Modifier.isInterface(clazz.getModifiers()))
				continue;
			final Loader loader = (Loader) injector.getInstance(clazz);
			loader.load(classes);
		}
	}

	/**
	 * Actually invokes method where given interface class is present.
	 * 
	 * @param classes
	 *            Classes to scan for interface
	 */
	private void doMethodInvokeUnload(Class<?>[] classes) {
		for (final Class<?> clazz : classes) {
			if (!ClassUtils.isSubclass(clazz, Unloader.class))
				continue;
			if (Modifier.isAbstract(clazz.getModifiers())
					|| Modifier.isInterface(clazz.getModifiers()))
				continue;
			final Unloader unloader = (Unloader) injector.getInstance(clazz);
			unloader.unload(classes);
		}
	}
}
