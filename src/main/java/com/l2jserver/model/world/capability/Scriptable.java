package com.l2jserver.model.world.capability;

import com.l2jserver.model.world.AbstractObject;
import com.l2jserver.service.game.script.Script;

/**
 * Defines an {@link AbstractObject} that can be controller by an {@link Script}
 * implementation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Scriptable extends WorldCapability {
	/**
	 * The the current script attached to this object
	 * 
	 * @return the attached script
	 */
	Script<Scriptable> getScript();

	/**
	 * Set the attached script to this object
	 * 
	 * @param script
	 *            the script
	 */
	void setScript(Script<Scriptable> script);
}
