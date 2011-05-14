package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.Item;

/**
 * Template for lottery ticket {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class LotteryTicketTemplate extends TicketTemplate {
	public LotteryTicketTemplate(ItemTemplateID id) {
		super(id);
	}
}
