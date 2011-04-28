package com.l2jserver.service.configuration;

import com.l2jserver.service.configuration.Configuration.ConfigurationName;

@ConfigurationName("l2j")
public interface Configuration {

	public @interface ConfigurationName {
		String value();
	}

	public @interface ConfigurationPrefix {
		String value();
	}
	
	public @interface ConfigurationPropertyGetter {
		String name();
		String defaultValue() default "";
	}
	
	public @interface ConfigurationPropertySetter {
		String name();
	}
}
