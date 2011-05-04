package com.l2jserver.service.game.template;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.Template;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.game.scripting.ScriptContext;
import com.l2jserver.service.game.scripting.ScriptingService;
import com.l2jserver.util.factory.CollectionFactory;

public class StaticTemplateService extends AbstractService implements
		TemplateService {
	private final ScriptingService scriptingService;
	private final StaticTemplateServiceConfiguration config;
	private final Injector injector;

	private ScriptContext context;

	private Map<TemplateID, Template> templates = CollectionFactory.newMap(
			TemplateID.class, Template.class);

	@Inject
	public StaticTemplateService(ScriptingService scriptingService,
			ConfigurationService configService, Injector injector) {
		this.scriptingService = scriptingService;
		this.injector = injector;
		this.config = configService
				.get(StaticTemplateServiceConfiguration.class);
	}

	@Override
	public void start() throws ServiceStartException {
		if (context == null) {
			try {
				context = scriptingService.load(config.getTemplateDescriptor())
						.get(0);
			} catch (Exception e) {
				throw new ServiceStartException(e);
			}
			return;
		}
		if (context.isInitialized())
			context.shutdown();
		context.init();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Template> T getTemplate(TemplateID<T> id) {
		return (T) templates.get(id);
	}

	public void addTemplate(Class<? extends Template> t) {
		final Template template = injector.getInstance(t);
		System.out.println(template.getID() + " -> " + template);
		if (template.getID() != null)
			templates.put(template.getID(), template);
	}

	public void removeTemplate(Template t) {
		// TODO templates.remove(t);
	}

	@Override
	public void reload() {
		context.reload();
	}

	@Override
	public void stop() throws ServiceStopException {
		if (context.isInitialized())
			context.shutdown();
		context = null;
	}
}
