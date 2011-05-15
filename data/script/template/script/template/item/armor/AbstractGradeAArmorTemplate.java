
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
package script.template.item.armor;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Enchantable;
import com.l2jserver.model.template.capability.Penalty;
import com.l2jserver.model.template.item.ArmorTemplate;
import com.l2jserver.model.world.capability.Equiper;

public abstract class AbstractGradeAArmorTemplate extends ArmorTemplate
		implements Enchantable, Penalty {
	public AbstractGradeAArmorTemplate(ItemTemplateID id) {
		super(id);
	}

	@Override
	public void enchant(com.l2jserver.model.world.capability.Enchantable target) {
		target.setEnchantLevel(target.getEnchantLevel() + 1);
	}

	@Override
	public void penalty(Equiper user) {
		// if (!(user instanceof Levelable) && !(user instanceof Castable))
		// return;
		// final Levelable levelable = (Levelable) user;
		// final Castable castable = (Castable) user;
		// if (levelable.getLevel() < 20) {
		//
		// }
	}
}
