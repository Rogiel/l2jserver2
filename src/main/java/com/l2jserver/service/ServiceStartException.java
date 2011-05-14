package com.l2jserver.service;

/**
 * Exception thrown when a service failed to start.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ServiceStartException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceStartException() {
		super();
	}

	public ServiceStartException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceStartException(String message) {
		super(message);
	}

	public ServiceStartException(Throwable cause) {
		super(cause);
	}
}
