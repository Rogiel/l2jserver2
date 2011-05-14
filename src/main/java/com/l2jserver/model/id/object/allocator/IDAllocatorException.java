package com.l2jserver.model.id.object.allocator;

/**
 * Exception thrown by an {@link IDAllocator}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class IDAllocatorException extends RuntimeException {
	private static final long serialVersionUID = 111195059766878062L;

	public IDAllocatorException() {
		super();
	}

	public IDAllocatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public IDAllocatorException(String message) {
		super(message);
	}

	public IDAllocatorException(Throwable cause) {
		super(cause);
	}
}
