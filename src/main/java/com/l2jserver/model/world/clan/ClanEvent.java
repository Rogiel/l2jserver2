package com.l2jserver.model.world.clan;

import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.event.WorldEvent;

public interface ClanEvent extends WorldEvent {
	Clan getClan();
}
