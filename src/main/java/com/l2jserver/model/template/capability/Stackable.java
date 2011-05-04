package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.WorldObject;

/**
 * Defines an template that can be stacked (i.e. more then one item per slot in
 * the inventory)
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Stackable<T extends WorldObject> extends TemplateCapability {
	void stack(T... object);
}
