package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;

public class AbstractTemplate {
	private final TemplateID id;

	public AbstractTemplate(TemplateID id) {
		super();
		this.id = id;
	}

	public TemplateID getId() {
		return id;
	}
}
