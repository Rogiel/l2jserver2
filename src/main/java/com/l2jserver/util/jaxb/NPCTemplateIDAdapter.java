/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.util.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.provider.NPCTemplateIDProvider;
import com.l2jserver.model.world.NPC;

/**
 * This class is an JAXB Adapter for {@link NPCTemplateIDProvider}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class NPCTemplateIDAdapter extends XmlAdapter<Integer, NPCTemplateID> {
	/**
	 * The {@link NPC} template id provider
	 */
	private final NPCTemplateIDProvider provider;

	/**
	 * Creates a new empty instance
	 */
	public NPCTemplateIDAdapter() {
		provider = null;
	}

	/**
	 * @param provider
	 *            the {@link NPC} template id provider
	 */
	@Inject
	public NPCTemplateIDAdapter(NPCTemplateIDProvider provider) {
		this.provider = provider;
	}

	@Override
	public NPCTemplateID unmarshal(Integer v) throws Exception {
		if (v == 0)
			return null;
		if (provider == null)
			return new NPCTemplateID(v, null);
		return provider.resolveID(v);
	}

	@Override
	public Integer marshal(NPCTemplateID v) throws Exception {
		if (v == null)
			return 0;
		return v.getID();
	}
}
