package com.l2jserver.model.world;

import java.util.Iterator;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.iterator.WorldObjectIterator;
import com.l2jserver.model.world.capability.Joinable;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.clan.ClanEvent;
import com.l2jserver.model.world.clan.ClanListener;
import com.l2jserver.model.world.clan.ClanMembers;

/**
 * This class represents an clan in the Lineage II world
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Clan extends AbstractObject implements
		Listenable<ClanListener, ClanEvent>, Joinable<L2Character> {
	/**
	 * Clan leader
	 */
	private CharacterID leaderID;

	/**
	 * Members in the clan
	 */
	private final ClanMembers members = new ClanMembers(this);

	@Override
	public void join(L2Character member) {
		members.add(member);
	}

	@Override
	public void leave(L2Character member) {
		members.remove(member);
	}

	/**
	 * @return the leaderID
	 */
	public CharacterID getLeaderID() {
		return leaderID;
	}

	/**
	 * @return the leader
	 */
	public L2Character getLeader() {
		return leaderID.getObject();
	}

	/**
	 * @param leaderID
	 *            the leaderID to set
	 */
	public void setLeaderID(CharacterID leaderID) {
		this.leaderID = leaderID;
	}

	@Override
	public ClanID getID() {
		return (ClanID) super.getID();
	}

	@Override
	public Iterator<L2Character> iterator() {
		return new WorldObjectIterator<L2Character>(members.iterator());
	}
}
