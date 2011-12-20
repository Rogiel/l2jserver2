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
package com.l2jserver.model.id.provider;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.l2jserver.model.id.object.allocator.BitSetIDAllocator;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.CharacterIDProvider.CharacterIDGuiceFactory;
import com.l2jserver.model.id.object.provider.ClanIDProvider;
import com.l2jserver.model.id.object.provider.ClanIDProvider.ClanIDGuiceFactory;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider.ItemIDGuiceFactory;
import com.l2jserver.model.id.object.provider.NPCIDProvider;
import com.l2jserver.model.id.object.provider.NPCIDProvider.NPCIDGuiceFactory;
import com.l2jserver.model.id.object.provider.ObjectIDResolver;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.id.template.provider.EffectTemplateIDProvider;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.id.template.provider.SkillTemplateIDProvider;
import com.l2jserver.model.id.template.provider.TeleportationTemplateIDProvider;

/**
 * Google Guice {@link IDProvider} {@link Module}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class IDProviderModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(IDAllocator.class).to(BitSetIDAllocator.class)
				.in(Scopes.SINGLETON);

		// OBJECT IDS
		bind(ObjectIDResolver.class).in(Scopes.SINGLETON); // read-only!

		bind(CharacterIDProvider.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(CharacterIDGuiceFactory.class));

		bind(NPCIDProvider.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(NPCIDGuiceFactory.class));

		bind(ItemIDProvider.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(ItemIDGuiceFactory.class));

		bind(ClanIDProvider.class).in(Scopes.SINGLETON);
		install(new FactoryModuleBuilder().build(ClanIDGuiceFactory.class));

		// bind(PetIDFactory.class).in(Scopes.SINGLETON);
		// install(new FactoryModuleBuilder().build(PetIDGuiceFactory.class));

		// MISC OBJECTS
		install(new FactoryModuleBuilder().build(AccountIDProvider.class));
		install(new FactoryModuleBuilder()
				.build(CharacterShortcutIDProvider.class));
		install(new FactoryModuleBuilder().build(FortIDProvider.class));
		install(new FactoryModuleBuilder().build(FriendIDProvider.class));
		install(new FactoryModuleBuilder().build(ChatMessageIDProvider.class));

		// TEMPLATE IDS
		install(new FactoryModuleBuilder().build(ItemTemplateIDProvider.class));
		install(new FactoryModuleBuilder()
				.build(EffectTemplateIDProvider.class));
		install(new FactoryModuleBuilder().build(SkillTemplateIDProvider.class));
		install(new FactoryModuleBuilder()
				.build(CharacterTemplateIDProvider.class));
		install(new FactoryModuleBuilder().build(NPCTemplateIDProvider.class));
		install(new FactoryModuleBuilder()
				.build(TeleportationTemplateIDProvider.class));
	}
}
