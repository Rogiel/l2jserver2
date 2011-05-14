package com.l2jserver.model.id.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.l2jserver.model.id.object.allocator.BitSetIDAllocator;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.id.object.factory.CharacterIDFactory.CharacterIDGuiceFactory;
import com.l2jserver.model.id.object.factory.ItemIDFactory;
import com.l2jserver.model.id.object.factory.ItemIDFactory.ItemIDGuiceFactory;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.id.template.factory.SkillTemplateIDFactory;

/**
 * Google Guice {@link IDFactory} {@link Module}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class IDFactoryModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(IDAllocator.class).to(BitSetIDAllocator.class)
				.in(Scopes.SINGLETON);

		// ACCOUNT ID
		install(new FactoryModuleBuilder().build(AccountIDFactory.class));

		// OBJECT IDS
		bind(CharacterIDFactory.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(CharacterIDGuiceFactory.class));

		bind(ItemIDFactory.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(ItemIDGuiceFactory.class));

		// bind(ClanIDFactory.class).in(Scopes.SINGLETON);
		// install(new FactoryModuleBuilder().build(ClanIDGuiceFactory.class));
		//
		// bind(PetIDFactory.class).in(Scopes.SINGLETON);
		// install(new FactoryModuleBuilder().build(PetIDGuiceFactory.class));

		// TEMPLATE IDS
		install(new FactoryModuleBuilder().build(ItemTemplateIDFactory.class));
		install(new FactoryModuleBuilder().build(SkillTemplateIDFactory.class));
		install(new FactoryModuleBuilder()
				.build(CharacterTemplateIDFactory.class));
	}
}
