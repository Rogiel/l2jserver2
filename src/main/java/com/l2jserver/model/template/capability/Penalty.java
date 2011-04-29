package com.l2jserver.model.template.capability;

import com.l2jserver.model.template.AbstractTemplate;
import com.l2jserver.model.world.capability.Equiper;

/**
 * Indicated than an {@link AbstractTemplate} can add penalties to an given
 * user.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Penalty extends TemplateCapability {
	void penalty(Equiper user);
}
