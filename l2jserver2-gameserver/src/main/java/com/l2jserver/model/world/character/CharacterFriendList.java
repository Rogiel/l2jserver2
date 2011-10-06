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
package com.l2jserver.model.world.character;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.l2jserver.model.game.CharacterFriend;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Controls the friend list of an {@link L2Character}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterFriendList implements Iterable<CharacterFriend> {
	/**
	 * The character
	 */
	private final L2Character character;

	/**
	 * The list of friends of this character
	 */
	private final Set<CharacterFriend> friends = CollectionFactory.newSet();

	/**
	 * Creates a new instance
	 * 
	 * @param character
	 *            the parent character
	 */
	public CharacterFriendList(L2Character character) {
		this.character = character;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}

	/**
	 * Iterator containing all friends IDs
	 * 
	 * @return an iterator with friend ids
	 */
	public Iterator<CharacterFriend> idIterator() {
		return friends.iterator();
	}

	@Override
	public Iterator<CharacterFriend> iterator() {
		// return new WorldObjectIterator<L2Character>(friends.iterator());
		// FIXME
		return null;
	}

	/**
	 * Load an {@link Collection} of {@link CharacterID} to this object.
	 * <p>
	 * Note that this is normally used by DAOs do load data.
	 * 
	 * @param list
	 *            the id list
	 */
	public void load(Collection<CharacterFriend> list) {
		friends.addAll(list);
	}
}
