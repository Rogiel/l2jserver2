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

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class XMLConfigurationServiceTest {
	/**
	 * The {@link TestConfig} proxy
	 */
	private TestConfig config;

	@Before
	public void tearUp() throws ServiceStartException {
		final XMLConfigurationService service = new XMLConfigurationService(
				new File("src/test/resources/test-config.xml"));
		service.start();
		config = service.get(TestConfig.class);
	}

	@Test
	public void testString() throws ServiceStartException {
		Assert.assertEquals("test", config.getTestString());
	}

	@Test
	public void testDefaultValue() throws ServiceStartException {
		Assert.assertEquals("default", config.getDefaultTestString());
	}

	@Test
	public void testInteger() throws ServiceStartException {
		Assert.assertEquals(256, config.getTestInteger());
	}

	@Test
	public void testSetter() throws ServiceStartException {
		config.setTestString("new-value");
		Assert.assertEquals("new-value", config.getTestString());
	}

	@Test
	public void testNullSetter() throws ServiceStartException {
		config.setTestString(null);
		Assert.assertEquals("test-default", config.getTestString());
	}

	/**
	 * The TestConfig interface
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface TestConfig extends Configuration {
		@ConfigurationPropertyGetter(defaultValue = "test-default")
		@ConfigurationXPath("/configuration/test/testvalue")
		String getTestString();

		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/test/testvalue")
		void setTestString(String value);

		@ConfigurationPropertyGetter(defaultValue = "default")
		@ConfigurationXPath("/configuration/test/nonexistentkey")
		String getDefaultTestString();

		@ConfigurationPropertyGetter(defaultValue = "0")
		@ConfigurationXPath("/configuration/test/integer")
		int getTestInteger();

		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/test/integer")
		void setTestInteger(Integer n);
	}
}
