package com.l2jserver.model.world;

import java.util.Iterator;
import java.util.List;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ClanID;
import com.l2jserver.model.id.object.iterator.WorldObjectIterator;
import com.l2jserver.model.world.capability.Joinable;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.party.PartyEvent;
import com.l2jserver.model.world.party.PartyListener;
import com.l2jserver.util.factory.CollectionFactory;

public class Party extends AbstractObject implements
		Listenable<PartyListener, PartyEvent>, Joinable<L2Character> {
	private final List<CharacterID> members = CollectionFactory
			.newList(CharacterID.class);

	@Override
	public void join(L2Character member) {
		members.add(member.getID());
	}

	@Override
	public ClanID getID() {
		return (ClanID) super.getID();
	}

	@Override
	public void leave(L2Character member) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<L2Character> iterator() {
		return new WorldObjectIterator<L2Character>(members.iterator());
	}
}
