/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.configuration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.l2jserver.service.configuration.ProxyConfigurationService.ConfigurationName;

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
	 * The getter method for an configuration property
	 * <p>
	 * The method must have no arguments.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(value = ElementType.METHOD)
	@Documented
	public @interface ConfigurationPropertyGetter {
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
	@Documented
	public @interface ConfigurationPropertySetter {
	}
}
