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
import com.l2jserver.service.BasicServiceModule;
import com.l2jserver.service.ServiceModule;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.database.DatabaseService;

public class IDFactoryTest {
	private final Injector injector = Guice.createInjector(new ServiceModule(),
			new BasicServiceModule(), new DAOModuleMySQL5(),
			new IDFactoryModule());
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

		injector.getInstance(DatabaseService.class).start();
		final CharacterID id1 = charIdFactory.createID(268435456);
		final L2Character character = id1.getObject();

		System.out.println(character.getAppearance().getHairColor());

		Assert.assertNotNull(character);
		Assert.assertEquals(id1, character.getID());
	}
}
