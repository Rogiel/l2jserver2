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
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.configuration.Configuration.ConfigurationName;
import com.l2jserver.service.configuration.Configuration.ConfigurationPropertyGetter;
import com.l2jserver.service.configuration.Configuration.ConfigurationPropertySetter;
import com.l2jserver.util.transformer.Transformer;
import com.l2jserver.util.transformer.TransformerFactory;

public class ProxyConfigurationService extends AbstractService implements
		ConfigurationService {
	private File directory = new File("./config");
	private final Logger logger = LoggerFactory
			.getLogger(ProxyConfigurationService.class);

	private Map<Class<?>, Object> cache = new WeakHashMap<Class<?>, Object>();

	@Override
	public void start() throws ServiceStartException {
		if (!directory.exists())
			directory.mkdirs();
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

	private class ConfigInvocationHandler implements InvocationHandler {
		private final Properties properties;
		private Map<String, Object> cache = new WeakHashMap<String, Object>();

		public ConfigInvocationHandler(Properties properties) {
			this.properties = properties;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			logger.debug("Configuration service, method invoked: {}", method.getName());
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

		private Object get(ConfigurationPropertyGetter getter, Class<?> type) {
			if (cache.containsKey(getter.name()))
				return cache.get(getter.name());
			Object o = untransform(
					getRaw(getter.name(), getter.defaultValue()), type);
			cache.put(getter.name(), o);
			return o;
		}

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

		private String getRaw(String key, String defaultValue) {
			if (properties == null)
				return defaultValue;
			if (properties.containsKey(key)) {
				return (String) properties.get(key);
			}
			return defaultValue;
		}
	}

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

	public File getDirectory() {
		return directory;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}
}
