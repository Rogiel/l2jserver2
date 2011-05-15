package com.l2jserver.model.id.object.factory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.factory.IDFactory;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.allocator.IDAllocator;

/**
 * {@link IDFactory} for {@link CharacterID}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterIDFactory implements ObjectIDFactory<CharacterID> {
	/**
	 * The ID allocator
	 */
	private final IDAllocator allocator;
	/**
	 * The Guice Factory
	 */
	private final CharacterIDGuiceFactory factory;

	@Inject
	public CharacterIDFactory(IDAllocator allocator,
			CharacterIDGuiceFactory factory) {
		super();
		this.allocator = allocator;
		this.factory = factory;
	}

	@Override
	public CharacterID createID() {
		return createID(allocator.allocate());
	}

	@Override
	public CharacterID createID(Integer id) {
		return factory.create(id);
	}

	@Override
	public void destroy(CharacterID id) {
		allocator.release(id.getID());
	}

	/**
	 * This is an Google Guice factory. Assistect Inject extension will
	 * automatically implement it and create the injected instances.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface CharacterIDGuiceFactory {
		/**
		 * Creates a new ID instance
		 * 
		 * @param id
		 *            the numeric ID
		 * @return the new ID created by injection
		 */
		CharacterID create(@Assisted int id);
	}
}
