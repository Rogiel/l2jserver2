package com.l2jserver.model.template.actor;

/**
 * Represent the sex of an actor.
 * <p>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ActorSex {
	/**
	 * Represents an male character
	 */
	MALE(0x00),
	/**
	 * Represents an female character
	 */
	FEMALE(0x01);

	/**
	 * The numeric sex option
	 */
	public final int option;

	/**
	 * @param option
	 *            the numeric sex option
	 */
	ActorSex(int option) {
		this.option = option;
	}

	/**
	 * @param option
	 *            the numeric sex option
	 * @return the resolved {@link ActorSex}
	 */
	public static ActorSex fromOption(int option) {
		for (ActorSex sex : values()) {
			if (sex.option == option)
				return sex;
		}
		return null;
	}
}