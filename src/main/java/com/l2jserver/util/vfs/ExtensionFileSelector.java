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
package com.l2jserver.util.vfs;

import java.util.Arrays;

import org.apache.commons.vfs.FileSelectInfo;
import org.apache.commons.vfs.FileSelector;
import org.apache.commons.vfs.FileType;

/**
 * This selector will select all <tt>FILES</tt> that has an given extension.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ExtensionFileSelector implements FileSelector {
	private final String[] extensions;
	private final boolean descendents;

	public ExtensionFileSelector(String... extensions) {
		this(true, extensions);
	}

	public ExtensionFileSelector(boolean descendents, String... extensions) {
		this.descendents = descendents;
		this.extensions = extensions;
		Arrays.sort(extensions);
	}

	@Override
	public boolean includeFile(FileSelectInfo file) throws Exception {
		if (file.getFile().getType() != FileType.FILE)
			return false;
		return (Arrays.binarySearch(extensions, file.getFile().getName()
				.getExtension()) >= 0);
	}

	@Override
	public boolean traverseDescendents(FileSelectInfo file) throws Exception {
		return descendents;
	}

	public static final ExtensionFileSelector ext(String... extensions) {
		return new ExtensionFileSelector(extensions);
	}
}
