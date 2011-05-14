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
	
	private int count = 1;

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
