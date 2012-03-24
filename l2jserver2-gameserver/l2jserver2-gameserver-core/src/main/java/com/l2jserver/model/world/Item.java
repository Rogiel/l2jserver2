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
package com.l2jserver.model.world;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;

/**
 * This class represents an {@link Item} in the Lineage II World. The item can
 * be:
 * <ul>
 * <li><b>In the {@link L2Character character} inventory</b>: <tt>location</tt>
 * is {@link ItemLocation#INVENTORY}, <tt>coordinate</tt> and <tt>paperdoll</tt>
 * are null.</li>
 * <li><b>In the {@link L2Character character} warehouse</b>: <tt>location</tt>
 * is {@link ItemLocation#WAREHOUSE}, <tt>coordinate</tt> and <tt>paperdoll</tt>
 * are null.</li>
 * <li><b>Equipped by the {@link L2Character character}</b>: <tt>location</tt>
 * is {@link ItemLocation#PAPERDOLL}, <tt>paperdoll</tt> is not null and
 * <tt>coordinate</tt> is null.</li>
 * <li><b>Dropped on the ground</b>: <tt>location</tt></li> and
 * <tt>paperdoll</tt> are null, <tt>coordinate</tt> is not null and represents
 * the dropping location.
 * </ul>
 * <p>
 * Please note that {@link PositionableObject} values are only set if the object
 * is dropped on the ground.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Item extends PositionableObject {
	/**
	 * The {@link ItemTemplate} ID
	 */
	private final ItemTemplateID templateID;
	/**
	 * The {@link L2Character} ID owner of this object
	 */
	private CharacterID ownerID;

	/**
	 * Inventory location of this item
	 */
	private ItemLocation location = ItemLocation.INVENTORY;
	/**
	 * Paperdoll slot for this item
	 */
	private InventoryPaperdoll paperdoll;

	/**
	 * Count of items
	 */
	private long count = 1;

	/**
	 * @param template
	 *            the item template
	 */
	public Item(ItemTemplate template) {
		this.templateID = template.getID();
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(long count) {
		desireUpdate();
		this.count = count;
	}

	/**
	 * @return the location
	 */
	public ItemLocation getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(ItemLocation location) {
		desireUpdate();
		this.location = location;
		if (location != ItemLocation.PAPERDOLL)
			this.paperdoll = null;
	}

	/**
	 * @return the paperdoll
	 */
	public InventoryPaperdoll getPaperdoll() {
		return paperdoll;
	}

	/**
	 * @param paperdoll
	 *            the paperdoll to set
	 */
	public void setPaperdoll(InventoryPaperdoll paperdoll) {
		desireUpdate();
		this.paperdoll = paperdoll;
	}

	/**
	 * @return the templateID
	 */
	public ItemTemplateID getTemplateID() {
		return templateID;
	}

	/**
	 * @return the templateID
	 */
	public ItemTemplate getTemplate() {
		return templateID.getTemplate();
	}

	/**
	 * @return the ownerID
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
	 *            the ownerID to set
	 */
	public void setOwnerID(CharacterID ownerID) {
		desireUpdate();
		this.ownerID = ownerID;
	}

	@Override
	public ItemID getID() {
		return (ItemID) super.getID();
	}
}
