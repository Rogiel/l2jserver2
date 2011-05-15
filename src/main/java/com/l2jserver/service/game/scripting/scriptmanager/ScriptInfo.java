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
package com.l2jserver.service.game.scripting.scriptmanager;

import java.io.File;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.l2jserver.service.game.scripting.impl.javacc.ScriptCompilerImpl;

/**
 * Simple class that represents script info.<br>
 * <br>
 * It contains Script root, list of libraries and list of child contexes
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "scriptinfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ScriptInfo {
	/**
	 * Root of this script context. Child directories of root will be scanned
	 * for script files
	 */
	@XmlAttribute(required = true)
	private File root;

	/**
	 * List of libraries of this script context
	 */
	@XmlElement(name = "library")
	private List<File> libraries;

	/**
	 * List of child contexts
	 */
	@XmlElement(name = "scriptinfo")
	private List<ScriptInfo> scriptInfos;

	/**
	 * Default compiler class name.
	 */
	@XmlElement(name = "compiler")
	private String compilerClass = ScriptCompilerImpl.class.getName();

	/**
	 * Returns root of script context
	 * 
	 * @return root of script context
	 */
	public File getRoot() {
		return root;
	}

	/**
	 * Sets root for script context
	 * 
	 * @param root
	 *            root for script context
	 */
	public void setRoot(File root) {
		this.root = root;
	}

	/**
	 * Returns list of libraries that will be used byscript context and it's
	 * children
	 * 
	 * @return lib of libraries
	 */
	public List<File> getLibraries() {
		return libraries;
	}

	/**
	 * Sets list of libraries that will be used by script context and it's
	 * children
	 * 
	 * @param libraries
	 *            sets list of libraries
	 */
	public void setLibraries(List<File> libraries) {
		this.libraries = libraries;
	}

	/**
	 * Return list of child context descriptors
	 * 
	 * @return list of child context descriptors
	 */
	public List<ScriptInfo> getScriptInfos() {
		return scriptInfos;
	}

	/**
	 * Sets list of child context descriptors
	 * 
	 * @param scriptInfos
	 *            list of child context descriptors
	 */
	public void setScriptInfos(List<ScriptInfo> scriptInfos) {
		this.scriptInfos = scriptInfos;
	}

	/**
	 * Returns compiler class name
	 * 
	 * @return name of compiler class
	 */
	public String getCompilerClass() {
		return compilerClass;
	}

	/**
	 * Sets compiler class name
	 * 
	 * @param compilerClass
	 *            name of compiler class
	 */
	public void setCompilerClass(String compilerClass) {
		this.compilerClass = compilerClass;
	}

	/**
	 * Returns true if roots are quals
	 * 
	 * @param o
	 *            object to compare with
	 * @return true if this ScriptInfo and anothers ScriptInfo has same root
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ScriptInfo that = (ScriptInfo) o;

		return root.equals(that.root);

	}

	/**
	 * Returns hashcode of root
	 * 
	 * @return hashcode of root
	 */
	@Override
	public int hashCode() {
		return root.hashCode();
	}
}
