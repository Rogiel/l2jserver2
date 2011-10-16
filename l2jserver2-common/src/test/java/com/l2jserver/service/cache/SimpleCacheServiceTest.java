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
package com.l2jserver.service.cache;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.l2jserver.service.ServiceStartException;

public class SimpleCacheServiceTest {
	private final EhCacheService cacheService = new EhCacheService();

	@Before
	public void tearUp() throws ServiceStartException {
		cacheService.start();
	}

	@Test
	public void testNoArgs() {
		final TestCacheable cached = cacheService.decorate(TestCacheable.class,
				new TestCacheableInstance());
		int output1 = cached.random();
		int output2 = cached.random();
		Assert.assertEquals(output1, output2);
	}

	@Test
	public void testSameArgs() {
		final TestCacheable cached = cacheService.decorate(TestCacheable.class,
				new TestCacheableInstance());
		int output1 = cached.random(10);
		int output2 = cached.random(10);
		Assert.assertEquals(output1, output2);
	}

	@Test
	public void testDifferentArgs() {
		final TestCacheable cached = cacheService.decorate(TestCacheable.class,
				new TestCacheableInstance());
		int output1 = cached.random(10);
		int output2 = cached.random(20);
		Assert.assertFalse(output1 == output2);
	}

	@Test
	public void testIgnoreCaching() {
		final TestCacheable cached = cacheService.decorate(TestCacheable.class,
				new TestCacheableInstance());
		int output1 = cached.notCached();
		int output2 = cached.notCached();
		Assert.assertFalse(output1 == output2);
	}

	public interface TestCacheable extends Cacheable {
		public int random();

		public int random(int arg);

		@IgnoreCaching
		public int notCached();
	}

	public static class TestCacheableInstance implements TestCacheable {
		private final Random random = new Random();

		@Override
		public int random() {
			return random.nextInt(Integer.MAX_VALUE);
		}

		@Override
		public int random(int arg) {
			return random.nextInt(Integer.MAX_VALUE);
		}

		@Override
		public int notCached() {
			return random.nextInt(Integer.MAX_VALUE);
		}
	}
}
