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
package com.l2jserver.db.dao.mysql5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.db.dao.NPCDAO;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.allocator.IDAllocator;
import com.l2jserver.model.id.object.provider.NPCIDProvider;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.database.DatabaseService;
import com.l2jserver.service.database.MySQLDatabaseService.CachedMapper;
import com.l2jserver.service.database.MySQLDatabaseService.InsertUpdateQuery;
import com.l2jserver.service.database.MySQLDatabaseService.Mapper;
import com.l2jserver.service.database.MySQLDatabaseService.SelectListQuery;
import com.l2jserver.service.database.MySQLDatabaseService.SelectSingleQuery;
import com.l2jserver.util.dimensional.Point;

/**
 * {@link CharacterDAO} implementation for MySQL5
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MySQL5NPCDAO extends AbstractMySQL5DAO<NPC, NPCID> implements
		NPCDAO {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link NPCID} provider
	 */
	private final NPCIDProvider idProvider;
	/**
	 * The {@link NPCTemplateID} provider
	 */
	private final NPCTemplateIDProvider templateIdProvider;

	/**
	 * Character table name
	 */
	public static final String TABLE = "npc";
	// FIELDS
	public static final String NPC_ID = "npc_id";
	public static final String NPC_TEMPLATE_ID = "npc_template_id";

	public static final String POINT_X = "point_x";
	public static final String POINT_Y = "point_y";
	public static final String POINT_Z = "point_z";
	public static final String POINT_ANGLE = "point_angle";

	@Inject
	public MySQL5NPCDAO(DatabaseService database,
			final NPCIDProvider idProvider,
			NPCTemplateIDProvider templateIdProvider) {
		super(database);
		this.idProvider = idProvider;
		this.templateIdProvider = templateIdProvider;
	}

	/**
	 * The {@link Mapper} for {@link NPC}
	 */
	private final Mapper<NPC> mapper = new CachedMapper<NPC, NPCID>(database) {
		@Override
		protected NPCID createID(ResultSet rs) throws SQLException {
			if (rs.getInt(NPC_ID) < IDAllocator.FIRST_ID)
				return idProvider.createID();
			return idProvider.createID(rs.getInt(NPC_ID));
		}

		@Override
		protected NPC map(NPCID id, ResultSet rs) throws SQLException {
			NPCTemplateID templateId = templateIdProvider.createID(rs
					.getInt(NPC_TEMPLATE_ID));
			NPCTemplate template = templateId.getTemplate();
			if (template == null) {
				// set default npc instance, for now!
				// RoxxyGatekeeperTemplate - 30006
				log.warn("No template found for {}", templateId);
				return null;
			}

			final NPC npc = template.create();

			npc.setID(id);
			npc.setPoint(Point.fromXYZA(rs.getInt(POINT_X), rs.getInt(POINT_Y),
					rs.getInt(POINT_Z), rs.getDouble(POINT_ANGLE)));

			return npc;
		}
	};

	/**
	 * The {@link Mapper} for {@link NPCID}
	 */
	private final Mapper<NPCID> idMapper = new Mapper<NPCID>() {
		@Override
		public NPCID map(ResultSet rs) throws SQLException {
			if (rs.getString(NPC_ID) == null)
				return null;
			return idProvider.createID(rs.getInt(NPC_ID));
		}
	};

	@Override
	public NPC select(final NPCID id) {
		return database.query(new SelectSingleQuery<NPC>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `" + NPC_ID
						+ "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, id.getID());
			}

			@Override
			protected Mapper<NPC> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<NPC> loadAll() {
		return database.query(new SelectListQuery<NPC>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<NPC> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<NPC> selectByTemplate(final NPCTemplateID template) {
		return database.query(new SelectListQuery<NPC>() {
			@Override
			protected String query() {
				return "SELECT * FROM `" + TABLE + "` WHERE `"
						+ NPC_TEMPLATE_ID + "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st) throws SQLException {
				st.setInt(1, template.getID());
			}

			@Override
			protected Mapper<NPC> mapper() {
				return mapper;
			}
		});
	}

	@Override
	public List<NPCID> listIDs() {
		return database.query(new SelectListQuery<NPCID>() {
			@Override
			protected String query() {
				return "SELECT `" + NPC_ID + "` FROM `" + TABLE + "`";
			}

			@Override
			protected Mapper<NPCID> mapper() {
				return idMapper;
			}
		});
	}

	@Override
	public boolean insert(NPC npc) {
		return database.query(new InsertUpdateQuery<NPC>(npc) {
			@Override
			protected String query() {
				return "INSERT INTO `" + TABLE + "` (`" + NPC_ID + "`,`"
						+ NPC_TEMPLATE_ID + "`,`" + POINT_X + "`,`" + POINT_Y
						+ "`,`" + POINT_Z + "`,`" + POINT_ANGLE
						+ "`) VALUES(?,?,?,?,?,?)";
			}

			@Override
			protected void parametize(PreparedStatement st, NPC npc)
					throws SQLException {
				int i = 1;

				st.setInt(i++, npc.getID().getID());
				st.setInt(i++, npc.getTemplateID().getID());

				st.setInt(i++, npc.getPoint().getX());
				st.setInt(i++, npc.getPoint().getY());
				st.setInt(i++, npc.getPoint().getZ());
				st.setDouble(i++, npc.getPoint().getAngle());
			}
		}) > 0;
	}

	@Override
	public boolean update(NPC npc) {
		return database.query(new InsertUpdateQuery<NPC>(npc) {
			@Override
			protected String query() {
				return "UPDATE `" + TABLE + "` SET " + NPC_TEMPLATE_ID
						+ "` = ?,`" + POINT_X + "` = ?,`" + POINT_Y + "` = ?,`"
						+ POINT_Z + "` = ?,`" + POINT_ANGLE + "` = ? WHERE `"
						+ NPC_ID + "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st, NPC npc)
					throws SQLException {
				int i = 1;

				// SET
				st.setInt(i++, npc.getTemplateID().getID());

				st.setInt(i++, npc.getPoint().getX());
				st.setInt(i++, npc.getPoint().getY());
				st.setInt(i++, npc.getPoint().getZ());
				st.setDouble(i++, npc.getPoint().getAngle());

				// WHERE
				st.setInt(i++, npc.getID().getID());
			}
		}) > 0;
	}

	@Override
	public boolean delete(NPC npc) {
		return database.query(new InsertUpdateQuery<NPC>(npc) {
			@Override
			protected String query() {
				return "DELETE FROM `" + TABLE + "` WHERE `" + NPC_ID + "` = ?";
			}

			@Override
			protected void parametize(PreparedStatement st, NPC npc)
					throws SQLException {
				st.setInt(1, npc.getID().getID());
			}
		}) > 0;
	}
}
