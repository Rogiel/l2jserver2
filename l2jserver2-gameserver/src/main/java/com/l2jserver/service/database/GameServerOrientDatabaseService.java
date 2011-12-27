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
package com.l2jserver.service.database;

import java.io.IOException;

import com.google.inject.Inject;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.cache.CacheService;
import com.l2jserver.service.configuration.ConfigurationService;
import com.l2jserver.service.core.LoggingService;
import com.l2jserver.service.core.threading.ThreadService;
import com.l2jserver.service.core.vfs.VFSService;
import com.l2jserver.service.database.model.QActorSkill;
import com.l2jserver.service.database.model.QCharacter;
import com.l2jserver.service.database.model.QCharacterFriend;
import com.l2jserver.service.database.model.QCharacterShortcut;
import com.l2jserver.service.database.model.QClan;
import com.l2jserver.service.database.model.QItem;
import com.l2jserver.service.database.model.QLogChat;
import com.l2jserver.service.database.model.QNPC;
import com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService;
import com.l2jserver.service.game.template.TemplateService;

/**
 * This is an implementation of {@link DatabaseService} that provides an layer
 * to OrientDB Object Database.
 * 
 * <h1>Internal specification</h1> <h2>The
 * {@link com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.Query
 * Query} object</h2>
 * 
 * If you wish to implement a new {@link DataAccessObject} you should try not
 * use
 * {@link com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.Query
 * Query} object directly because it only provides low level access to the JDBC
 * architecture. Instead, you could use an specialized class, like
 * {@link com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.InsertQuery
 * InsertUpdateQuery} ,
 * {@link com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectListQuery
 * SelectListQuery} or
 * {@link com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.SelectSingleQuery
 * SelectSingleQuery} . If you do need low level access, feel free to use the
 * {@link com.l2jserver.service.database.orientdb.AbstractOrientDatabaseService.Query
 * Query} class directly.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, CacheService.class,
		ConfigurationService.class, TemplateService.class, ThreadService.class })
public class GameServerOrientDatabaseService extends
		AbstractOrientDatabaseService implements DatabaseService {
	/**
	 * The VFS service
	 */
	private final VFSService vfsService;

	/**
	 * @param configService
	 *            the config service
	 * @param cacheService
	 *            the cache service
	 * @param threadService
	 *            the thread service
	 * @param vfsService
	 *            the VFS service
	 * @param daoResolver
	 *            the {@link DataAccessObject DAO} resolver
	 */
	@Inject
	public GameServerOrientDatabaseService(ConfigurationService configService,
			CacheService cacheService, ThreadService threadService,
			final VFSService vfsService, DAOResolver daoResolver) {
		super(configService, cacheService, threadService, daoResolver);
		this.vfsService = vfsService;
	}

	@Override
	public void updateSchemas() {
		updateSchema(QActorSkill.actorSkill);
		updateSchema(QCharacter.character);
		updateSchema(QCharacterFriend.characterFriend);
		updateSchema(QCharacterShortcut.characterShortcut);
		updateSchema(QClan.clan);
		updateSchema(QItem.item);
		updateSchema(QLogChat.logChat);
		if (updateSchema(QNPC.npc)) {
			try {
				importData(vfsService.resolveDataFile("static/npc.csv"), QNPC.npc);
			} catch (IOException e) {
				throw new DatabaseException(e);
			}
		}
	}
}
