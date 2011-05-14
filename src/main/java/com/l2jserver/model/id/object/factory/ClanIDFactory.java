package com.l2jserver.model.id.object.factory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.factory.IDFactory;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.allocator.IDAllocator;

/**
 * {@link IDFactory} for {@link ClanID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClanIDFactory implements ObjectIDFactory<ClanID> {
	/**
	 * The ID allocator
	 */
	private final IDAllocator allocator;
	/**
	 * The Guice factory
	 */
	private final ClanIDGuiceFactory factory;

	@Inject
	public ClanIDFactory(IDAllocator allocator, ClanIDGuiceFactory factory) {
		super();
		this.allocator = allocator;
		this.factory = factory;
	}

	@Override
	public ClanID createID() {
		return createID(allocator.allocate());
	}

	@Override
	public ClanID createID(Integer id) {
		return factory.create(id);
	}

	@Override
	public void destroy(ClanID id) {
		allocator.release(id.getID());
	}

	/**
	 * This is an Google Guice factory. Assistect Inject extension will
	 * automatically implement it and create the injected instances.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface ClanIDGuiceFactory {
		/**
		 * Creates a new ID instance
		 * 
		 * @param id
		 *            the numeric ID
		 * @return the new ID created by injection
		 */
		public ClanID create(@Assisted int id);
	}
}
