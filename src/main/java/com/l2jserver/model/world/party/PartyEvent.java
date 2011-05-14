package com.l2jserver.model.world.party;

import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.Party;
import com.l2jserver.model.world.event.WorldEvent;

/**
 * Base event for {@link Party} objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface PartyEvent extends WorldEvent {
	Clan getClan();
}
