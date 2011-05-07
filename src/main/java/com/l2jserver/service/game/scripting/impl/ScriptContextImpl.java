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

package com.l2jserver.service.game.scripting.impl;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.l2jserver.service.game.scripting.CompilationResult;
import com.l2jserver.service.game.scripting.ScriptClassLoader;
import com.l2jserver.service.game.scripting.ScriptCompiler;
import com.l2jserver.service.game.scripting.ScriptContext;
import com.l2jserver.service.game.scripting.classlistener.ClassListener;
import com.l2jserver.service.game.scripting.classlistener.DefaultClassListener;

/**
 * This class is actual implementation of {@link ScriptContext}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ScriptContextImpl implements ScriptContext {
	/**
	 * logger for this class
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ScriptContextImpl.class);

	private final Injector injector;

	/**
	 * Script context that is parent for this script context
	 */
	private final ScriptContext parentScriptContext;

	/**
	 * Libraries (list of jar files) that have to be loaded class loader
	 */
	private Iterable<File> libraries;

	/**
	 * Root directory of this script context. It and it's subdirectories will be
	 * scanned for .java files.
	 */
	private final File root;

	/**
	 * Result of compilation of script context
	 */
	private CompilationResult compilationResult;

	/**
	 * List of child script contexts
	 */
	private Set<ScriptContext> childScriptContexts;

	/**
	 * Classlistener for this script context
	 */
	private ClassListener classListener;

	/**
	 * Class name of the compiler that will be used to compile sources
	 */
	private String compilerClassName;

	/**
	 * Creates new scriptcontext with given root file
	 * 
	 * @param root
	 *            file that represents root directory of this script context
	 * @throws NullPointerException
	 *             if root is null
	 * @throws IllegalArgumentException
	 *             if root directory doesn't exists or is not a directory
	 */
	public ScriptContextImpl(Injector injector, File root) {
		this(injector, root, null);
	}

	/**
	 * Creates new ScriptContext with given file as root and another
	 * ScriptContext as parent
	 * 
	 * @param root
	 *            file that represents root directory of this script context
	 * @param parent
	 *            parent ScriptContex. It's classes and libraries will be
	 *            accessible for this script context
	 * @throws NullPointerException
	 *             if root is null
	 * @throws IllegalArgumentException
	 *             if root directory doesn't exists or is not a directory
	 */
	public ScriptContextImpl(Injector injector, File root, ScriptContext parent) {
		if (root == null)
			throw new NullPointerException("Root file must be specified");
		if (!root.exists() || !root.isDirectory())
			throw new IllegalArgumentException(
					"Root directory not exists or is not a directory");

		this.injector = injector;
		this.root = root;
		this.parentScriptContext = parent;
	}

	@Override
	public synchronized void init() {

		if (compilationResult != null) {
			log.error("", new Exception(
					"Init request on initialized ScriptContext"));
			return;
		}

		ScriptCompiler scriptCompiler = instantiateCompiler();

		@SuppressWarnings("unchecked")
		Collection<File> files = FileUtils.listFiles(root,
				scriptCompiler.getSupportedFileTypes(), true);

		if (parentScriptContext != null) {
			scriptCompiler.setParentClassLoader(parentScriptContext
					.getCompilationResult().getClassLoader());
		}

		scriptCompiler.setLibraires(libraries);
		compilationResult = scriptCompiler.compile(files);

		getClassListener().postLoad(compilationResult.getCompiledClasses());

		if (childScriptContexts != null) {
			for (ScriptContext context : childScriptContexts) {
				context.init();
			}
		}
	}

	@Override
	public synchronized void shutdown() {

		if (compilationResult == null) {
			log.error("Shutdown of not initialized stript context",
					new Exception());
			return;
		}

		if (childScriptContexts != null) {
			for (ScriptContext child : childScriptContexts) {
				child.shutdown();
			}
		}

		getClassListener().preUnload(compilationResult.getCompiledClasses());
		compilationResult = null;
	}

	@Override
	public void reload() {
		shutdown();
		init();
	}

	@Override
	public File getRoot() {
		return root;
	}

	@Override
	public CompilationResult getCompilationResult() {
		return compilationResult;
	}

	@Override
	public synchronized boolean isInitialized() {
		return compilationResult != null;
	}

	@Override
	public void setLibraries(Iterable<File> files) {
		this.libraries = files;
	}

	@Override
	public Iterable<File> getLibraries() {
		return libraries;
	}

	@Override
	public ScriptContext getParentScriptContext() {
		return parentScriptContext;
	}

	@Override
	public synchronized Collection<ScriptContext> getChildScriptContexts() {
		return childScriptContexts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void addChildScriptContext(ScriptContext context) {
		synchronized (this) {
			if (childScriptContexts == null) {
				childScriptContexts = new HashSet<ScriptContext>();
			}

			if (childScriptContexts.contains(context)) {
				log.error("Double child definition, root: "
						+ root.getAbsolutePath() + ", child: "
						+ context.getRoot().getAbsolutePath());
				return;
			}

			if (isInitialized()) {
				context.init();
			}
		}
		childScriptContexts.add(context);
	}

	@Override
	public void setClassListener(ClassListener cl) {
		classListener = cl;
	}

	@Override
	public ClassListener getClassListener() {
		if (classListener == null) {
			if (getParentScriptContext() == null) {
				setClassListener(new DefaultClassListener(injector));
				return classListener;
			} else {
				return getParentScriptContext().getClassListener();
			}
		} else {
			return classListener;
		}
	}

	@Override
	public void setCompilerClassName(String className) {
		this.compilerClassName = className;
	}

	@Override
	public String getCompilerClassName() {
		return this.compilerClassName;
	}

	@Override
	public ScriptClassLoader getClassLoader() {
		return compilationResult.getClassLoader();
	}

	/**
	 * Creates new instance of ScriptCompiler that should be used with this
	 * ScriptContext
	 * 
	 * @return instance of ScriptCompiler
	 * @throws RuntimeException
	 *             if failed to create instance
	 */
	protected ScriptCompiler instantiateCompiler() throws RuntimeException {
		ClassLoader cl = getClass().getClassLoader();
		if (getParentScriptContext() != null) {
			cl = getParentScriptContext().getCompilationResult()
					.getClassLoader();
		}

		ScriptCompiler sc;
		try {
			sc = (ScriptCompiler) Class.forName(getCompilerClassName(), true,
					cl).newInstance();
		} catch (Exception e) {
			RuntimeException e1 = new RuntimeException(
					"Can't create instance of compiler", e);
			log.error("Compiler exception", e1);
			throw e1;
		}

		return sc;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ScriptContextImpl)) {
			return false;
		}

		ScriptContextImpl another = (ScriptContextImpl) obj;

		if (parentScriptContext == null) {
			return another.getRoot().equals(root);
		} else {
			return another.getRoot().equals(root)
					&& parentScriptContext.equals(another.parentScriptContext);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int result = parentScriptContext != null ? parentScriptContext
				.hashCode() : 0;
		result = 31 * result + root.hashCode();
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void finalize() throws Throwable {
		if (compilationResult != null) {
			log.error("Finalization of initialized ScriptContext. Forcing context shutdown.");
			shutdown();
		}
		super.finalize();
	}
}