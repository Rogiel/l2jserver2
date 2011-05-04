package com.l2jserver.model.world.character;

import java.util.Iterator;
import java.util.Set;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Defines how an character looks in-game.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterFriendList implements Iterable<L2Character> {
	private final L2Character character;

	private final Set<CharacterID> friends = CollectionFactory
			.newSet(CharacterID.class);

	public CharacterFriendList(L2Character character) {
		this.character = character;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}

	@Override
	public Iterator<L2Character> iterator() {
		// TODO
		return null;
	}
}
