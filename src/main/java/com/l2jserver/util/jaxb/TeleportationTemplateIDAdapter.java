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
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.id.template.provider.TeleportationTemplateIDProvider;

/**
 * TODO this should use an {@link ItemTemplateIDProvider}!
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class TeleportationTemplateIDAdapter extends
		XmlAdapter<String, TeleportationTemplateID> {
	private final TeleportationTemplateIDProvider provider;

	@Inject
	public TeleportationTemplateIDAdapter(
			TeleportationTemplateIDProvider provider) {
		this.provider = provider;
	}

	@Override
	public TeleportationTemplateID unmarshal(String v) throws Exception {
		if (v == null)
			return null;
		return provider.createID(v);
	}

	@Override
	public String marshal(TeleportationTemplateID v) throws Exception {
		if (v == null)
			return null;
		return v.getID();
	}
}
