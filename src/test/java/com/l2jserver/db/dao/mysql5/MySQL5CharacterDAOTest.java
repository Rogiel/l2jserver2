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
package com.l2jserver.db.dao.mysql5;

import junit.framework.Assert;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.GameServerModule;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.ServiceManager;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.template.TemplateService;

public class MySQL5CharacterDAOTest {
	private final Injector injector = Guice
			.createInjector(new GameServerModule());

	@Test
	public void testCachedLoad() throws ServiceStartException {
		injector.getInstance(ServiceManager.class).start(TemplateService.class);
		injector.getInstance(ServiceManager.class).start(DatabaseService.class);

		final CharacterDAO dao = injector.getInstance(CharacterDAO.class);
		final L2Character char1 = dao.load(injector.getInstance(
				CharacterIDProvider.class).createID(268437456));
		final L2Character char2 = dao.load(injector.getInstance(
				CharacterIDProvider.class).createID(268437456));

		Assert.assertSame(char1, char2);
	}
}
