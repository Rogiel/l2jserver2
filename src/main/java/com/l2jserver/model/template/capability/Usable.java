package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.Template;

/**
 * Defines an {@link Template template} {@link TemplateCapability capability}
 * that can be used.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Usable extends TemplateCapability {
	void canUse();
}
