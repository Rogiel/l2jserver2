package com.l2jserver.model.game;

import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.util.dimensional.Point;

/**
 * This class represents an spawn instance of a NPC or Monster
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Spawn {
	/**
	 * The NPC template id
	 */
	private NPCTemplateID npcTemplateID;
	/**
	 * The NPC spawn point
	 */
	private Point point;

	/**
	 * @return the npcTemplate ID
	 */
	public NPCTemplateID getNPCTemplateID() {
		return npcTemplateID;
	}

	/**
	 * @return the npcTemplate
	 */
	public NPCTemplate getNPCTemplate() {
		return npcTemplateID.getTemplate();
	}

	/**
	 * @param npcTemplateID
	 *            the npcTemplate ID to set
	 */
	public void setNPCTemplateID(NPCTemplateID npcTemplateID) {
		this.npcTemplateID = npcTemplateID;
	}

	/**
	 * @return the point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(Point point) {
		this.point = point;
	}
}
