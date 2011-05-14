package com.l2jserver.model.world.item;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.event.WorldEvent;

/**
 * Base event for items
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface ItemEvent extends WorldEvent {
	/**
	 * @return the item
	 */
	Item getItem();
}
