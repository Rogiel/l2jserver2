package com.l2jserver.model.template.capability;

import com.l2jserver.model.world.capability.Damagable;

/**
 * Indicates that an template has the ability to intercept outgoing damage.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface OutgoingDamageIntercept extends TemplateCapability {
	void interceptOutgoingDamage(Damagable target);
}
