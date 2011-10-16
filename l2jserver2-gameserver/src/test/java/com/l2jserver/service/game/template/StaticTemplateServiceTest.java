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
package com.l2jserver.service.game.template;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.model.id.provider.IDProviderModule;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.ServiceModule;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.database.H2DAOModule;

public class StaticTemplateServiceTest {
	private final Injector injector = Guice.createInjector(new ServiceModule(),
			new IDProviderModule(), new H2DAOModule());
	private final ItemTemplateIDProvider factory = injector
			.getInstance(ItemTemplateIDProvider.class);

	@Test
	public void testAdena() throws ServiceStartException {
		injector.getInstance(ServiceManager.class).start(TemplateService.class);
		System.out.println(factory.resolveID(57).getTemplate());
	}
}
