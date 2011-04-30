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

import com.l2jserver.service.game.scripting.impl.ScriptContextImpl;

/**
 * This class is script context provider. We can switch to any other
 * ScriptContext implementation later, so it's good to have factory class
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public final class ScriptContextFactory {
	public static ScriptContext getScriptContext(File root, ScriptContext parent)
			throws InstantiationException {
		ScriptContextImpl ctx;
		if (parent == null) {
			ctx = new ScriptContextImpl(root);
		} else {
			ctx = new ScriptContextImpl(root, parent);
			parent.addChildScriptContext(ctx);
		}
		return ctx;
	}
}
