package com.l2jserver.model.world.character;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.db.dao.CharacterFriendDAO;
import com.l2jserver.db.dao.DAOModuleMySQL5;
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
			new DAOModuleMySQL5(), new IDFactoryModule());
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
