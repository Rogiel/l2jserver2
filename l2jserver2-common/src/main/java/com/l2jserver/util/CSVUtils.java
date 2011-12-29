/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.l2jserver.util.factory.CollectionFactory;

/**
 * This class provices CSV parsing and other utilities
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CSVUtils {
	/**
	 * Parses an CSV files and sends every row to be processed by the
	 * <code>processor</code>.
	 * <p>
	 * <h1>The parsing process</h1>
	 * <p>
	 * The parser reads the first line of the CSV and interprets it as an header
	 * that will be subsequently be passed to the {@link CSVProcessor} as the
	 * <code>header</code> {@link String String[]}.
	 * <p>
	 * Following rows are data rows and are read and passed to the
	 * {@link CSVProcessor} one by one until the end of file.
	 * 
	 * @param <R>
	 *            the processor return type
	 * 
	 * @param reader
	 *            the CSV {@link BufferedReader}
	 * @param processor
	 *            the processor
	 * @return all processed rows
	 * @throws IOException
	 *             if any exception occur while parsing
	 */
	public static <R> List<R> parseCSV(BufferedReader reader,
			CSVProcessor<R> processor) throws IOException {
		final List<R> results = CollectionFactory.newList();
		String line = reader.readLine();
		if (line == null)
			return results;
		final String header[] = line.split(",");
		while ((line = reader.readLine()) != null) {
			results.add(processor.process(header, line.split(",")));
		}
		return results;
	}

	/**
	 * Parses an CSV files and sends every row to be processed by the
	 * <code>processor</code>
	 * 
	 * @param <R>
	 *            the processor return type
	 * 
	 * @param path
	 *            the CSV file path
	 * @param processor
	 *            the processor
	 * @return all processed rows
	 * @throws IOException
	 *             if any exception occur while parsing
	 * @see #parseCSV(BufferedReader, CSVProcessor)
	 *      <code>parseCSV(BufferedReader, CSVProcessor)</code> for more details
	 *      about how the parsing works
	 */
	public static <R> List<R> parseCSV(Path path, CSVProcessor<R> processor)
			throws IOException {
		return parseCSV(
				Files.newBufferedReader(path, Charset.defaultCharset()),
				processor);
	}

	/**
	 * Parses an CSV files and sends every row to be processed by the
	 * <code>processor</code>
	 * 
	 * @param <R>
	 *            the processor return type
	 * 
	 * @param file
	 *            the CSV file
	 * @param processor
	 *            the processor
	 * @return all processed rows
	 * @throws IOException
	 *             if any exception occur while parsing
	 */
	public static <R> List<R> parseCSV(File file, CSVProcessor<R> processor)
			throws IOException {
		return parseCSV(file.toPath(), processor);
	}

	/**
	 * Parses an CSV files and sends every row to be processed by the
	 * <code>processor</code>
	 * 
	 * @param <R>
	 *            the processor return type
	 * 
	 * @param in
	 *            the CSV {@link InputStream}
	 * @param processor
	 *            the processor
	 * @return all processed rows
	 * @throws IOException
	 *             if any exception occur while parsing
	 */
	public static <R> List<R> parseCSV(InputStream in, CSVProcessor<R> processor)
			throws IOException {
		return parseCSV(new BufferedReader(new InputStreamReader(in)),
				processor);
	}

	/**
	 * Parses an CSV files and sends every row to be processed by the
	 * <code>processor</code>
	 * 
	 * @param <R>
	 *            the processor return type
	 * 
	 * @param reader
	 *            the CSV {@link Reader}
	 * @param processor
	 *            the processor
	 * @return all processed rows
	 * @throws IOException
	 *             if any exception occur while parsing
	 */
	public static <R> List<R> parseCSV(Reader reader, CSVProcessor<R> processor)
			throws IOException {
		return parseCSV(new BufferedReader(reader), processor);
	}

	/**
	 * Processes an single row in an CSV files and returns an processed object.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * @param <R>
	 *            the processed object type
	 */
	public interface CSVProcessor<R> {
		/**
		 * Processes the current row and returns the processed object
		 * 
		 * @param header
		 *            the CSV header (first row)
		 * @param data
		 *            the CSV data
		 * @return the processed object
		 */
		R process(String[] header, String[] data);
	}

	/**
	 * This {@link CSVProcessor} implementations act as an adapter to the String
	 * arrays, providing access to the rows in a more object oriented fashing,
	 * through a {@link Map}.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 * 
	 * @param <R>
	 *            the processed object type
	 */
	public static abstract class CSVMapProcessor<R> implements CSVProcessor<R> {
		@Override
		public final R process(String[] header, String[] data) {
			final Map<String, String> map = CollectionFactory.newMap();
			for (int i = 0; i < header.length; i++) {
				map.put(header[i], data[i]);
			}
			return process(map);
		}

		/**
		 * @param map
		 *            the CSV row mapped into an {@link Map}
		 * @return the row processed object
		 */
		public abstract R process(Map<String, String> map);
	}
}
