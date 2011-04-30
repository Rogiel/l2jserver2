package com.l2jserver.service.game.scripting;

import java.io.File;

import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.game.scripting.impl.ScriptContextImpl;

public class ScriptingServiceImpl implements ScriptingService {
	@Override
	public void start() throws ServiceStartException {
		
	}

	@Override
	public ScriptContext getScriptContext(File root, ScriptContext parent)
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

	@Override
	public void stop() throws ServiceStopException {
		
	}
}
