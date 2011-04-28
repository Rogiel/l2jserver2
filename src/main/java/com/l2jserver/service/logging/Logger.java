package com.l2jserver.service.logging;

public interface Logger {
	void info(String message);

	void info(String message, Exception e);
}
