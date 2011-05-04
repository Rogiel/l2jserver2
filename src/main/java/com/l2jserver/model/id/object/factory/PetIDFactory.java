package com.l2jserver.model.id.object.factory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.factory.IDFactory;
import com.l2jserver.model.id.object.PetID;
import com.l2jserver.model.id.object.allocator.IDAllocator;

/**
 * {@link IDFactory} for {@link PetID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class PetIDFactory implements ObjectIDFactory<PetID> {
	/**
	 * The ID allocator
	 */
	private final IDAllocator allocator;
	/**
	 * The Guice factory
	 */
	private final PetIDGuiceFactory factory;

	@Inject
	public PetIDFactory(IDAllocator allocator, PetIDGuiceFactory factory) {
		super();
		this.allocator = allocator;
		this.factory = factory;
	}

	@Override
	public PetID createID() {
		return createID(allocator.allocate());
	}

	@Override
	public PetID createID(int id) {
		return factory.create(id);
	}

	@Override
	public void destroy(PetID id) {
		allocator.release(id.getID());
	}

	/**
	 * This is an Google Guice factory. Assistect Inject extension will
	 * automatically implement it and create the injected instances.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface PetIDGuiceFactory {
		/**
		 * Creates a new ID instance
		 * 
		 * @param id
		 *            the numeric ID
		 * @return the new ID created by injection
		 */
		public PetID create(@Assisted int id);
	}
}
