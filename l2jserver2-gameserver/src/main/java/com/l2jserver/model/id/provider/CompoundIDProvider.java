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
package com.l2jserver.model.id.provider;

import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.compound.AbstractCompoundID;

/**
 * The ID factory is used to create instances of IDs. It will automatically make
 * sure the ID is free before allocating it.
 * 
 * @param <I1>
 *            the first compound {@link ID} type
 * @param <I2>
 *            the second compound {@link ID} type
 * @param <T>
 *            the {@link CompoundIDProvider} type
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface CompoundIDProvider<I1 extends ID<?>, I2 extends ID<?>, T extends AbstractCompoundID<I1, I2>> {
	/**
	 * Creates the ID object for an <b>EXISTING</b> ID.
	 * 
	 * @param id1
	 *            the first id
	 * @param id2
	 *            the second id
	 * @return the created compound {@link ID}
	 */
	T createID(@Assisted("id1") I1 id1, @Assisted("id2") I2 id2);

}
