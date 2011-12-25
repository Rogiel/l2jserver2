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
package com.l2jserver.service.database.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.id.object.NPCID;
import com.l2jserver.model.id.object.provider.NPCIDProvider;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.NPCTemplate;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.database.dao.AbstractMapper;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.PrimaryKeyMapper;
import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.l2jserver.service.database.model.QNPC;
import com.l2jserver.util.geometry.Point3D;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class NPCMapper extends AbstractMapper<NPC, Integer, NPCID, QNPC> {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final PrimaryKeyMapper<NPCID, Integer> idMapper = new PrimaryKeyMapper<NPCID, Integer>() {
		@Override
		public NPCID createID(Integer raw) {
			return idProvider.resolveID(raw);
		}
	};

	/**
	 * The {@link NPCID} provider
	 */
	private final NPCIDProvider idProvider;
	/**
	 * The {@link NPCTemplateID} provider
	 */
	private final NPCTemplateIDProvider templateIdProvider;

	/**
	 * @param idProvider
	 *            the {@link NPCID} provider
	 * @param templateIdProvider
	 *            the item template id provider
	 */
	@Inject
	public NPCMapper(final NPCIDProvider idProvider,
			NPCTemplateIDProvider templateIdProvider) {
		this.idProvider = idProvider;
		this.templateIdProvider = templateIdProvider;
	}

	@Override
	public NPC select(QNPC e, DatabaseRow row) {
		final NPCID id = idProvider.resolveID(row.get(e.npcId));

		NPCTemplateID templateId = templateIdProvider.resolveID(row
				.get(e.npcTemplateId));
		NPCTemplate template = templateId.getTemplate();
		if (template == null) {
			log.warn("No template found for {}", templateId);
			return null;
		}

		final NPC npc = template.create();
		npc.setID(id);

		if (!row.isNull(e.hp))
			npc.setHP(row.get(e.hp));
		if (!row.isNull(e.mp))
			npc.setMP(row.get(e.mp));

		if (!row.isNull(e.pointX) && !row.isNull(e.pointY)
				&& !row.isNull(e.pointZ) && !row.isNull(e.pointAngle))
			npc.setPoint(Point3D.fromXYZA(row.get(e.pointX), row.get(e.pointY),
					row.get(e.pointZ), row.get(e.pointAngle)));

		npc.setRespawnInterval(row.get(e.respawnTime));

		return npc;
	}

	@Override
	public void insert(QNPC e, NPC object, WritableDatabaseRow row) {
		update(e, object, row);
	}

	@Override
	public void update(QNPC e, NPC object, WritableDatabaseRow row) {
		row.set(e.npcId, object.getID().getID())
				.set(e.npcTemplateId, object.getTemplateID().getID())
				.set(e.hp, object.getHP())
				.set(e.mp, object.getMP())
				.set(e.pointX,
						(object.getPoint() != null ? object.getPoint().getX()
								: null))
				.set(e.pointY,
						(object.getPoint() != null ? object.getPoint().getY()
								: null))
				.set(e.pointZ,
						(object.getPoint() != null ? object.getPoint().getZ()
								: null))
				.set(e.pointAngle,
						(object.getPoint() != null ? object.getPoint()
								.getAngle() : null))

				.set(e.respawnTime, object.getRespawnInterval());
	}

	@Override
	public PrimaryKeyMapper<NPCID, Integer> getPrimaryKeyMapper() {
		return idMapper;
	}
}