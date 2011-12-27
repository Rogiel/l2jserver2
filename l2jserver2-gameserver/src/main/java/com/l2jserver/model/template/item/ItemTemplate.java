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
package com.l2jserver.model.template.item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.AbstractTemplate;
import com.l2jserver.model.template.calculator.ItemSetActorCalculator;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.actor.stat.StatType;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;

/**
 * Template for an {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "item", namespace = "http://schemas.l2jserver2.com/item")
@XmlType(namespace = "http://schemas.l2jserver2.com/item", name = "ItemType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemTemplate extends AbstractTemplate {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ItemTemplate.class);

	/**
	 * The Item Template ID
	 */
	@XmlAttribute(name = "id", required = true)
	@XmlJavaTypeAdapter(ItemTemplateIDAdapter.class)
	protected ItemTemplateID id;

	/**
	 * The item name
	 */
	@XmlAttribute(name = "name", required = true)
	protected String name;
	/**
	 * The item weight
	 */
	@XmlElement(name = "weight", required = true)
	protected int weight = 0;
	/**
	 * The item price
	 */
	@XmlElement(name = "price", required = true)
	protected int price = 0;
	/**
	 * The item icon
	 */
	@XmlElement(name = "icon", required = false)
	protected String icon;
	/**
	 * The item effects
	 */
	@XmlElement(name = "effect", required = false)
	protected EffectContainer effect;

	/**
	 * Effect container for items
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "ItemEffectsType")
	private static class EffectContainer {
		/**
		 * The effect type
		 */
		@XmlAttribute(name = "type", required = true)
		protected EffectType effect;
	}

	/**
	 * The item stats container
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "ItemStatsType")
	protected static class StatsContainer {
		/**
		 * The weapon's physical damage
		 */
		@XmlElement(name = "physicalDamage", required = false)
		protected StatAttribute physicalDamage;
		/**
		 * The weapons's magical damage
		 */
		@XmlElement(name = "magicalDamage", required = false)
		protected StatAttribute magicalDamage;
		/**
		 * The weapon's critical chance
		 */
		@XmlElement(name = "criticalChance", required = false)
		protected StatAttribute criticalChance;
		/**
		 * The weapon's physical attack speed
		 */
		@XmlElement(name = "physicalAttackSpeed", required = false)
		protected StatAttribute physicalAttackSpeed;
	}

	/**
	 * The item stats
	 */
	@XmlElement(name = "stats", required = false)
	protected StatsContainer stats;

	/**
	 * The item material
	 */
	@XmlElement(name = "material", required = true)
	protected ItemMaterial material;

	/**
	 * The item effect type
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "ItemEffectType")
	public enum EffectType {
		/**
		 * The effect is applied immediately once used
		 */
		IMMEDIATE;
	}

	/**
	 * The item type
	 */
	protected ItemType itemType = ItemType.NONE;
	/**
	 * The weapon type
	 */
	protected WeaponType weaponType = WeaponType.NONE;
	/**
	 * The armor type
	 */
	protected ArmorType armorType = ArmorType.NONE;

	/**
	 * An item stat attribute
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "ItemAttributeType")
	public static class StatAttribute {
		/**
		 * The set
		 */
		@XmlElement(name = "set", required = true)
		protected StatSet set;

		/**
		 * Defines an SET calculator function
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		@XmlType(name = "")
		public static class StatSet {
			/**
			 * The execution order
			 */
			@XmlAttribute(name = "order", required = true)
			protected int order;
			/**
			 * The value to be set
			 */
			@XmlValue
			protected double value;

			/**
			 * @return the order
			 */
			public int getOrder() {
				return order;
			}

			/**
			 * @return the value
			 */
			public double getValue() {
				return value;
			}
		}

		/**
		 * @return the set
		 */
		public StatSet getSet() {
			return set;
		}
	}

	/**
	 * Create a new {@link Item}
	 * 
	 * @return the created object
	 */
	public Item create() {
		log.debug("Creating a new Item instance with template {}", this);
		return new Item(id);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @return the immediateEffect
	 */
	public EffectType getEffectType() {
		return effect.effect;
	}

	/**
	 * @return the material
	 */
	public ItemMaterial getMaterial() {
		return material;
	}

	/**
	 * @return the physical damage
	 */
	public ItemSetActorCalculator getPhysicalDamage() {
		if (stats == null)
			return null;
		if (stats.physicalDamage == null)
			return null;
		return new ItemSetActorCalculator(stats.physicalDamage.set,
				StatType.POWER_ATTACK);
	}

	/**
	 * @return the magical damage
	 */
	public ItemSetActorCalculator getMagicalDamage() {
		if (stats == null)
			return null;
		if (stats.magicalDamage == null)
			return null;
		return new ItemSetActorCalculator(stats.magicalDamage.set,
				StatType.MAGIC_ATTACK);
	}

	/**
	 * @return the magical damage
	 */
	public ItemSetActorCalculator getCriticalChance() {
		if (stats == null)
			return null;
		if (stats.criticalChance == null)
			return null;
		return new ItemSetActorCalculator(stats.criticalChance.set,
				StatType.CRITICAL_RATE);
	}

	@Override
	public ItemTemplateID getID() {
		return id;
	}
}
