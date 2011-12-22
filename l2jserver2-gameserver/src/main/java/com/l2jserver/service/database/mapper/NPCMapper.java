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
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.Mapper;
import com.l2jserver.service.database.model.QNPC;
import com.l2jserver.util.geometry.Point3D;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class NPCMapper implements Mapper<NPC, QNPC> {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final Mapper<NPCID, QNPC> idMapper = new Mapper<NPCID, QNPC>() {
		@Override
		public NPCID map(QNPC e, DatabaseRow row) {
			return idProvider.resolveID(row.get(e.npcId));
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
	public NPC map(QNPC e, DatabaseRow row) {
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

	public Mapper<NPCID, QNPC> getIDMapper() {
		return idMapper;
	}
}