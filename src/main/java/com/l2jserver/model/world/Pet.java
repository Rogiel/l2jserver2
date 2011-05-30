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
package com.l2jserver.model.world;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.template.ActorTemplateID;
import com.l2jserver.model.world.actor.stat.ActorStats;

/**
 * This class represents an Pet in the Lineage II World
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Pet extends Player {
	/**
	 * The {@link CharacterID} pet's owner
	 */
	private CharacterID ownerID;
	/**
	 * {@link ItemID} used to summon this pet
	 */
	private ItemID itemID;

	public Pet(ActorTemplateID<?> templateID) {
		super(templateID);
	}

	/**
	 * @return the owner ID
	 */
	public CharacterID getOwnerID() {
		return ownerID;
	}

	/**
	 * @return the owner
	 */
	public L2Character getOwner() {
		return ownerID.getObject();
	}

	/**
	 * @param ownerID
	 *            the owner ID to set
	 */
	public void setOwnerID(CharacterID ownerID) {
		this.ownerID = ownerID;
	}

	/**
	 * @return the item ID
	 */
	public ItemID getItemID() {
		return itemID;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return itemID.getObject();
	}

	/**
	 * @param itemID
	 *            the item ID to set
	 */
	public void setItemID(ItemID itemID) {
		this.itemID = itemID;
	}

	/* (non-Javadoc)
	 * @see com.l2jserver.model.world.Actor#getStats()
	 */
	@Override
	public ActorStats<?> getStats() {
		// TODO Auto-generated method stub
		return null;
	}
}
