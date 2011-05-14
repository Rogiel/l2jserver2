package com.l2jserver.service;

/**
 * Exception thrown when a service failed to stop
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ServiceStopException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceStopException() {
		super();
	}

	public ServiceStopException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceStopException(String message) {
		super(message);
	}

	public ServiceStopException(Throwable cause) {
		super(cause);
	}
}
