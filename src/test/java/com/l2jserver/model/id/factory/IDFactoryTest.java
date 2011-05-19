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

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.db.dao.MySQL5DAOModule;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.provider.IDProviderModule;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.ServiceModule;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;

public class IDFactoryTest {
	private final Injector injector = Guice.createInjector(new ServiceModule(),
			new MySQL5DAOModule(), new IDProviderModule());
	private final CharacterIDProvider charIdFactory = injector
			.getInstance(CharacterIDProvider.class);

	@Test
	public void testCreateID() {
		final ID<Integer> id1 = charIdFactory.createID();
		final ID<Integer> id2 = charIdFactory.createID();
		Assert.assertNotNull(id1);
		Assert.assertFalse(id1.equals(id2));
	}

	@Test
	public void testDestroy() {
		final CharacterID id1 = charIdFactory.createID();
		Assert.assertNotNull(id1);
		charIdFactory.destroy(id1);
	}

	@Test
	public void testGetObject() throws ServiceStartException {
		BasicConfigurator.configure();
		injector.getInstance(ScriptingService.class).start();
		injector.getInstance(TemplateService.class).start();

		injector.getInstance(DatabaseService.class).start();
		final CharacterID id = charIdFactory.createID(268437456);
		final L2Character character = id.getObject();

		Assert.assertNotNull(character);
		System.out.println(character.getAppearance().getHairColor());

		Assert.assertNotNull(character);
		Assert.assertEquals(id, character.getID());
	}
}
