/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.util.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.google.inject.Inject;
import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.id.template.provider.SkillTemplateIDProvider;

/**
 * This class is an JAXB Adapter for {@link SkillTemplateIDProvider}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SkillTemplateIDAdapter extends
		XmlAdapter<Integer, SkillTemplateID> {
	/**
	 * The {@link Skill} template id provider
	 */
	private final SkillTemplateIDProvider provider;

	/**
	 * Creates a new empty instance
	 */
	public SkillTemplateIDAdapter() {
		provider = null;
	}

	/**
	 * @param provider
	 *            the {@link Skill} template id provider
	 */
	@Inject
	public SkillTemplateIDAdapter(SkillTemplateIDProvider provider) {
		this.provider = provider;
	}

	@Override
	public SkillTemplateID unmarshal(Integer v) throws Exception {
		if (v == 0)
			return null;
		if (provider == null)
			return new SkillTemplateID(v, null);
		return provider.resolveID(v);
	}

	@Override
	public Integer marshal(SkillTemplateID v) throws Exception {
		if (v == null)
			return 0;
		return v.getID();
	}
}
