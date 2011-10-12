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
package com.l2jserver.model.template.npc;

import javax.xml.bind.annotation.XmlType;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlType(name = "NPCRaceType")
public enum NPCRace {
	// character races
	HUMAN, ELVEN, DARKELVEN, ORC, DWARVEN, KAMAEL,

	// npc exclusive
	UNDEAD, MAGIC_CREATURE, BEAST, ANIMAL, PLANT, HUMANOID, SPIRIT,

	ANGEL, DEMON, DRAGON, GIANT, BUG, FAIRIE, OTHER, NON_LIVING,

	SIEGE_WEAPON, DEFENDING_ARMY, MERCENARIE, UNKNOWN, NONE;
}
