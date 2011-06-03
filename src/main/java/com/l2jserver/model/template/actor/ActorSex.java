package com.l2jserver.model.template.actor;

/**
 * Represent the sex of an actor.
 * <p>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum ActorSex {
	MALE(0x00), FEMALE(0x01);

	public final int option;

	ActorSex(int option) {
		this.option = option;
	}

	public static ActorSex fromOption(int option) {
		for (ActorSex sex : values()) {
			if (sex.option == option)
				return sex;
		}
		return null;
	}
}