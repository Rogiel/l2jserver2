package com.l2jserver.model.world;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.capability.Dropable;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.character.CharacterInventory.InventoryLocation;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.item.ItemEvent;
import com.l2jserver.model.world.item.ItemListener;
import com.l2jserver.util.Coordinate;

public class Item extends AbstractObject implements Playable, Spawnable,
		Listenable<ItemListener, ItemEvent>, Dropable {
	private final ItemTemplateID templateID;
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

	public Item(ItemTemplateID templateID) {
		this.templateID = templateID;
	}

	@Override
	public void drop(Coordinate position) {
		this.coordinate = position;
	}

	@Override
	public void spawn(Coordinate coordinate) {
		this.drop(coordinate);
	}

	@Override
	public boolean isSpawned() {
		// TODO Auto-generated method stub
		return false;
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
