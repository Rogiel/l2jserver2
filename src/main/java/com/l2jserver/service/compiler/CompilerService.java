package com.l2jserver.service.compiler;

import com.l2jserver.service.Service;

public interface CompilerService extends Service {
	Class<?> compile(byte[] clazz);
	ClassLoader getClassLoader();
}
