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

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.Configuration.ConfigurationPropertyGetter;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.transformer.Transformer;
import com.l2jserver.util.transformer.TransformerFactory;

/**
 * Creates {@link Configuration} object through Java {@link Proxy}. Uses the
 * annotations in the interface to retrieve and store values.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, CacheService.class })
public class XMLConfigurationService extends AbstractService implements
		ConfigurationService {
	/**
	 * The directory in which configuration files are stored
	 */
	private File file = new File("./config/config.xml");
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The DOM {@link DocumentBuilderFactory}
	 */
	private DocumentBuilderFactory factory;
	/**
	 * The DOM {@link DocumentBuilder}
	 */
	private DocumentBuilder builder;

	/**
	 * The XML {@link Document} containing configuration data
	 */
	private Document properties;

	/**
	 * The cache of configuration objects
	 */
	private Map<Class<?>, Object> cache = CollectionFactory.newWeakMap();

	/**
	 * Defines the XPath for the configuration parameter
	 * 
	 * @author Rogiel
	 */
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ConfigurationXPath {
		/**
		 * @return the xml path for the configuration
		 */
		String value();
	}

	/**
	 * Creates a new empty instance
	 */
	@Inject
	protected XMLConfigurationService() {
	}

	/**
	 * Creates a new service instance. <b>This is used for tests</b>
	 * 
	 * @param file
	 *            the configuration file
	 */
	protected XMLConfigurationService(File file) {
		this.file = file;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
			properties = builder.parse(file);
		} catch (ParserConfigurationException e) {
			throw new ServiceStartException(e);
		} catch (SAXException e) {
			throw new ServiceStartException(e);
		} catch (IOException e) {
			throw new ServiceStartException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <C extends Configuration> C get(Class<C> config) {
		Preconditions.checkNotNull(config, "config");

		if (cache.containsKey(config))
			return (C) cache.get(config);
		log.debug("Trying to create {} proxy", config);

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
		/**
		 * The invocation handler properties
		 */
		private final Document properties;
		/**
		 * The invocation cache
		 */
		private Map<String, Object> cache = CollectionFactory.newWeakMap();

		/**
		 * @param properties
		 *            the properties
		 */
		public ConfigInvocationHandler(Document properties) {
			this.properties = properties;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			log.debug("Configuration service, method invoked: {}",
					method.getName());
			if (args == null || args.length == 0) {
				final ConfigurationPropertyGetter getter = method
						.getAnnotation(ConfigurationPropertyGetter.class);
				final ConfigurationXPath xpath = method
						.getAnnotation(ConfigurationXPath.class);
				if (getter == null)
					return null;
				if (xpath == null)
					return null;
				return get(getter, xpath, method.getReturnType());
			} else if (args.length == 1) {
				final ConfigurationXPath xpath = method
						.getAnnotation(ConfigurationXPath.class);
				if (xpath == null)
					return null;
				set(xpath, args[0], method.getParameterTypes()[0]);
			}
			return null;
		}

		/**
		 * Get the untransformed value of an property
		 * 
		 * @param getter
		 *            the getter annotation
		 * @param xpath
		 *            the xpath annotation
		 * @param type
		 *            the transformed type
		 * @return the untransformed property
		 */
		private Object get(ConfigurationPropertyGetter getter,
				ConfigurationXPath xpath, Class<?> type) {
			if (cache.containsKey(xpath.value()))
				return cache.get(xpath.value());
			Object o;
			try {
				o = untransform(getRaw(xpath.value(), getter.defaultValue()),
						type);
			} catch (XPathExpressionException e) {
				return null;
			}
			cache.put(xpath.value(), o);
			return o;
		}

		/**
		 * Set the transformed value of an property
		 * 
		 * @param xpath
		 *            the xpath annotation
		 * @param value
		 *            the untransformed value
		 * @param type
		 *            the transformed type
		 * @throws XPathExpressionException
		 *             if any error occur while compiling the XPath
		 */
		private void set(ConfigurationXPath xpath, Object value, Class<?> type)
				throws XPathExpressionException {
			Node node = (Node) XPathFactory.newInstance().newXPath()
					.compile(xpath.value())
					.evaluate(properties, XPathConstants.NODE);
			if (value != null) {
				node.setNodeValue(transform(value.toString(), type));
				cache.put(xpath.value(), value);
			} else {
				node.getParentNode().removeChild(node);
				cache.remove(xpath.value());
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
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private Object untransform(String value, Class<?> type) {
			if (value == null)
				return null;
			if (type == String.class)
				return value;
			final Transformer transformer = TransformerFactory
					.getTransfromer(type);
			if (transformer == null)
				return null;
			return transformer.untransform(type, value);
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
			return transformer.transform(type, value);
		}

		/**
		 * Retrieve the raw value from the property file
		 * 
		 * @param key
		 *            the key
		 * @param defaultValue
		 *            the default value
		 * @return the value found or default value
		 * @throws XPathExpressionException
		 *             if any XPath exception occur
		 */
		private String getRaw(String key, String defaultValue)
				throws XPathExpressionException {
			if (properties == null)
				return defaultValue;
			String value = XPathFactory.newInstance().newXPath()
					.evaluate(key, properties);
			if (value == null || value.length() == 0)
				return defaultValue;
			return value;
		}
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
		Preconditions.checkNotNull(annotationClass, "annotationClass");
		Preconditions.checkNotNull(clazz, "clazz");

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
	public File getFile() {
		return file;
	}
}
