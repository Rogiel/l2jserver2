package com.l2jserver.model.template;

import com.l2jserver.model.id.TemplateID;

public interface Template<T> {
	T create();

	TemplateID<?> getID();
}
