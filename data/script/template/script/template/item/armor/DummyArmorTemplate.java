
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

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Damagable;

public class DummyArmorTemplate extends AbstractNoGradeArmorTemplate {
	public static final int ID = 20;

	private static final int REDUCED_DAMAGE_PHYSICAL = 10;
	private static final int REDUCED_DAMAGE_MAGICAL = 10;

	@Inject
	public DummyArmorTemplate(ItemTemplateIDFactory factory) {
		super(factory.createID(ID));
	}

	@Override
	public void defend(Attacker source, Attackable target) {
	}

	@Override
	public void interceptIncomingDamage(Damagable target) {
	}

	@Override
	public int getPhysicalDefense() {
		return REDUCED_DAMAGE_PHYSICAL;
	}

	@Override
	public int getMagicalDefense() {
		return REDUCED_DAMAGE_MAGICAL;
	}
}
