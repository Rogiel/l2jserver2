package com.l2jserver.service.cache;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

public class SimpleCacheServiceTest {
	private final SimpleCacheService cacheService = new SimpleCacheService();

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
