package com.l2jserver.model.id.factory;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.l2jserver.db.dao.DAOModuleMySQL5;
import com.l2jserver.model.id.ID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.ServiceModule;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.template.TemplateService;

public class IDFactoryTest {
	private final Injector injector = Guice.createInjector(new ServiceModule(),
			new DAOModuleMySQL5(), new IDFactoryModule());
	private final CharacterIDFactory charIdFactory = injector
			.getInstance(CharacterIDFactory.class);

	@Test
	public void testCreateID() {
		final ID id1 = charIdFactory.createID();
		final ID id2 = charIdFactory.createID();
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
