package com.l2jserver.model.world.clan;

import com.l2jserver.model.world.Clan;
import com.l2jserver.model.world.event.WorldEvent;

/**
 * Base event for {@link Clan} objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ClanEvent extends WorldEvent {
	/**
	 * @return the clan
	 */
	Clan getClan();
}
