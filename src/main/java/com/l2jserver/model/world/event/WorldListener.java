package com.l2jserver.model.world.event;

public interface WorldListener<E extends WorldEvent> {
	void onAction(E e);
}
