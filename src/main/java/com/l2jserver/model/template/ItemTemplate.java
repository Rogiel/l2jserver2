package com.l2jserver.model.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.world.Item;

/**
 * Template for an {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ItemTemplate extends AbstractTemplate<Item> {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ItemTemplate.class);

	public ItemTemplate(ItemTemplateID id) {
		super(id);
	}

	@Override
	public Item create() {
		log.debug("Creating a new Item instance with template {}", this);
		return new Item(this.getID());
	}

	@Override
	public ItemTemplateID getID() {
		return (ItemTemplateID) super.getID();
	}
}
