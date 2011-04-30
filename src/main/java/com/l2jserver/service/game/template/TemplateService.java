package com.l2jserver.service.game.template;

import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.Template;
import com.l2jserver.service.Service;

public interface TemplateService extends Service {
	/**
	 * Get the template assigned with <tt>id</tt>
	 * 
	 * @param id
	 *            the template id
	 * @return the template matching the id
	 */
	Template getTemplate(TemplateID id);

	/**
	 * Reload the template list.
	 */
	void reload();
}
