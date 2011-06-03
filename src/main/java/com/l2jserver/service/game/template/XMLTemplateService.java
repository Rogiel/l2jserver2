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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.vfs.FileObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.template.TeleportationTemplate;
import com.l2jserver.model.template.Template;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.cache.Cache;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.core.vfs.VFSService;
import com.l2jserver.util.jaxb.CharacterTemplateIDAdapter;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.NPCTemplateIDAdapter;
import com.l2jserver.util.jaxb.SkillTemplateIDAdapter;
import com.l2jserver.util.jaxb.TeleportationTemplateIDAdapter;
import com.l2jserver.util.vfs.ExtensionFileSelector;

@Depends({ LoggingService.class, VFSService.class, CacheService.class,
		ConfigurationService.class })
public class XMLTemplateService extends AbstractService implements
		TemplateService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final VFSService vfsService;
	private final CacheService cacheService;

	private final XMLTemplateServiceConfiguration config;
	private final NPCTemplateIDAdapter npcTemplateIdAdapter;
	private final ItemTemplateIDAdapter itemTemplateIdAdapter;
	private final SkillTemplateIDAdapter skillTemplateIdAdapter;
	private final CharacterTemplateIDAdapter charIdTemplateAdapter;
	private final TeleportationTemplateIDAdapter teleportationIdTemplateAdapter;

	private JAXBContext context;
	private Unmarshaller unmarshaller;

	@SuppressWarnings("rawtypes")
	private Cache<TemplateID, Template> templates;

	@Inject
	public XMLTemplateService(final VFSService vfsService,
			CacheService cacheService, ConfigurationService configService,
			NPCTemplateIDAdapter npcTemplateIdAdapter,
			ItemTemplateIDAdapter itemTemplateIdAdapter,
			SkillTemplateIDAdapter skillTemplateIdAdapter,
			CharacterTemplateIDAdapter charIdTemplateAdapter,
			TeleportationTemplateIDAdapter teleportationIdTemplateAdapter) {
		this.vfsService = vfsService;
		this.cacheService = cacheService;
		this.config = configService.get(XMLTemplateServiceConfiguration.class);
		this.npcTemplateIdAdapter = npcTemplateIdAdapter;
		this.itemTemplateIdAdapter = itemTemplateIdAdapter;
		this.skillTemplateIdAdapter = skillTemplateIdAdapter;
		this.charIdTemplateAdapter = charIdTemplateAdapter;
		this.teleportationIdTemplateAdapter = teleportationIdTemplateAdapter;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		templates = cacheService.createEternalCache("templates", 100 * 1000);
		try {
			log.debug("Creating JAXBContext instance");
			context = JAXBContext.newInstance(CharacterTemplate.class,
					NPCTemplate.class, ItemTemplate.class,
					TeleportationTemplateContainer.class);

			log.debug("Creating Unmarshaller instance");
			unmarshaller = context.createUnmarshaller();

			unmarshaller.setAdapter(NPCTemplateIDAdapter.class,
					npcTemplateIdAdapter);
			unmarshaller.setAdapter(ItemTemplateIDAdapter.class,
					itemTemplateIdAdapter);
			unmarshaller.setAdapter(SkillTemplateIDAdapter.class,
					skillTemplateIdAdapter);
			unmarshaller.setAdapter(CharacterTemplateIDAdapter.class,
					charIdTemplateAdapter);
			unmarshaller.setAdapter(TeleportationTemplateIDAdapter.class,
					teleportationIdTemplateAdapter);

			final FileObject root = vfsService.resolve(config
					.getTemplateDirectory());

			log.info("Scanning {} for XML templates", root);

			FileObject[] files = root.findFiles(ExtensionFileSelector
					.ext("xml"));

			log.info("Located {} XML template files", files.length);
			for (final FileObject file : files) {
				loadTemplate(file);
			}
			final FileObject teleportsXml = root.getParent().resolveFile(
					"teleports.xml");
			final InputStream in = teleportsXml.getContent().getInputStream();
			try {
				TeleportationTemplateContainer container = (TeleportationTemplateContainer) unmarshaller
						.unmarshal(in);
				for (final TeleportationTemplate template : container.templates) {
					templates.put(template.getID(), template);
				}
			} finally {
				in.close();
			}
		} catch (JAXBException e) {
			throw new ServiceStartException(e);
		} catch (IOException e) {
			throw new ServiceStartException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Template<?>> T getTemplate(TemplateID<T, ?> id) {
		Preconditions.checkNotNull(id, "id");
		return (T) templates.get(id);
	}

	public void loadTemplate(FileObject file) throws JAXBException, IOException {
		Preconditions.checkNotNull(file, "file");
		log.debug("Loading template {}", file);
		final InputStream in = file.getContent().getInputStream();
		try {
			final Template<?> template = (Template<?>) unmarshaller
					.unmarshal(in);
			if (template.getID() != null)
				templates.put(template.getID(), template);
		} finally {
			in.close();
		}
	}

	public void removeTemplate(Template<?> template) {
		Preconditions.checkNotNull(template, "template");
		templates.remove(template.getID());
	}

	@Override
	protected void doStop() throws ServiceStopException {
		cacheService.dispose(templates);
		templates = null;
		unmarshaller = null;
		context = null;
	}

	@XmlRootElement(name = "teleports")
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(namespace = "teleports")
	public static class TeleportationTemplateContainer {
		@XmlElement(name = "teleport")
		public List<TeleportationTemplate> templates;
	}
}
