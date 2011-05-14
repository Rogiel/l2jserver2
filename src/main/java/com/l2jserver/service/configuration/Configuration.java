package com.l2jserver.service.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Configuration interface
 * <p>
 * Each service desiring to use the configuration system must extend the
 * interface and define methods for getters and setters. Each method must be
 * annotated either with {@link ConfigurationPropertyGetter} or
 * {@link ConfigurationPropertySetter}
 * <p>
 * Each interface may optionally be annotated with {@link ConfigurationName}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Configuration {
	/**
	 * Each configuration can define the name of its configuration. This will be
	 * used by implementations to look for the configuration.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Target(value = ElementType.TYPE)
	public @interface ConfigurationName {
		/**
		 * @return the configuration name
		 */
		String value();
	}

	/**
	 * The getter method for an configuration property
	 * <p>
	 * The method must have no arguments.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface ConfigurationPropertyGetter {
		/**
		 * @return the property name
		 */
		String name();

		/**
		 * @return the default value to be used
		 */
		String defaultValue() default "";
	}

	/**
	 * The setter method for an configuration property.
	 * <p>
	 * The method must have a single argument and return type void.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	public @interface ConfigurationPropertySetter {
		/**
		 * @return the property name
		 */
		String name();
	}
}
