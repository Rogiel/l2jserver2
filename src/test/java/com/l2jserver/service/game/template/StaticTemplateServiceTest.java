package com.l2jserver.service.game.template;

import org.junit.Test;

import script.template.item.AdenaItemTemplate;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.l2jserver.db.dao.DAOModuleMySQL5;
import com.l2jserver.model.id.factory.IDFactoryModule;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.service.game.scripting.ScriptingServiceImpl;

public class StaticTemplateServiceTest {
	private final Injector injector = Guice.createInjector(
			new IDFactoryModule(), new DAOModuleMySQL5(), new AbstractModule() {
				@Override
				protected void configure() {
					bind(ScriptingService.class).to(ScriptingServiceImpl.class)
							.in(Scopes.SINGLETON);
					bind(TemplateService.class).to(StaticTemplateService.class)
							.in(Scopes.SINGLETON);
				}
			});
	private final TemplateService service = injector
			.getInstance(TemplateService.class);
	private final ItemTemplateIDFactory factory = injector
			.getInstance(ItemTemplateIDFactory.class);

	@Test
	public void testAdena() throws ServiceStartException {
		service.start();
		System.out
				.println(factory.createID(AdenaItemTemplate.ID).getTemplate());
	}
}
