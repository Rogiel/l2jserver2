package com.l2jserver.service.configuration;

public interface ConfigurationService {
	<C extends Configuration> C get(Class<C> config);
}
