/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.template;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.template.Template;
import com.l2jserver.model.template.character.CharacterTemplate;
import com.l2jserver.model.template.item.ItemTemplate;
import com.l2jserver.model.template.npc.NPCTemplate;
import com.l2jserver.model.template.npc.TeleportationTemplate;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.cache.Cache;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.core.vfs.VFSService;
import com.l2jserver.util.jaxb.CharacterTemplateIDAdapter;
import com.l2jserver.util.jaxb.EffectTemplateIDAdapter;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.NPCTemplateIDAdapter;
import com.l2jserver.util.jaxb.SkillTemplateIDAdapter;
import com.l2jserver.util.jaxb.TeleportationTemplateIDAdapter;

/**
 * This service loads template data from XML files using the {@link JAXB}
 * service.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, VFSService.class, CacheService.class,
		ConfigurationService.class })
public class XMLTemplateService extends AbstractService implements
		TemplateService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The vfs service
	 */
	private final VFSService vfsService;
	/**
	 * The cache service
	 */
	private final CacheService cacheService;

	/**
	 * The XML template service configuration
	 */
	private final XMLTemplateServiceConfiguration config;
	/**
	 * The npc template id adapter
	 */
	private final NPCTemplateIDAdapter npcTemplateIdAdapter;
	/**
	 * The item template id adapter
	 */
	private final ItemTemplateIDAdapter itemTemplateIdAdapter;
	/**
	 * The skill template id adapter
	 */
	private final SkillTemplateIDAdapter skillTemplateIdAdapter;
	/**
	 * The character template id adapter
	 */
	private final CharacterTemplateIDAdapter charIdTemplateAdapter;
	/**
	 * The effect template id adapter
	 */
	private final EffectTemplateIDAdapter effectIdTemplateAdapter;
	/**
	 * The teleportation template id adapter
	 */
	private final TeleportationTemplateIDAdapter teleportationIdTemplateAdapter;

	/**
	 * The {@link JAXB} context
	 */
	private JAXBContext context;
	/**
	 * The {@link JAXB} unmarshaller
	 */
	private Unmarshaller unmarshaller;

	/**
	 * An cache of all loaded templates
	 */
	@SuppressWarnings("rawtypes")
	private Cache<TemplateID, Template> templates;

	/**
	 * XML {@link TemplateService} configuration interface
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public interface XMLTemplateServiceConfiguration extends
			TemplateServiceConfiguration {
		/**
		 * @return the directory in which templates are stored
		 */
		@ConfigurationPropertyGetter(defaultValue = "data/template")
		@ConfigurationXPath("/configuration/services/template/directory")
		URI getTemplateDirectory();

		/**
		 * @param file
		 *            the directory in which templates are stored
		 */
		@ConfigurationPropertySetter
		@ConfigurationXPath("/configuration/services/template/directory")
		void setTemplateDirectory(URI file);
	}

	/**
	 * @param vfsService
	 *            the vfs service
	 * @param cacheService
	 *            the cache service
	 * @param configService
	 *            the configuration service
	 * @param npcTemplateIdAdapter
	 *            the npc template id adapter
	 * @param itemTemplateIdAdapter
	 *            the item template id adapter
	 * @param skillTemplateIdAdapter
	 *            the skill template id adapter
	 * @param charIdTemplateAdapter
	 *            the character id template adapter
	 * @param effectIdTemplateAdapter
	 *            the effect id template adapter
	 * @param teleportationIdTemplateAdapter
	 *            the teleportation template id adapter
	 */
	@Inject
	public XMLTemplateService(final VFSService vfsService,
			CacheService cacheService, ConfigurationService configService,
			NPCTemplateIDAdapter npcTemplateIdAdapter,
			ItemTemplateIDAdapter itemTemplateIdAdapter,
			SkillTemplateIDAdapter skillTemplateIdAdapter,
			CharacterTemplateIDAdapter charIdTemplateAdapter,
			EffectTemplateIDAdapter effectIdTemplateAdapter,
			TeleportationTemplateIDAdapter teleportationIdTemplateAdapter) {
		this.vfsService = vfsService;
		this.cacheService = cacheService;
		this.config = configService.get(XMLTemplateServiceConfiguration.class);
		this.npcTemplateIdAdapter = npcTemplateIdAdapter;
		this.itemTemplateIdAdapter = itemTemplateIdAdapter;
		this.skillTemplateIdAdapter = skillTemplateIdAdapter;
		this.charIdTemplateAdapter = charIdTemplateAdapter;
		this.effectIdTemplateAdapter = effectIdTemplateAdapter;
		this.teleportationIdTemplateAdapter = teleportationIdTemplateAdapter;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		templates = cacheService.createEternalCache("templates", 100 * 1000);
		try {
			log.debug("Creating JAXBContext instance");
			context = JAXBContext.newInstance(CharacterTemplate.class,
					NPCTemplate.class, ItemTemplate.class, SkillTemplate.class,
					TeleportationTemplateContainer.class);

			log.debug("Creating Unmarshaller instance");
			unmarshaller = context.createUnmarshaller();

			unmarshaller.setAdapter(npcTemplateIdAdapter);
			unmarshaller.setAdapter(itemTemplateIdAdapter);
			unmarshaller.setAdapter(skillTemplateIdAdapter);
			unmarshaller.setAdapter(charIdTemplateAdapter);
			unmarshaller.setAdapter(effectIdTemplateAdapter);
			unmarshaller.setAdapter(teleportationIdTemplateAdapter);

			final Path templatePath = vfsService.resolve(config
					.getTemplateDirectory().toString());

			log.info("Scanning {} for XML templates", templatePath);

			Files.walkFileTree(templatePath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					if (!file.toString().endsWith(".xml"))
						return FileVisitResult.CONTINUE;
					// FIXME remove hard coded skip of zone template loading
					if (file.toString().contains("zones.xml"))
						return FileVisitResult.CONTINUE;
					try {
						loadTemplate(file);
						return FileVisitResult.CONTINUE;
					} catch (JAXBException e) {
						throw new IOException(e);
					}
				}
			});
		} catch (JAXBException e) {
			throw new ServiceStartException(e);
		} catch (IOException e) {
			throw new ServiceStartException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Template> T getTemplate(TemplateID<T, ?> id) {
		Preconditions.checkNotNull(id, "id");
		return (T) templates.get(id);
	}

	/**
	 * Loads the template located in <tt>path</tt>
	 * 
	 * @param path
	 *            the path to the template
	 * @throws JAXBException
	 *             if any error occur while processing the XML
	 * @throws IOException
	 *             if any error occur in the I/O level
	 */
	public void loadTemplate(Path path) throws JAXBException, IOException {
		Preconditions.checkNotNull(path, "path");
		log.debug("Loading template {}", path);
		final InputStream in = Files.newInputStream(path);
		try {
			Object obj = unmarshaller.unmarshal(in);
			if (obj instanceof Template) {
				final Template template = (Template) obj;
				log.debug("Template loaded: {}", template);
				if (template.getID() != null)
					templates.put(template.getID(), template);
			} else if (obj instanceof TeleportationTemplateContainer) {
				for (final Template template : ((TeleportationTemplateContainer) obj).templates) {
					log.debug("Template loaded: {}", template);
					if (template.getID() != null)
						templates.put(template.getID(), template);
				}
			}
		} finally {
			// in.close();
		}
	}

	/**
	 * Removes the given <tt>template</tt> from the cache
	 * 
	 * @param template
	 *            the template to be purged
	 */
	public void removeTemplate(Template template) {
		Preconditions.checkNotNull(template, "template");
		log.debug("Removing template {}", template);
		templates.remove(template.getID());
	}

	@Override
	protected void doStop() throws ServiceStopException {
		cacheService.dispose(templates);
		templates = null;
		unmarshaller = null;
		context = null;
	}

	/**
	 * The teleportation template container
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlRootElement(name = "teleports", namespace = "http://schemas.l2jserver2.com/teleport")
	@XmlType(namespace = "http://schemas.l2jserver2.com/teleport", name = "TeleportsType")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TeleportationTemplateContainer {
		/**
		 * The list of all teleportation templates
		 */
		@XmlElement(namespace = "http://schemas.l2jserver2.com/teleport", name = "teleport")
		public List<TeleportationTemplate> templates;
	}
}
