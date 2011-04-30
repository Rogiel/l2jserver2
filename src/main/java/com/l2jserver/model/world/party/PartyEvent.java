package com.l2jserver.model.world.party;

import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.event.WorldEvent;

public interface PartyEvent extends WorldEvent {
	Clan getClan();
}
