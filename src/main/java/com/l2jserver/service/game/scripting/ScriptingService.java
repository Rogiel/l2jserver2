package com.l2jserver.service.game.scripting;

import java.io.File;

import com.l2jserver.service.Service;

public interface ScriptingService extends Service {
	/**
	 * Creates script context, sets the root context. Adds child context if
	 * needed
	 * 
	 * @param root
	 *            file that will be threated as root for compiler
	 * @param parent
	 *            parent of new ScriptContext
	 * @return ScriptContext with presetted root file
	 * @throws InstantiationException
	 *             if java compiler is not aviable
	 */
	ScriptContext getScriptContext(File root, ScriptContext parent)
			throws InstantiationException;
}
