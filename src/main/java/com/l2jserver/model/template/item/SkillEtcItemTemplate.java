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
package com.l2jserver.model.template.item;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;

/**
 * Template for general items {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class SkillEtcItemTemplate extends ItemTemplate {
	protected SkillTemplateID skill;

	public SkillEtcItemTemplate(ItemTemplateID id, String icon,
			ItemMaterial material, SkillTemplateID skill) {
		super(id, icon, material);
	}

	@Override
	protected void use(Item item, L2Character character, Lineage2Connection conn) {
		
		super.use(item, character, conn);
	}

	/**
	 * @return the skill
	 */
	public SkillTemplateID getSkill() {
		return skill;
	}
}
