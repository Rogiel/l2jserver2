package com.l2jserver.service.game.npc;

import com.l2jserver.util.exception.L2Exception;

/**
 * Exception thrown when the action implementation thrown an exception. Will
 * always contain an <tt>cause</tt>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ActionServiceException extends NPCServiceException {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance
	 * 
	 * @param cause
	 *            the cause
	 */
	public ActionServiceException(L2Exception cause) {
		super(cause);
	}
}