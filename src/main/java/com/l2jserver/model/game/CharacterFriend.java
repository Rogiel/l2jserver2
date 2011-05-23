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

import com.l2jserver.model.AbstractModel;
import com.l2jserver.model.id.FriendID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;

/**
 * Represents a pair of two {@link CharacterID} whose characters are friends.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterFriend extends AbstractModel<FriendID> {
	public CharacterFriend(FriendID id) {
		this.setID(id);
	}
	
	public CharacterID getCharacterID() {
		return id.getID1();
	}

	public L2Character getCharacter() {
		return id.getID1().getObject();
	}

	public CharacterID getFriendID() {
		return id.getID2();
	}

	public L2Character getFriend() {
		return id.getID2().getObject();
	}
}
