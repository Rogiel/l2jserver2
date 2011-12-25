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
import java.sql.Connection;
import java.sql.SQLException;

import com.google.inject.Inject;
import com.l2jserver.model.game.CharacterShortcut.ShortcutType;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
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
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService;
import com.l2jserver.service.game.chat.ChatMessageType;
import com.l2jserver.service.game.template.TemplateService;
import com.mysema.query.sql.types.EnumByNameType;

/**
 * This is an implementation of {@link DatabaseService} that provides an layer
 * to JDBC.
 * 
 * <h1>Internal specification</h1> <h2>The
 * {@link com.l2jserver.service.database.sql.AbstractSQLDatabaseService.Query
 * Query} object</h2>
 * 
 * If you wish to implement a new {@link DataAccessObject} you should try not
 * use
 * {@link com.l2jserver.service.database.sql.AbstractSQLDatabaseService.Query
 * Query} object directly because it only provides low level access to the JDBC
 * architecture. Instead, you could use an specialized class, like
 * {@link com.l2jserver.service.database.sql.AbstractSQLDatabaseService.InsertQuery
 * InsertUpdateQuery} ,
 * {@link com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectListQuery
 * SelectListQuery} or
 * {@link com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectSingleQuery
 * SelectSingleQuery} . If you do need low level access, feel free to use the
 * {@link com.l2jserver.service.database.sql.AbstractSQLDatabaseService.Query
 * Query} class directly.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ LoggingService.class, CacheService.class,
		ConfigurationService.class, TemplateService.class, ThreadService.class })
public class GameServerJDBCDatabaseService extends AbstractSQLDatabaseService
		implements DatabaseService {
	/**
	 * @param configService
	 *            the config service
	 * @param cacheService
	 *            the cache service
	 * @param threadService
	 *            the thread service
	 * @param vfsService
	 *            the vfs service
	 * @param daoResolver
	 *            the {@link DataAccessObject DAO} resolver
	 */
	@Inject
	public GameServerJDBCDatabaseService(ConfigurationService configService,
			CacheService cacheService, ThreadService threadService,
			VFSService vfsService, DAOResolver daoResolver) {
		super(
				configService,
				cacheService,
				threadService,
				vfsService,
				daoResolver,
				new EnumByNameType<CharacterRace>(CharacterRace.class),
				new EnumByNameType<CharacterClass>(CharacterClass.class),
				new EnumByNameType<ActorSex>(ActorSex.class),
				new EnumByNameType<CharacterHairColor>(CharacterHairColor.class),
				new EnumByNameType<CharacterHairStyle>(CharacterHairStyle.class),
				new EnumByNameType<CharacterFace>(CharacterFace.class),
				new EnumByNameType<ShortcutType>(ShortcutType.class),
				new EnumByNameType<ItemLocation>(ItemLocation.class),
				new EnumByNameType<InventoryPaperdoll>(InventoryPaperdoll.class),
				new EnumByNameType<ChatMessageType>(ChatMessageType.class));
	}

	@Override
	protected void ensureDatabaseSchema(Connection conn) throws SQLException,
			IOException {
		updateSchema(conn, QActorSkill.actorSkill);
		updateSchema(conn, QCharacter.character);
		updateSchema(conn, QCharacterFriend.characterFriend);
		updateSchema(conn, QCharacterShortcut.characterShortcut);
		updateSchema(conn, QClan.clan);
		updateSchema(conn, QItem.item);
		updateSchema(conn, QLogChat.logChat);
		if (updateSchema(conn, QNPC.npc)) {
			importData(vfsService.resolve("data/static/npc.csv"), QNPC.npc);
		}
	}
}
