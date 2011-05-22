package com.l2jserver.service.game.character;

import com.l2jserver.model.world.capability.Actor;

/**
 * Exception thrown when the character is trying to attack an {@link Actor} that
 * is not attackable
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActorIsNotAttackableServiceException extends
		CharacterServiceException {
	private static final long serialVersionUID = 1L;
}