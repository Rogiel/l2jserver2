package com.l2jserver.model.id;

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
	public AccountID(@Assisted String login) {
		super(login);
	}
}
