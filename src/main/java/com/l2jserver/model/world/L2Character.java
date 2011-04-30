package com.l2jserver.model.world;

import com.l2jserver.model.id.CharacterID;
import com.l2jserver.model.id.ClanID;
import com.l2jserver.model.id.PetID;
import com.l2jserver.model.world.character.CharacterAppearance;
import com.l2jserver.model.world.character.CharacterInventory;

/**
 * This class represents a playable character in Lineage II world.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class L2Character extends Player {
	/**
	 * The clan id
	 */
	private ClanID clanID;
	/**
	 * The pet id
	 */
	private PetID petID;
	/**
	 * The character name
	 */
	private String name;
	/**
	 * The character's status
	 */
	private boolean online;

	/**
	 * This character's inventory
	 */
	private final CharacterInventory inventory = new CharacterInventory(this);
	/**
	 * The appearance of this character
	 */
	private final CharacterAppearance appearance = new CharacterAppearance(this);

	@Override
	public CharacterID getID() {
		return (CharacterID) super.getID();
	}

	/**
	 * @return the clanID
	 */
	public ClanID getClanID() {
		return clanID;
	}

	/**
	 * @return the clan
	 */
	public Clan getClan() {
		if (clanID == null)
			return null;
		return clanID.getObject();
	}

	/**
	 * @param clanID
	 *            the clanID to set
	 */
	public void setClanID(ClanID clanID) {
		this.clanID = clanID;
	}

	/**
	 * @return the petID
	 */
	public PetID getPetID() {
		return petID;
	}

	/**
	 * @return the pet
	 */
	public Pet getPet() {
		if (petID == null)
			return null;
		return petID.getObject();
	}

	/**
	 * @param petID
	 *            the petID to set
	 */
	public void setPetID(PetID petID) {
		this.petID = petID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}

	/**
	 * @param online
	 *            the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

	/**
	 * @return the inventory
	 */
	public CharacterInventory getInventory() {
		return inventory;
	}

	/**
	 * @return the appearance
	 */
	public CharacterAppearance getAppearance() {
		return appearance;
	}
}
