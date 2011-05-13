package com.l2jserver.util.transformer;

import java.io.File;
import java.net.InetSocketAddress;

import com.l2jserver.util.transformer.impl.BooleanTransformer;
import com.l2jserver.util.transformer.impl.ByteTransformer;
import com.l2jserver.util.transformer.impl.ClassTransformer;
import com.l2jserver.util.transformer.impl.DoubleTransformer;
import com.l2jserver.util.transformer.impl.FileTransformer;
import com.l2jserver.util.transformer.impl.FloatTransformer;
import com.l2jserver.util.transformer.impl.InetSocketAddressTransformer;
import com.l2jserver.util.transformer.impl.IntegerTransformer;
import com.l2jserver.util.transformer.impl.LongTransformer;
import com.l2jserver.util.transformer.impl.ShortTransformer;

public class TransformerFactory {
	public static final Transformer<?> getTransfromer(Class<?> type) {
		if (type == Byte.class || type == Byte.TYPE) {
			return ByteTransformer.SHARED_INSTANCE;
		} else if (type == Short.class || type == Short.TYPE) {
			return ShortTransformer.SHARED_INSTANCE;
		} else if (type == Integer.class || type == Integer.TYPE) {
			return IntegerTransformer.SHARED_INSTANCE;
		} else if (type == Long.class || type == Long.TYPE) {
			return LongTransformer.SHARED_INSTANCE;
		} else if (type == Float.class || type == Float.TYPE) {
			return FloatTransformer.SHARED_INSTANCE;
		} else if (type == Double.class || type == Double.TYPE) {
			return DoubleTransformer.SHARED_INSTANCE;
		} else if (type == Boolean.class || type == Boolean.TYPE) {
			return BooleanTransformer.SHARED_INSTANCE;
		} else if(type == InetSocketAddress.class) {
			return InetSocketAddressTransformer.SHARED_INSTANCE;
		} else if(type == File.class) {
			return FileTransformer.SHARED_INSTANCE;
		}else if(type == Class.class) {
			return ClassTransformer.SHARED_INSTANCE;
		}
		return null;
	}
}
