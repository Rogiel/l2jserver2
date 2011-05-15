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
package com.l2jserver.model.id;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Each account is identified by its {@link ID}. This {@link ID} is equal to the
 * account username or login.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class AccountID extends ID<String> {
	/**
	 * Creates a new instance
	 * 
	 * @param login
	 *            the login
	 */
	@Inject
	public AccountID(@Assisted String login) {
		super(login);
	}
}
