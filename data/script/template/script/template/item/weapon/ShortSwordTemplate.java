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
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;

public class ShortSwordTemplate extends AbstractNoGradeWeaponTemplate {
	public static final int ID = 1;

	@Inject
	public ShortSwordTemplate(ItemTemplateIDProvider factory) {
		super(factory.createID(ID), "icon.weapon_small_sword_i00",
				ItemMaterial.STEEL, InventoryPaperdoll.RIGHT_HAND,
				WeaponType.SWORD);

		attribute.set(PHYSICAL_ATTACK, 0x08, 8);
		attribute.set(MAGICAL_ATTACK, 0x08, 6);
		attribute.set(R_CRITICAL, 0x08, 8);
		attribute.set(PHYSICAL_ATTACK_SPEED, 0x08, 379);

		this.type = WeaponType.SWORD;
		this.paperdoll = InventoryPaperdoll.RIGHT_HAND;
		this.randomDamage = 10;
		this.attackRange = 40;
		this.damageRange = new int[] { 0, 0, 40, 120 };
		this.material = ItemMaterial.STEEL;
		this.weight = 1600;
		this.price = 590;
		this.soulshots = 1;
		this.spiritshots = 1;
	}
}
