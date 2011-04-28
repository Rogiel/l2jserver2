package com.l2jserver.service.logging;

public class JdkLogger implements Logger {
	private final java.util.logging.Logger logger;

	public JdkLogger(java.util.logging.Logger logger) {
		this.logger = logger;
	}

	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void info(String message, Exception e) {
		logger.info(message);
	}
}
