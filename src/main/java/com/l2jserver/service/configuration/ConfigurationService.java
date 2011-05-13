package com.l2jserver.service.configuration;

import com.l2jserver.service.Service;

public interface ConfigurationService extends Service {
	<C extends Configuration> C get(Class<C> config);
}
