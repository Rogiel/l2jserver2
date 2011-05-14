package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * for an object that can be enchanted.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Enchantable extends TemplateCapability {
	void enchant(com.l2jserver.model.world.capability.Enchantable target);
}
