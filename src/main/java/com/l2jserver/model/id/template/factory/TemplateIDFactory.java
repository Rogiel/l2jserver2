package com.l2jserver.model.id.template.factory;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.id.factory.IDFactory;

/**
 * Creates a new {@link TemplateID}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 * @param <T>
 *            the subclass of {@link TemplateID} that will be createdF
 */
public interface TemplateIDFactory<T extends TemplateID<?>> extends
		IDFactory<T> {
}
