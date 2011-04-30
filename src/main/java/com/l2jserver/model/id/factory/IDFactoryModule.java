package com.l2jserver.model.id.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.l2jserver.model.id.allocator.BitSetIDAllocator;
import com.l2jserver.model.id.allocator.IDAllocator;
import com.l2jserver.model.id.factory.CharacterIDFactory.CharacterIDGuiceFactory;
import com.l2jserver.model.id.factory.ItemIDFactory.ItemIDGuiceFactory;

public class IDFactoryModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(IDAllocator.class).to(BitSetIDAllocator.class)
				.in(Scopes.SINGLETON);

		bind(CharacterIDFactory.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(CharacterIDGuiceFactory.class));

		bind(ItemIDFactory.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(ItemIDGuiceFactory.class));

		// bind(ClanIDFactory.class).in(Scopes.SINGLETON);
		// install(new FactoryModuleBuilder().build(ClanIDGuiceFactory.class));
		//
		// bind(PetIDFactory.class).in(Scopes.SINGLETON);
		// install(new FactoryModuleBuilder().build(PetIDGuiceFactory.class));
	}
}
