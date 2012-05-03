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
package com.l2jserver.service.game.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceException;
import com.l2jserver.service.game.character.CharacterInventoryService;
import com.l2jserver.service.game.item.ItemService;
import com.l2jserver.service.game.spawn.CharacterAlreadyTeleportingServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.util.geometry.Coordinate;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class AdministratorServiceImpl extends AbstractService implements
		AdministratorService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link SpawnService}
	 */
	private final SpawnService spawnService;

	/**
	 * The inventory service
	 */
	private final CharacterInventoryService inventoryService;
	/**
	 * The Item service
	 */
	private final ItemService itemService;
	/**
	 * The item template ID provider
	 */
	private final ItemTemplateIDProvider itemTemplateIdProvider;

	/**
	 * List of online administrators
	 */
	@SuppressWarnings("unused")
	private List<CharacterID> online;

	/**
	 * @param spawnService
	 *            the spawn service
	 * @param inventoryService
	 *            the inventory service
	 * @param itemService
	 *            the item service
	 * @param itemTemplateIdProvider
	 *            the item template id provider
	 */
	@Inject
	private AdministratorServiceImpl(SpawnService spawnService,
			CharacterInventoryService inventoryService,
			ItemService itemService,
			ItemTemplateIDProvider itemTemplateIdProvider) {
		this.spawnService = spawnService;
		this.inventoryService = inventoryService;
		this.itemService = itemService;
		this.itemTemplateIdProvider = itemTemplateIdProvider;
	}

	@Override
	public void command(Lineage2Client conn, L2Character character,
			String command, String... args) throws NotSpawnedServiceException,
			CharacterAlreadyTeleportingServiceException, ServiceException {
		log.debug("{} is opening admin control panel", character);
		switch (command) {
		case "tele":
			Actor teleport = character;
			if (character.getTarget() != null)
				teleport = character.getTarget();
			spawnService.teleport(
					teleport,
					Coordinate.fromXYZ(Integer.parseInt(args[0]),
							Integer.parseInt(args[1]),
							Integer.parseInt(args[2])));
			break;
		case "give":
			L2Character target;
			if (character.getTarget() instanceof L2Character) {
				target = (L2Character) character.getTarget();
			} else {
				target = character;
			}

			int count = 1;
			ItemTemplateID itemTemplateID;

			if (args.length == 1) {
				itemTemplateID = itemTemplateIdProvider.resolveID(Integer
						.parseInt(args[0]));
			} else if (args.length == 2) {
				itemTemplateID = itemTemplateIdProvider.resolveID(Integer
						.parseInt(args[0]));
				count = Integer.parseInt(args[1]);
			} else {
				throw new ServiceException();
			}
			inventoryService.add(target,
					itemService.create(itemTemplateID.getTemplate(), count));
			break;
		default:
			throw new ServiceException();
		}
	}
}
