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
import com.l2jserver.model.id.template.TeleportationTemplateID;
import com.l2jserver.model.id.template.provider.TeleportationTemplateIDProvider;

/**
 * This class is an JAXB Adapter for {@link TeleportationTemplateIDProvider}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TeleportationTemplateIDAdapter extends
		XmlAdapter<Integer, TeleportationTemplateID> {
	/**
	 * The teleportation template id provider
	 */
	private final TeleportationTemplateIDProvider provider;

	/**
	 * Creates a new empty instance
	 */
	public TeleportationTemplateIDAdapter() {
		provider = null;
	}

	/**
	 * @param provider
	 *            the teleportation template id provider
	 */
	@Inject
	public TeleportationTemplateIDAdapter(
			TeleportationTemplateIDProvider provider) {
		this.provider = provider;
	}

	@Override
	public TeleportationTemplateID unmarshal(Integer v) throws Exception {
		if (v == null)
			return null;
		if (provider == null)
			return new TeleportationTemplateID(v, null);
		return provider.resolveID(v);
	}

	@Override
	public Integer marshal(TeleportationTemplateID v) throws Exception {
		if (v == null)
			return null;
		return v.getID();
	}
}
