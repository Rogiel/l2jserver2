package com.l2jserver.service.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface Configuration {
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Target(value = ElementType.TYPE)
	public @interface ConfigurationName {
		String value();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface ConfigurationPropertyGetter {
		String name();
		String defaultValue() default "";
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface ConfigurationPropertySetter {
		String name();
	}
}
