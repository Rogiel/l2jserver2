package com.l2jserver.util.transformer.impl;

import java.io.File;

import com.l2jserver.util.transformer.Transformer;

/**
 * Transform an {@link Integer} into an string.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class FileTransformer implements Transformer<File> {
	public static final FileTransformer SHARED_INSTANCE = new FileTransformer();

	private final File root = new File("./");

	@Override
	public String transform(File value) {
		return value.getAbsolutePath();
	}

	@Override
	public File untransform(String value) {
		return new File(root, value);
	}

}
