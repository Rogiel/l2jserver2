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

import java.sql.Date;

import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.object.ActorID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.PetID;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.model.world.character.CharacterAppearance;
import com.l2jserver.model.world.character.CharacterFriendList;
import com.l2jserver.model.world.character.CharacterInventory;
import com.l2jserver.model.world.character.CharacterShortcutContainer;
import com.l2jserver.model.world.character.CharacterStats;
import com.l2jserver.util.geometry.Point3D;

/**
 * This class represents a playable character in Lineage II world.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class L2Character extends Player {
	/**
	 * The account id
	 */
	private AccountID accountID;
	/**
	 * The clan id
	 */
	private ClanID clanID;
	/**
	 * The pet id
	 */
	private PetID petID;

	/**
	 * The character race
	 */
	protected CharacterRace race;

	/**
	 * This character's inventory
	 */
	private final CharacterInventory inventory = new CharacterInventory(this);
	/**
	 * The appearance of this character
	 */
	private final CharacterAppearance appearance = new CharacterAppearance(this);
	/**
	 * The list of friend of this character
	 */
	private final CharacterFriendList friendList = new CharacterFriendList(this);
	/**
	 * The shortcut container of this character
	 */
	private final CharacterShortcutContainer shortcuts = new CharacterShortcutContainer(
			this);

	/**
	 * The character name
	 */
	private String name;
	/**
	 * The character title. Can be null.
	 */
	private String title;
	/**
	 * The class of the character
	 */
	private CharacterClass characterClass;

	/**
	 * The character CP
	 */
	private double CP;

	/**
	 * Date of character's last access
	 */
	private Date lastAccess;

	/**
	 * The character karma points
	 */
	private int karma;
	/**
	 * The character PK kills
	 */
	private int pkKills;
	/**
	 * The character PVP kills
	 */
	private int pvpKills;

	// ////////////////////////////////////
	// / RUNTIME
	// ////////////////////////////////////
	/**
	 * The character stat
	 */
	private transient final CharacterStats stats = new CharacterStats(this);
	/**
	 * The character walk mode.
	 * <p>
	 * This field is not persisted.
	 */
	private transient CharacterMoveType moveType = CharacterMoveType.WALK;

	/**
	 * The character walking mode
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum CharacterMoveType {
		/**
		 * Run mode
		 */
		RUN(0x01),

		/**
		 * Walk mode
		 */
		WALK(0x00);

		/**
		 * The move mode numeric id
		 */
		public final int id;

		/**
		 * @param id
		 *            the mode numeric id
		 */
		CharacterMoveType(int id) {
			this.id = id;
		}
	}

	/**
	 * The character target, if any.
	 */
	private transient ActorID<?> targetID;

	/**
	 * The point the player is moving, teleporting etc...
	 */
	private transient Point3D targetLocation;

	/**
	 * Creates a new instance
	 * 
	 * @param template
	 *            the {@link CharacterTemplateID}
	 */
	public L2Character(CharacterTemplate template) {
		super(template.getID());
		this.characterClass = template.getID().getCharacterClass();
		this.race = this.characterClass.race;
	}

	/**
	 * @return the account ID
	 */
	public AccountID getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID
	 *            the account ID to set
	 */
	public void setAccountID(AccountID accountID) {
		desireUpdate();
		this.accountID = accountID;
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
		desireUpdate();
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
		desireUpdate();
		this.petID = petID;
	}

	/**
	 * @return the race
	 */
	public CharacterRace getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public void setRace(CharacterRace race) {
		desireUpdate();
		this.race = race;
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
		desireUpdate();
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		desireUpdate();
		this.title = title;
	}

	/**
	 * @return the characterClass
	 */
	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	/**
	 * @param characterClass
	 *            the characterClass to set
	 */
	public void setCharacterClass(CharacterClass characterClass) {
		desireUpdate();
		this.characterClass = characterClass;
	}

	/**
	 * @return the character CP
	 */
	public double getCP() {
		return CP;
	}

	/**
	 * @param CP
	 *            the character CP to set
	 */
	public void setCP(double CP) {
		desireUpdate();
		this.CP = CP;
	}

	/**
	 * @return the lastAccess
	 */
	public Date getLastAccess() {
		return lastAccess;
	}

	/**
	 * @param lastAccess
	 *            the lastAccess to set
	 */
	public void setLastAccess(Date lastAccess) {
		desireUpdate();
		this.lastAccess = lastAccess;
	}

	/**
	 * @return the stats
	 */
	@Override
	public CharacterStats getStats() {
		return stats;
	}

	/**
	 * @return the character karma points
	 */
	public int getKarma() {
		return karma;
	}

	/**
	 * @param karma
	 *            the character karma points to set
	 */
	public void setKarma(int karma) {
		desireUpdate();
		this.karma = karma;
	}

	/**
	 * @return the character PK kills
	 */
	public int getPkKills() {
		return pkKills;
	}

	/**
	 * @param pkKills
	 *            the character PK kills to set
	 */
	public void setPkKills(int pkKills) {
		desireUpdate();
		this.pkKills = pkKills;
	}

	/**
	 * @return the character PVP kills
	 */
	public int getPvpKills() {
		return pvpKills;
	}

	/**
	 * @param pvpKills
	 *            the character PVP kills to set
	 */
	public void setPvpKills(int pvpKills) {
		desireUpdate();
		this.pvpKills = pvpKills;
	}

	/**
	 * @return the moveType
	 */
	public CharacterMoveType getMoveType() {
		return moveType;
	}

	/**
	 * @param moveType
	 *            the moveType to set
	 */
	public void setMoveType(CharacterMoveType moveType) {
		this.moveType = moveType;
	}

	/**
	 * @return the target ID
	 */
	public ActorID<?> getTargetID() {
		return targetID;
	}

	/**
	 * @return the target
	 */
	public Actor getTarget() {
		if (targetID == null)
			return null;
		return targetID.getObject();
	}

	/**
	 * @param target
	 *            the target ID to set
	 */
	public void setTargetID(ActorID<?> target) {
		this.targetID = target;
	}

	/**
	 * @return true if character is alive
	 */
	public boolean isAlive() {
		return !isDead();
	}

	/**
	 * @return the targetLocation
	 */
	public Point3D getTargetLocation() {
		return targetLocation;
	}

	/**
	 * @param targetLocation
	 *            the targetLocation to set
	 */
	public void setTargetLocation(Point3D targetLocation) {
		this.targetLocation = targetLocation;
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

	/**
	 * @return the friendList
	 */
	public CharacterFriendList getFriendList() {
		return friendList;
	}

	/**
	 * @return the shortcuts
	 */
	public CharacterShortcutContainer getShortcuts() {
		return shortcuts;
	}

	@Override
	public CharacterTemplateID getTemplateID() {
		return (CharacterTemplateID) super.getTemplateID();
	}

	@Override
	public CharacterTemplate getTemplate() {
		return (CharacterTemplate) super.getTemplate();
	}

	@Override
	public CharacterID getID() {
		return (CharacterID) super.getID();
	}
}
