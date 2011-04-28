package com.l2jserver.service.logging;

import com.l2jserver.service.Service;

public interface LoggingService extends Service {
	Logger getLogger(Class<?> clazz);
}
