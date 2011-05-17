/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.Configuration.ConfigurationName;
import com.l2jserver.service.configuration.Configuration.ConfigurationPropertyGetter;
import com.l2jserver.service.configuration.Configuration.ConfigurationPropertySetter;
import com.l2jserver.service.logging.LoggingService;
import com.l2jserver.util.transformer.Transformer;
import com.l2jserver.util.transformer.TransformerFactory;

/**
 * Creates {@link Configuration} object through Java {@link Proxy}. Uses the
 * annotations in the interface to retrieve and store values.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, CacheService.class })
public class ProxyConfigurationService extends AbstractService implements
		ConfigurationService {
	/**
	 * The directory in which configuration files are stored
	 */
	private File directory = new File("./config");
	/**
	 * The logger
	 */
	private final Logger logger = LoggerFactory
			.getLogger(ProxyConfigurationService.class);

	/**
	 * The cache of configuration objects
	 */
	private Map<Class<?>, Object> cache = new WeakHashMap<Class<?>, Object>();

	@Override
	protected void doStart() throws ServiceStartException {
		if (!directory.exists())
			if (!directory.mkdirs())
				throw new ServiceStartException("Failed to create directories");
	}

	@Override
	@SuppressWarnings("unchecked")
	public <C extends Configuration> C get(Class<C> config) {
		if (cache.containsKey(config))
			return (C) cache.get(config);
		logger.info("Trying to create {} proxy", config);
		Properties properties;
		try {
			properties = findProperties(config);
		} catch (IOException e) {
			properties = new Properties();
			logger.info(
					"Configuration file for {} not found, falling back to default values",
					config);
		}
		C proxy = (C) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class<?>[] { config }, new ConfigInvocationHandler(
						properties));
		cache.put(config, proxy);
		return proxy;
	}

	/**
	 * The invocation handler for configuration interfaces
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class ConfigInvocationHandler implements InvocationHandler {
		private final Properties properties;
		private Map<String, Object> cache = new WeakHashMap<String, Object>();

		public ConfigInvocationHandler(Properties properties) {
			this.properties = properties;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			logger.debug("Configuration service, method invoked: {}",
					method.getName());
			if (args == null || args.length == 0) {
				final ConfigurationPropertyGetter getter = method
						.getAnnotation(ConfigurationPropertyGetter.class);
				if (getter == null)
					return null;
				return get(getter, method.getReturnType());
			} else if (args.length == 1) {
				final ConfigurationPropertySetter setter = method
						.getAnnotation(ConfigurationPropertySetter.class);
				if (setter == null)
					return null;
				set(setter, args[0], method.getParameterTypes()[0]);
			}
			return null;
		}

		/**
		 * Get the untransformed value of an property
		 * 
		 * @param getter
		 *            the getter annotation
		 * @param type
		 *            the transformed type
		 * @return the untransformed property
		 */
		private Object get(ConfigurationPropertyGetter getter, Class<?> type) {
			if (cache.containsKey(getter.name()))
				return cache.get(getter.name());
			Object o = untransform(
					getRaw(getter.name(), getter.defaultValue()), type);
			cache.put(getter.name(), o);
			return o;
		}

		/**
		 * Set the transformed value of an property
		 * 
		 * @param setter
		 *            the setter annotation
		 * @param value
		 *            the untransformed value
		 * @param type
		 *            the transformed type
		 */
		private void set(ConfigurationPropertySetter setter, Object value,
				Class<?> type) {
			if (value != null) {
				properties.setProperty(setter.name(),
						transform(value.toString(), type));
				cache.remove(setter.name());
			} else {
				properties.remove(setter.name());
				cache.remove(setter.name());
			}
		}

		/**
		 * Untransforms an value
		 * 
		 * @param value
		 *            the raw value
		 * @param type
		 *            the output type
		 * @return the untransformed value
		 */
		private Object untransform(String value, Class<?> type) {
			if (value == null)
				return null;
			if (type == String.class)
				return value;
			final Transformer<?> transformer = TransformerFactory
					.getTransfromer(type);
			if (transformer == null)
				return null;
			return transformer.untransform(value);
		}

		/**
		 * Transforms an value
		 * 
		 * @param value
		 *            the raw typed value
		 * @param type
		 *            the input type
		 * @return the string representing <tt>value</tt>
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private String transform(Object value, Class<?> type) {
			if (value == null)
				return null;
			if (value instanceof String)
				return (String) value;
			final Transformer transformer = TransformerFactory
					.getTransfromer(type);
			if (transformer == null)
				return null;
			return transformer.transform(value);
		}

		/**
		 * Retrieve the raw value from the property file
		 * 
		 * @param key
		 *            the key
		 * @param defaultValue
		 *            the default value
		 * @return the value found or default value
		 */
		private String getRaw(String key, String defaultValue) {
			if (properties == null)
				return defaultValue;
			if (properties.containsKey(key)) {
				return (String) properties.get(key);
			}
			return defaultValue;
		}
	}

	/**
	 * Tries to locate an .properties file
	 * 
	 * @param clazz
	 *            configuration interface class
	 * @return the found property
	 * @throws IOException
	 *             if any i/o error occur
	 */
	private Properties findProperties(Class<?> clazz) throws IOException {
		ConfigurationName config = findAnnotation(ConfigurationName.class,
				clazz);
		if (config == null)
			return null;
		final Properties prop = new Properties();
		final File file = new File(directory, config.value() + ".properties");
		final InputStream in = new FileInputStream(file);
		try {
			prop.load(in);
		} finally {
			in.close();
		}
		return prop;
	}

	/**
	 * Tries to find an annotation in the class or any parent-class.
	 * 
	 * @param <T>
	 *            the annotation type
	 * @param annotationClass
	 *            the annotation class
	 * @param clazz
	 *            the class to look for annotations
	 * @return the annotation found
	 */
	private <T extends Annotation> T findAnnotation(Class<T> annotationClass,
			Class<?> clazz) {
		T ann = clazz.getAnnotation(annotationClass);
		if (ann != null)
			return ann;

		for (Class<?> clazz2 : annotationClass.getInterfaces()) {
			if (clazz2 == clazz)
				continue;
			ann = findAnnotation(annotationClass, clazz2);
			if (ann != null)
				return ann;
		}
		return null;
	}

	/**
	 * @return the configuration store directory
	 */
	public File getDirectory() {
		return directory;
	}

	/**
	 * Set the configuration store directory
	 * 
	 * @param directory
	 *            the directory
	 */
	public void setDirectory(File directory) {
		this.directory = directory;
	}
}
