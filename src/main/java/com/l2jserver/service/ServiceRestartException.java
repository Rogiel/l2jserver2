package com.l2jserver.service;

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
