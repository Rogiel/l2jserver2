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

public class ScriptTemplateService extends AbstractService implements
		TemplateService {
	private final ScriptingService scriptingService;
	private final ScriptTemplateServiceConfiguration config;
	private final Injector injector;

	private ScriptContext context;

	@SuppressWarnings("rawtypes")
	private Map<TemplateID, Template> templates = CollectionFactory.newMap(
			TemplateID.class, Template.class);

	@Inject
	public ScriptTemplateService(ScriptingService scriptingService,
			ConfigurationService configService, Injector injector) {
		this.scriptingService = scriptingService;
		this.injector = injector;
		this.config = configService
				.get(ScriptTemplateServiceConfiguration.class);
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
	public <T extends Template<?>> T getTemplate(TemplateID<T> id) {
		return (T) templates.get(id);
	}

	public void addTemplate(Class<? extends Template<?>> t) {
		final Template<?> template = injector.getInstance(t);
		if (templates.containsKey(template.getID()))
			throw new TemplateException("Template with ID" + template.getID()
					+ " is already registered for "
					+ templates.get(template.getID()));

		if (template.getID() != null)
			templates.put(template.getID(), template);
	}

	public void removeTemplate(Template<?> t) {
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
