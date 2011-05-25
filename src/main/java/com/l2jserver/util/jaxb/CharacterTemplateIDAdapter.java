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
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.world.character.CharacterClass;

/**
 * TODO this should use an {@link ItemTemplateIDProvider}!
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterTemplateIDAdapter extends
		XmlAdapter<CharacterClass, CharacterTemplateID> {
	private final CharacterTemplateIDProvider provider;

	@Inject
	public CharacterTemplateIDAdapter(CharacterTemplateIDProvider provider) {
		this.provider = provider;
	}

	@Override
	public CharacterTemplateID unmarshal(CharacterClass v) throws Exception {
		if (v == null)
			return null;
		return provider.createID(v.id);
	}

	@Override
	public CharacterClass marshal(CharacterTemplateID v) throws Exception {
		if (v == null)
			return null;
		return CharacterClass.fromID(v.getID());
	}
}