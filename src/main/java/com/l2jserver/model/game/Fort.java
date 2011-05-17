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
package com.l2jserver.model.game;

import java.util.Date;

import com.l2jserver.model.AbstractModel;
import com.l2jserver.model.id.CastleID;
import com.l2jserver.model.id.FortID;
import com.l2jserver.model.id.object.CharacterID;

/**
 * An fort in Lineage II game world
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Fort extends AbstractModel<FortID> {
	/**
	 * The {@link Castle} of which this {@link Fort} belongs to
	 */
	private CastleID castleID;
	/**
	 * The owner (clan leader) of which this {@link Fort} belongs to.
	 */
	private CharacterID ownerID;

	/**
	 * The fort name
	 */
	private String name;
	/**
	 * The siege date
	 */
	private Date siegeDate;
	/**
	 * The last time this fort was owned by someone
	 */
	private Date lastOwnedTime;
	/**
	 * The fort type. TODO values names are unknown so far!
	 */
	private FortType fortType;

	/**
	 * The {@link Fort} types
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum FortType {
		TYPE1, TYPE2;
	}

	/**
	 * The {@link Fort} state
	 */
	private boolean state;
	/**
	 * The {@link Fort} blood state
	 */
	private boolean blood;
	/**
	 * The {@link Fort} supply level
	 */
	private int supplyLvL;

	/**
	 * @return the castleID
	 */
	public CastleID getCastleID() {
		return castleID;
	}

	/**
	 * @param castleID
	 *            the castleID to set
	 */
	public void setCastleID(CastleID castleID) {
		this.castleID = castleID;
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
	 * @return the siegeDate
	 */
	public Date getSiegeDate() {
		return siegeDate;
	}

	/**
	 * @param siegeDate
	 *            the siegeDate to set
	 */
	public void setSiegeDate(Date siegeDate) {
		this.siegeDate = siegeDate;
	}

	/**
	 * @return the lastOwnedTime
	 */
	public Date getLastOwnedTime() {
		return lastOwnedTime;
	}

	/**
	 * @param lastOwnedTime
	 *            the lastOwnedTime to set
	 */
	public void setLastOwnedTime(Date lastOwnedTime) {
		this.lastOwnedTime = lastOwnedTime;
	}

	/**
	 * @return the fortType
	 */
	public FortType getFortType() {
		return fortType;
	}

	/**
	 * @param fortType
	 *            the fortType to set
	 */
	public void setFortType(FortType fortType) {
		this.fortType = fortType;
	}

	/**
	 * @return the state
	 */
	public boolean isState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * @return the blood
	 */
	public boolean isBlood() {
		return blood;
	}

	/**
	 * @param blood
	 *            the blood to set
	 */
	public void setBlood(boolean blood) {
		this.blood = blood;
	}

	/**
	 * @return the supplyLvL
	 */
	public int getSupplyLvL() {
		return supplyLvL;
	}

	/**
	 * @param supplyLvL
	 *            the supplyLvL to set
	 */
	public void setSupplyLvL(int supplyLvL) {
		this.supplyLvL = supplyLvL;
	}
}
