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
package com.l2jserver.service.game.template;

import org.junit.Test;

import script.template.item.AdenaItemTemplate;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.db.dao.MySQL5DAOModule;
import com.l2jserver.model.id.provider.IDProviderModule;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.service.ServiceModule;
import com.l2jserver.service.ServiceStartException;

public class StaticTemplateServiceTest {
	private final Injector injector = Guice.createInjector(new ServiceModule(),
			new IDProviderModule(), new MySQL5DAOModule());
	private final TemplateService service = injector
			.getInstance(TemplateService.class);
	private final ItemTemplateIDProvider factory = injector
			.getInstance(ItemTemplateIDProvider.class);

	@Test
	public void testAdena() throws ServiceStartException {
		service.start();
		System.out
				.println(factory.createID(AdenaItemTemplate.ID).getTemplate());
	}
}
