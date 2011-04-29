package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.capability.Damagable;

/**
 * Indicates that an template has the ability to intercept incoming damage.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public interface IncomingDamageIntercept extends TemplateCapability {
	void interceptIncomingDamage(Damagable target);
}
