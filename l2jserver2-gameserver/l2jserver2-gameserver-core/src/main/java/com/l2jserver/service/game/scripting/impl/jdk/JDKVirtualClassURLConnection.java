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
package com.l2jserver.service.game.scripting.impl.jdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.l2jserver.service.game.scripting.impl.BinaryClass;

/**
 * This class represents URL Connection that is used to "connect" to scripts
 * binary data that was loaded by specified {@link JDKScriptCompiler}.<br>
 * <br>
 * TODO: Implement all methods of {@link URLConnection} to ensure valid
 * behaviour
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class JDKVirtualClassURLConnection extends URLConnection {

	/**
	 * Input stream, is assigned from class
	 */
	private InputStream is;

	/**
	 * Creates URL connections that "connects" to class binary data
	 * 
	 * @param url
	 *            class name
	 * @param cl
	 *            classloader
	 */
	protected JDKVirtualClassURLConnection(URL url, JDKScriptClassLoader cl) {
		super(url);
		BinaryClass bc = cl.getClassFileManager().getCompiledClasses()
				.get(url.getHost());
		byte[] b = new byte[bc.getBytes().length];
		System.arraycopy(bc.getBytes(), 0, b, 0, b.length);
		is = new ByteArrayInputStream(b);
	}

	/**
	 * This method is ignored
	 */
	@Override
	public void connect() throws IOException {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return is;
	}
}
