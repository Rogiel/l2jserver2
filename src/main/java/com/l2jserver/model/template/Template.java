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
package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.world.WorldObject;

/**
 * An template is like a base for an {@link WorldObject}. Normally, instead of
 * manually creating the object this can be done through templates, that easy
 * the need of setting the new objects parameters.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the {@link WorldObject} type
 */
public interface Template<T> {
	/**
	 * Create a new {@link WorldObject}
	 * 
	 * @return the created object
	 */
	T create();

	/**
	 * Return this {@link TemplateID}
	 * 
	 * @return the template id
	 */
	TemplateID<?> getID();
}
