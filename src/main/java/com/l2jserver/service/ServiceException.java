package com.l2jserver.service;

/**
 * Exception for an {@link Service}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
