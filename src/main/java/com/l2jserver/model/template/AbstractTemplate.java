package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;

public abstract class AbstractTemplate<T> implements Template<T> {
	private final TemplateID<?> id;

	public AbstractTemplate(TemplateID<?> id) {
		super();
		this.id = id;
	}

	@Override
	public TemplateID<?> getID() {
		return id;
	}
}
