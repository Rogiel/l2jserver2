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

package com.l2jserver.service.game.scripting.impl.javacc;

import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is simple compiler error listener that forwards errors to log4j
 * logger
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ErrorListener implements DiagnosticListener<JavaFileObject> {

	/**
	 * Logger for this class
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ErrorListener.class);

	/**
	 * Reports compilation errors to log4j
	 * 
	 * @param diagnostic
	 *            compiler errors
	 */
	@Override
	public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Compier Error Report Start").append("\n");
		buffer.append("errcode:").append(diagnostic.getCode()).append("\n");
		buffer.append("line   :").append(diagnostic.getLineNumber())
				.append("\n");
		buffer.append("column :").append(diagnostic.getColumnNumber())
				.append("\n");
		buffer.append("message:")
				.append(diagnostic.getMessage(Locale.getDefault()))
				.append("\n");
		buffer.append("Compier Error Report End");
		log.error(buffer.toString());
	}
}
