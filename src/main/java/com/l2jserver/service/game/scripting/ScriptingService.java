/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.scripting;

import java.io.File;
import java.util.Collection;
import java.util.List;

import com.l2jserver.game.ai.AI;
import com.l2jserver.service.Service;

/**
 * The scripting service provides script capabilities to the server. Scripts can
 * be used in events (in-game), Quests and {@link AI}. Implementations are free
 * to use any language to implement scripts.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ScriptingService extends Service {
	/**
	 * Loads script contexts from descriptor
	 * 
	 * @param scriptDescriptor
	 *            xml file that describes contexes
	 * @return the {@link List} of {@link ScriptContext} loaded
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
