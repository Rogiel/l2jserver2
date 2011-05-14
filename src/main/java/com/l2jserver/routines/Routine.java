package com.l2jserver.routines;

import java.util.concurrent.Callable;

/**
 * An routine is a set of operations that can be performed on another thread.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the routine return type
 */
public interface Routine<T> extends Callable<T> {
}
