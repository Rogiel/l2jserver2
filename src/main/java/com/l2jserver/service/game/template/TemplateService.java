package com.l2jserver.service.game.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.AbstractTemplate;
import com.l2jserver.service.Service;

public interface TemplateService extends Service {
	/**
	 * Get the template assigned with <tt>id</tt>
	 * 
	 * @param id
	 *            the template id
	 * @return the template matching the id
	 */
	AbstractTemplate getTemplate(TemplateID id);

	/**
	 * Recompile the template with id <tt>id</tt>. This can be used to reload
	 * the template.
	 * 
	 * @param id
	 *            the template id
	 */
	void recompile(TemplateID id);
}
