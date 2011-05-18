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
package com.l2jserver.model.world.character;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.db.dao.CharacterFriendDAO;
import com.l2jserver.db.dao.MySQL5DAOModule;
import com.l2jserver.model.id.factory.IDFactoryModule;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.ServiceModule;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;

public class CharacterFriendListTest {
	private final Injector injector = Guice.createInjector(new ServiceModule(),
			new MySQL5DAOModule(), new IDFactoryModule());
	private final CharacterIDFactory charIdFactory = injector
			.getInstance(CharacterIDFactory.class);

	@Test
	public void testIterator() throws ServiceStartException {
		BasicConfigurator.configure();
		injector.getInstance(ScriptingService.class).start();
		injector.getInstance(TemplateService.class).start();

		injector.getInstance(DatabaseService.class).start();
		final CharacterID id = charIdFactory.createID(268437456);
		final L2Character character = id.getObject();

		final CharacterFriendDAO friendDao = injector
				.getInstance(CharacterFriendDAO.class);
		friendDao.load(character);

		for (final L2Character friend : character.getFriendList()) {
			Assert.assertNotNull(friend);
			Assert.assertNotSame(friend.getID(), character.getID());
			Assert.assertFalse(friend.getID().equals(character.getID()));
		}
	}

}
