package com.l2jserver.model.world.filter.impl;

import com.l2jserver.model.id.ID;
import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.model.world.filter.WorldObjectFilter;

public class IDFilter implements WorldObjectFilter<Positionable> {
	private final ID id;

	public IDFilter(final ID id) {
		this.id = id;
	}

	@Override
	public boolean accept(Positionable other) {
		if (other == null)
			return false;
		return other.getID().equals(id);
	}
}
