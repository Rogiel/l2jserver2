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
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceException;
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
	 * List of online administrators
	 */
	@SuppressWarnings("unused")
	private List<CharacterID> online;
	
	@Inject
	private ItemService itemService;
	
	@Inject
	private ItemTemplateIDProvider itidProvider;

	/**
	 * @param spawnService
	 *            the spawn service
	 */
	@Inject
	private AdministratorServiceImpl(SpawnService spawnService) {
		this.spawnService = spawnService;
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
//			conn.sendMessage( "adding " + itidProvider.resolveID(57).getTemplate().getName() );
			character.getInventory().add( itemService.create(itidProvider.resolveID(57).getTemplate(), 10000) );
			break;
		default:
			throw new ServiceException();
		}
	}
}
