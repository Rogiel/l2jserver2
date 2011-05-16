/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.id.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.l2jserver.model.id.object.allocator.BitSetIDAllocator;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.id.object.factory.CharacterIDFactory.CharacterIDGuiceFactory;
import com.l2jserver.model.id.object.factory.ClanIDFactory;
import com.l2jserver.model.id.object.factory.ClanIDFactory.ClanIDGuiceFactory;
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

		bind(ClanIDFactory.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(ClanIDGuiceFactory.class));

		// bind(PetIDFactory.class).in(Scopes.SINGLETON);
		// install(new FactoryModuleBuilder().build(PetIDGuiceFactory.class));

		// TEMPLATE IDS
		install(new FactoryModuleBuilder().build(ItemTemplateIDFactory.class));
		install(new FactoryModuleBuilder().build(SkillTemplateIDFactory.class));
		install(new FactoryModuleBuilder()
				.build(CharacterTemplateIDFactory.class));
	}
}
