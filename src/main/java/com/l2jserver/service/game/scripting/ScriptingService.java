package com.l2jserver.service.game.scripting;

import java.io.File;
import java.util.Collection;
import java.util.List;

import com.l2jserver.service.Service;

/**
 * This service is capable of loading raw .java files, compile them and add to
 * runtime.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ScriptingService extends Service {
	/**
	 * Loads script contexes from descriptor
	 * 
	 * @param scriptDescriptor
	 *            xml file that describes contexes
	 * @throws Exception
	 *             if can't load file
	 */
	List<ScriptContext> load(File scriptDescriptor) throws Exception;

	/**
	 * Returns unmodifiable set with script contexes
	 * 
	 * @return unmodifiable set of script contexes
	 */
	Collection<ScriptContext> getScriptContexts();

	/**
	 * Reloads all contexes
	 */
	void reload();
}
