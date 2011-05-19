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
package script.template.item.weapon;

import static com.l2jserver.model.template.item.WeaponTemplate.WeaponAttributeType.MAGICAL_ATTACK;
import static com.l2jserver.model.template.item.WeaponTemplate.WeaponAttributeType.PHYSICAL_ATTACK;
import static com.l2jserver.model.template.item.WeaponTemplate.WeaponAttributeType.PHYSICAL_ATTACK_SPEED;
import static com.l2jserver.model.template.item.WeaponTemplate.WeaponAttributeType.R_CRITICAL;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;

public class LongSwordTemplate extends AbstractNoGradeWeaponTemplate {
	public static final int ID = 2;

	@Inject
	public LongSwordTemplate(ItemTemplateIDProvider factory) {
		super(factory.createID(ID), "icon.weapon_long_sword_i00",
				ItemMaterial.STEEL, InventoryPaperdoll.RIGHT_HAND,
				WeaponType.SWORD);

		attribute.set(PHYSICAL_ATTACK, 0x80, 24);
		attribute.set(MAGICAL_ATTACK, 0x80, 17);
		attribute.set(R_CRITICAL, 0x80, 8);
		attribute.set(PHYSICAL_ATTACK_SPEED, 0x80, 379);

		this.randomDamage = 10;
		this.attackRange = 40;
		this.damageRange = new int[] { 0, 0, 40, 120 };
		this.weight = 1560;
		this.price = 105000;
		this.soulshots = 2;
		this.spiritshots = 2;
	}

	@Override
	public void attack(Attacker source, Attackable target) {
		source.attack(target, this);
		target.receiveAttack(source, this);
	}
}
