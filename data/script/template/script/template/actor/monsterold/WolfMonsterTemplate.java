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
package script.template.actor.monsterold;

import script.template.DisabledTemplate;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.template.npc.MonsterNPCTemplate;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@DisabledTemplate
public class WolfMonsterTemplate extends MonsterNPCTemplate {
	public static final int ID = 20120;

	@Inject
	protected WolfMonsterTemplate(NPCTemplateIDProvider provider) {
		super(provider.createID(ID));
		this.name = "Wolf";
		this.title = "Test Monster";
		this.attackable = true;
		this.maxHP = 200;
		this.collisionRadius = 13.00;
		this.collisionHeight = 9.00;
	}
}
