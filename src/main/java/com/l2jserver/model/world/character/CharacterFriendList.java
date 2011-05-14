package com.l2jserver.model.world.character;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.iterator.WorldObjectIterator;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Controls the friend list of an {@link L2Character}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterFriendList implements Iterable<L2Character> {
	/**
	 * The character
	 */
	private final L2Character character;

	/**
	 * The list of friends of this character
	 */
	private final Set<CharacterID> friends = CollectionFactory
			.newSet(CharacterID.class);

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
	public Iterator<CharacterID> idIterator() {
		return friends.iterator();
	}

	@Override
	public Iterator<L2Character> iterator() {
		return new WorldObjectIterator<L2Character>(friends.iterator());
	}

	/**
	 * Load an {@link Collection} of {@link CharacterID} to this object.
	 * <p>
	 * Note that this is normally used by DAOs do load data.
	 * 
	 * @param list
	 *            the id list
	 */
	public void load(Collection<CharacterID> list) {
		friends.addAll(list);
	}
}
