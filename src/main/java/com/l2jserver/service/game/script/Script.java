package com.l2jserver.service.game.script;

import com.l2jserver.model.world.capability.Scriptable;

public interface Script<O extends Scriptable> extends Runnable {
	/**
	 * Load this script for <tt>object</tt>
	 * 
	 * @param object
	 */
	void load(O object);

	void unload();
	
	O getObject();
}
