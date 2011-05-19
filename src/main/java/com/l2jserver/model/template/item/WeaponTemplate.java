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
package com.l2jserver.model.template.item;

import java.util.Map;
import java.util.Map.Entry;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.capability.Attackable;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.util.calculator.Calculator;
import com.l2jserver.util.calculator.DivisionFunction;
import com.l2jserver.util.calculator.MultiplicationFunction;
import com.l2jserver.util.calculator.Function;
import com.l2jserver.util.calculator.SetFunction;
import com.l2jserver.util.calculator.SubtractFunction;
import com.l2jserver.util.calculator.SumFunction;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Template for Weapon {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class WeaponTemplate extends ItemTemplate implements Attackable {
	/**
	 * The paperldoll slot used by this weapon
	 */
	protected InventoryPaperdoll paperdoll = null;
	/**
	 * The weapon type
	 */
	protected WeaponType type;

	/**
	 * The weapon type enum
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum WeaponType {
		FISHINGROD, CROSSBOW, BIG_SWORD, RAPIER, DUAL_FIST, ETC, DAGGER, BLUNT, BIG_BLUNT, DUAL_DAGGER, DUAL, FLAG, POLE, FIST, BOW, OWN_THING, SWORD, ANCIENT_SWORD;
	}

	/**
	 * The weapon's attributes
	 */
	protected final WeaponAttribute attribute = new WeaponAttribute();

	/**
	 * This weapon random damage
	 */
	protected int randomDamage = 0;
	/**
	 * This weapon attack range
	 */
	protected int attackRange = 0;
	/**
	 * Unknown!
	 */
	protected int[] damageRange = new int[] {};

	/**
	 * Number of soulshots used per attack
	 */
	protected int soulshots = 0;
	/**
	 * Number of spirithots used per cast
	 */
	protected int spiritshots = 0;

	/**
	 * The crystal count for this weapon
	 */
	protected int crystals = 0;
	/**
	 * This weapon crystal type
	 */
	protected CrystalType crystal;

	/**
	 * Enum containing all crystal types possible
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum CrystalType {
		GRADE_A, GRADE_B, GRADE_C, GRADE_D;
	}

	/**
	 * Creates a new instance. All arguments are required!
	 * 
	 * @param id
	 *            the template id
	 * @param icon
	 *            the item icon
	 * @param material
	 *            the item material
	 * @param paperdoll
	 *            the paperdoll slot
	 * @param type
	 *            the weapon type
	 */
	public WeaponTemplate(ItemTemplateID id, String icon,
			ItemMaterial material, InventoryPaperdoll paperdoll, WeaponType type) {
		super(id, icon, material);
		this.paperdoll = paperdoll;
		this.type = type;
	}

	/**
	 * Append all operation for this weapon
	 * 
	 * @param type
	 *            the attribute type
	 * @param calc
	 *            the calculator
	 */
	public void calculator(WeaponAttributeType type, Calculator calc) {
		attribute.calculator(type, calc);
	}

	/**
	 * Attribute types for weapons
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum WeaponAttributeType {
		PHYSICAL_ATTACK, MAGICAL_ATTACK, R_CRITICAL, PHYSICAL_ATTACK_SPEED;
	}

	/**
	 * This class handles the Weapon attributes an can add operations to the
	 * calculator
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public class WeaponAttribute {
		private final Map<WeaponAttributeType, Map<Integer, Function<Double>>> operations = CollectionFactory
				.newMap(null, null);

		/**
		 * Sets the result of an calculator
		 * 
		 * @param type
		 *            the attribute type
		 * @param order
		 *            the calculation order
		 * @param value
		 *            the value to set
		 */
		public void set(WeaponAttributeType type, int order, double value) {
			getMap(type).put(order, new SetFunction(value));
		}

		/**
		 * Adds <tt>value</tt> to the result of an calculator
		 * 
		 * @param type
		 *            the attribute type
		 * @param order
		 *            the calculation order
		 * @param value
		 *            the value to be summed
		 */
		public void add(WeaponAttributeType type, int order, double value) {
			getMap(type).put(order, new SumFunction(value));
		}

		/**
		 * Subtracts <tt>value</tt> from the result of an calculator
		 * 
		 * @param type
		 *            the attribute type
		 * @param order
		 *            the calculation order
		 * @param value
		 *            the value to be subtracted
		 */
		public void sub(WeaponAttributeType type, int order, double value) {
			getMap(type).put(order, new SubtractFunction(value));
		}

		/**
		 * Multiply <tt>value</tt> the result of an calculator
		 * 
		 * @param type
		 *            the attribute type
		 * @param order
		 *            the calculation order
		 * @param value
		 *            the value to be multiplied
		 */
		public void mult(WeaponAttributeType type, int order, double value) {
			getMap(type).put(order, new MultiplicationFunction(value));
		}

		/**
		 * Divides by <tt>value</tt> the result of an calculator
		 * 
		 * @param type
		 *            the attribute type
		 * @param order
		 *            the calculation order
		 * @param value
		 *            the value to be divided by
		 */
		public void div(WeaponAttributeType type, int order, double value) {
			getMap(type).put(order, new DivisionFunction(value));
		}

		/**
		 * Performs an enchant operation. Note that this is not implemented yet!
		 * 
		 * @param type
		 *            the attribute type
		 * @param order
		 *            the calculation order
		 * @param value
		 *            TODO
		 */
		public void enchant(WeaponAttributeType type, int order, double value) {
			// TODO enchant operation for weapon
		}

		/**
		 * Returns the Order-Operation map for <tt>type</tt>
		 * 
		 * @param type
		 *            the type
		 * @return the order-operation map
		 */
		private Map<Integer, Function<Double>> getMap(WeaponAttributeType type) {
			Map<Integer, Function<Double>> map = operations.get(type);
			if (map == null) {
				map = CollectionFactory.newMap(null, null);
				operations.put(type, map);
			}
			return map;
		}

		/**
		 * Creates the calculator object for this weapon
		 * 
		 * @param type
		 *            the type
		 * @param calculator
		 *            the calculator
		 */
		private void calculator(WeaponAttributeType type, Calculator calculator) {
			final Map<Integer, Function<Double>> operations = this.operations
					.get(type);
			for (final Entry<Integer, Function<Double>> entry : operations
					.entrySet()) {
				calculator.add(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * @return the paperdoll
	 */
	public InventoryPaperdoll getPaperdoll() {
		return paperdoll;
	}

	/**
	 * @param paperdoll
	 *            the paperdoll to set
	 */
	public void setPaperdoll(InventoryPaperdoll paperdoll) {
		this.paperdoll = paperdoll;
	}

	/**
	 * @return the type
	 */
	public WeaponType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(WeaponType type) {
		this.type = type;
	}

	/**
	 * @return the randomDamage
	 */
	public int getRandomDamage() {
		return randomDamage;
	}

	/**
	 * @param randomDamage
	 *            the randomDamage to set
	 */
	public void setRandomDamage(int randomDamage) {
		this.randomDamage = randomDamage;
	}

	/**
	 * @return the attackRange
	 */
	public int getAttackRange() {
		return attackRange;
	}

	/**
	 * @param attackRange
	 *            the attackRange to set
	 */
	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	/**
	 * @return the damageRange
	 */
	public int[] getDamageRange() {
		return damageRange;
	}

	/**
	 * @param damageRange
	 *            the damageRange to set
	 */
	public void setDamageRange(int[] damageRange) {
		this.damageRange = damageRange;
	}

	/**
	 * @return the soulshots
	 */
	public int getSoulshots() {
		return soulshots;
	}

	/**
	 * @param soulshots
	 *            the soulshots to set
	 */
	public void setSoulshots(int soulshots) {
		this.soulshots = soulshots;
	}

	/**
	 * @return the spiritshots
	 */
	public int getSpiritshots() {
		return spiritshots;
	}

	/**
	 * @param spiritshots
	 *            the spiritshots to set
	 */
	public void setSpiritshots(int spiritshots) {
		this.spiritshots = spiritshots;
	}

	/**
	 * @return the crystals
	 */
	public int getCrystals() {
		return crystals;
	}

	/**
	 * @param crystals
	 *            the crystals to set
	 */
	public void setCrystals(int crystals) {
		this.crystals = crystals;
	}

	/**
	 * @return the crystal
	 */
	public CrystalType getCrystal() {
		return crystal;
	}

	/**
	 * @param crystal
	 *            the crystal to set
	 */
	public void setCrystal(CrystalType crystal) {
		this.crystal = crystal;
	}

	/**
	 * @return the attribute
	 */
	public WeaponAttribute getAttribute() {
		return attribute;
	}
}
