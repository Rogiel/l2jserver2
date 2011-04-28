package com.l2jserver.model.world.item;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.event.WorldEvent;

public interface ItemEvent extends WorldEvent {
	Item getItem();
}
