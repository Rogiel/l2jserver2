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
package com.l2jserver.service.database.dao.sql;

import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.model.Model;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.NPCDAO;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.mapper.NPCMapper;
import com.l2jserver.service.database.model.QNPC;
import com.l2jserver.service.database.sql.AbstractSQLDAO;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.DeleteQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.InsertQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.SelectSingleQuery;
import com.l2jserver.service.database.sql.AbstractSQLDatabaseService.UpdateQuery;
import com.mysema.query.sql.AbstractSQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

/**
 * {@link CharacterDAO} implementation for JDBC
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SQLNPCDAO extends AbstractSQLDAO<NPC, NPCID> implements NPCDAO {
	/**
	 * The {@link NPC} mapper
	 */
	private final NPCMapper mapper;

	/**
	 * @param database
	 *            the database service
	 * @param mapper
	 *            the mapper
	 */
	@Inject
	public SQLNPCDAO(DatabaseService database, NPCMapper mapper) {
		super(database);
		this.mapper = mapper;
	}

	@Override
	public NPC select(final NPCID id) {
		return database.query(new SelectSingleQuery<NPC, Integer, NPCID, QNPC>(
				QNPC.npc, mapper) {
			@Override
			protected void query(AbstractSQLQuery<?> q, QNPC e) {
				q.where(e.npcId.eq(id.getID()));
			}
		});
	}

	@Override
	public Collection<NPC> loadAll() {
		return database.query(new SelectListQuery<NPC, Integer, NPCID, QNPC>(
				QNPC.npc, mapper) {
			@Override
			protected void query(AbstractSQLQuery<?> q, QNPC e) {
			}
		});
	}

	@Override
	public List<NPC> selectByTemplate(final NPCTemplateID templateID) {
		return database.query(new SelectListQuery<NPC, Integer, NPCID, QNPC>(
				QNPC.npc, mapper) {
			@Override
			protected void query(AbstractSQLQuery<?> q, QNPC e) {
				q.where(e.npcTemplateId.eq(templateID.getID()));
			}
		});
	}

	@Override
	public Collection<NPCID> selectIDs() {
		return database.query(new SelectListQuery<NPCID, Integer, NPCID, QNPC>(
				QNPC.npc, mapper.getIDMapper(QNPC.npc)) {
			@Override
			protected void query(AbstractSQLQuery<?> q, QNPC e) {
			}
		});
	}

	@Override
	public int insertObjects(NPC... objects) {
		return database.query(new InsertQuery<NPC, Integer, NPCID, QNPC>(
				QNPC.npc, mapper, objects));
	}

	@Override
	public int updateObjects(NPC... objects) {
		return database.query(new UpdateQuery<NPC, QNPC>(QNPC.npc, mapper,
				objects) {
			@Override
			protected void query(SQLUpdateClause q, NPC o) {
				q.where(entity.npcId.eq(o.getID().getID()));
			}
		});
	}

	@Override
	public int deleteObjects(NPC... objects) {
		return database.query(new DeleteQuery<NPC, QNPC>(QNPC.npc, objects) {
			@Override
			protected void query(SQLDeleteClause q, NPC o) {
				q.where(entity.npcId.eq(o.getID().getID()));
			}
		});
	}

	@Override
	protected NPC[] wrap(Model<?>... objects) {
		final NPC[] array = new NPC[objects.length];
		int i = 0;
		for (final Model<?> object : objects) {
			array[i++] = (NPC) object;
		}
		return array;
	}
}
