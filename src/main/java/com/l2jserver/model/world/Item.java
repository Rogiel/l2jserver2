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
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.capability.Dropable;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.character.CharacterInventory.InventoryLocation;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.item.ItemEvent;
import com.l2jserver.model.world.item.ItemListener;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * This class represents an {@link Item} in the Lineage II World. The item can
 * be:
 * <ul>
 * <li><b>In the {@link L2Character character} inventory</b>: <tt>location</tt>
 * is {@link InventoryLocation#INVENTORY}, <tt>coordinate</tt> and
 * <tt>paperdoll</tt> are null.</li>
 * <li><b>In the {@link L2Character character} warehouse</b>: <tt>location</tt>
 * is {@link InventoryLocation#WAREHOUSE}, <tt>coordinate</tt> and
 * <tt>paperdoll</tt> are null.</li>
 * <li><b>Equipped by the {@link L2Character character}</b>: <tt>location</tt>
 * is {@link InventoryLocation#PAPERDOLL}, <tt>paperdoll</tt> is not null and
 * <tt>coordinate</tt> is null.</li>
 * <li><b>Dropped on the ground</b>: <tt>location</li> and <tt>paperdoll</tt>
 * are null, <tt>coordinate</tt> is not null and represents the dropping
 * location.
 * </ul>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Item extends AbstractObject implements Playable, Spawnable,
		Listenable<ItemListener, ItemEvent>, Dropable {
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
	private InventoryLocation location;
	/**
	 * Paperdoll slot for this item
	 */
	private InventoryPaperdoll paperdoll;
	/**
	 * Drop coordinate of this item
	 */
	private Coordinate coordinate;

	/**
	 * Count of items
	 */
	private int count = 1;

	public Item(ItemTemplateID templateID) {
		this.templateID = templateID;
	}

	@Override
	public void drop(Coordinate position) {
		this.coordinate = position;
		this.location = null;
		this.paperdoll = null;
	}

	@Override
	public void spawn(Coordinate coordinate) {
		this.drop(coordinate);
	}

	@Override
	public boolean isSpawned() {
		return (location != null);
	}

	@Override
	public Coordinate getPosition() {
		return coordinate;
	}

	@Override
	public void setPosition(Coordinate coord) {
		this.coordinate = coord;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the location
	 */
	public InventoryLocation getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(InventoryLocation location) {
		this.location = location;
		if (location != InventoryLocation.PAPERDOLL)
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
		this.paperdoll = paperdoll;
	}

	/**
	 * @return the templateID
	 */
	public ItemTemplateID getTemplateID() {
		return templateID;
	}

	/**
	 * @return the ownerID
	 */
	public CharacterID getOwnerID() {
		return ownerID;
	}

	/**
	 * @param ownerID
	 *            the ownerID to set
	 */
	public void setOwnerID(CharacterID ownerID) {
		this.ownerID = ownerID;
	}
}
