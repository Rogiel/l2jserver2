package com.l2jserver.model.world;

import java.util.List;

import com.l2jserver.model.world.capability.Child;
import com.l2jserver.model.world.capability.Listenable;
import com.l2jserver.model.world.capability.Playable;
import com.l2jserver.model.world.capability.Spawnable;
import com.l2jserver.model.world.item.ItemEvent;
import com.l2jserver.model.world.item.ItemListener;
import com.l2jserver.util.Coordinate;
import com.l2jserver.util.factory.CollectionFactory;

public class Item extends AbstractObject implements Playable, Spawnable,
		Child<Player>, Listenable<ItemListener, ItemEvent> {
	private final List<ItemListener> listeners = CollectionFactory
			.newList(ItemListener.class);

	@Override
	public void spawn(Coordinate coordinate) {

	}

	@Override
	public void addListener(ItemListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(ItemListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void dispatch(ItemEvent e) {
		for (final ItemListener listener : listeners) {
			listener.dispatch(e);
		}
	}

	@Override
	public boolean isSpawned() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player getParent() {
		return null;
	}

	@Override
	public Coordinate getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Coordinate coord) {
		
	}
}
