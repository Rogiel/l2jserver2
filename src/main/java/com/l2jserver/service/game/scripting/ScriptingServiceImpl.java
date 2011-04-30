package com.l2jserver.service.game.scripting;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.game.scripting.impl.ScriptContextImpl;
import com.l2jserver.service.game.scripting.scriptmanager.ScriptInfo;
import com.l2jserver.service.game.scripting.scriptmanager.ScriptList;
import com.l2jserver.util.factory.CollectionFactory;

public class ScriptingServiceImpl extends AbstractService implements
		ScriptingService {
	/**
	 * Logger for script context
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ScriptingServiceImpl.class);

	private final Injector injector;

	/**
	 * Collection of script contexts
	 */
	private final Set<ScriptContext> contexts = new HashSet<ScriptContext>();

	@Inject
	public ScriptingServiceImpl(Injector injector) {
		this.injector = injector;
	}

	@Override
	public void start() throws ServiceStartException {
		for (ScriptContext context : contexts) {
			context.shutdown();
		}
	}

	/**
	 * Loads script contexes from descriptor
	 * 
	 * @param scriptDescriptor
	 *            xml file that describes contexes
	 * @throws Exception
	 *             if can't load file
	 */
	@Override
	public synchronized List<ScriptContext> load(File scriptDescriptor)
			throws Exception {
		final JAXBContext c = JAXBContext.newInstance(ScriptInfo.class,
				ScriptList.class);
		final Unmarshaller u = c.createUnmarshaller();
		final ScriptList list = (ScriptList) u.unmarshal(scriptDescriptor);

		final List<ScriptContext> contexts = CollectionFactory
				.newList(ScriptContext.class);

		for (ScriptInfo si : list.getScriptInfos()) {
			final ScriptContext context = createContext(si, null);
			if (context != null) {
				this.contexts.add(context);
				context.init();
				contexts.add(context);
			}
		}
		return contexts;
	}

	/**
	 * Creates new context and checks to not produce copies
	 * 
	 * @param si
	 *            script context descriptor
	 * @param parent
	 *            parent script context
	 * @return created script context
	 * @throws Exception
	 *             if can't create context
	 */
	private ScriptContext createContext(ScriptInfo si, ScriptContext parent)
			throws Exception {
		ScriptContext context = getScriptContext(si.getRoot(), parent);
		context.setLibraries(si.getLibraries());
		context.setCompilerClassName(si.getCompilerClass());

		if (parent == null && contexts.contains(context)) {
			log.warn("Double root script context definition: "
					+ si.getRoot().getAbsolutePath());
			return null;
		}

		if (si.getScriptInfos() != null && !si.getScriptInfos().isEmpty()) {
			for (ScriptInfo child : si.getScriptInfos()) {
				createContext(child, context);
			}
		}

		return context;
	}

	@Override
	public synchronized Collection<ScriptContext> getScriptContexts() {
		return Collections.unmodifiableSet(contexts);
	}

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
	private ScriptContext getScriptContext(File root, ScriptContext parent)
			throws InstantiationException {
		ScriptContextImpl ctx;
		if (parent == null) {
			ctx = new ScriptContextImpl(injector, root);
		} else {
			ctx = new ScriptContextImpl(injector, root, parent);
			parent.addChildScriptContext(ctx);
		}
		return ctx;
	}

	@Override
	public void reload() {
		for (ScriptContext context : contexts) {
			context.reload();
		}
	}

	@Override
	public void stop() throws ServiceStopException {
		for (ScriptContext context : contexts) {
			context.shutdown();
		}
		contexts.clear();
	}
}
