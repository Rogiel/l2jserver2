package com.l2jserver.service;

/**
 * Thrown when an service failed to restart. It's <tt>cause</tt> can be an
 * {@link ServiceStartException} or {@link ServiceStopException}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ServiceRestartException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public ServiceRestartException() {
		super();
	}

	public ServiceRestartException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceRestartException(String message) {
		super(message);
	}

	public ServiceRestartException(Throwable cause) {
		super(cause);
	}
}
