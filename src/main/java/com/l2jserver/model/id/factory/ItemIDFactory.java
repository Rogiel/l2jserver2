package com.l2jserver.model.id.factory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.ItemID;
import com.l2jserver.model.id.allocator.IDAllocator;

/**
 * {@link IDFactory} for {@link ItemID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ItemIDFactory implements ObjectIDFactory<ItemID> {
	/**
	 * The ID allocator
	 */
	private final IDAllocator allocator;
	/**
	 * The Guice factory
	 */
	private final ItemIDGuiceFactory factory;

	@Inject
	public ItemIDFactory(IDAllocator allocator, ItemIDGuiceFactory factory) {
		super();
		this.allocator = allocator;
		this.factory = factory;
	}

	@Override
	public ItemID createID() {
		return createID(allocator.allocate());
	}

	@Override
	public ItemID createID(int id) {
		return factory.create(id);
	}

	@Override
	public void destroy(ItemID id) {
		allocator.release(id.getID());
	}

	/**
	 * This is an Google Guice factory. Assistect Inject extension will
	 * automatically implement it and create the injected instances.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface ItemIDGuiceFactory {
		/**
		 * Creates a new ID instance
		 * 
		 * @param id
		 *            the numeric ID
		 * @return the new ID created by injection
		 */
		public ItemID create(@Assisted int id);
	}
}
