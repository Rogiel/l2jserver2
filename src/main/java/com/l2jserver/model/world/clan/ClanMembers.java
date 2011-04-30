package com.l2jserver.model.world.clan;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.l2jserver.model.id.CharacterID;
import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This class handles members inside an clan
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ClanMembers implements Iterable<CharacterID> {
	/**
	 * The parent {@link Clan}
	 */
	private final Clan clan;

	/**
	 * The list of active members
	 */
	private final Set<CharacterID> members = CollectionFactory
			.newSet(CharacterID.class);

	public ClanMembers(Clan clan) {
		this.clan = clan;
	}

	/**
	 * Effectively add this character as a clan member
	 * 
	 * @param character
	 *            the character
	 * @return true if add, false otherwise
	 */
	public boolean add(L2Character character) {
		return members.add(character.getID());
	}

	/**
	 * Effectively add this character as a clan member
	 * 
	 * @param character
	 *            the character
	 * @return true if add, false otherwise
	 */
	public boolean remove(L2Character character) {
		return members.remove(character.getID());
	}

	/**
	 * Load an list of members to this clan
	 * 
	 * @param members
	 *            the list of members ids
	 */
	public void load(List<CharacterID> members) {
		this.members.addAll(members);
	}

	/**
	 * @return the clan
	 */
	public Clan getClan() {
		return clan;
	}

	@Override
	public Iterator<CharacterID> iterator() {
		return members.iterator();
	}
}
