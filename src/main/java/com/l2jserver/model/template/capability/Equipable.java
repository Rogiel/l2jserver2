package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;
import com.l2jserver.model.world.capability.Equiper;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * for an object that acn be equipable by an {@link Equiper}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface Equipable extends TemplateCapability {
	void equip(Equiper equiper);
}
