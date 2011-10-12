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

import javax.xml.bind.annotation.XmlType;

/**
 * Enum for all available item types
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlType(name = "ItemEnumType")
public enum ItemType {
	/**
	 * No specific item type
	 */
	NONE(1),
	/**
	 * An arrow for bows
	 */
	ARROW(2),
	/**
	 * An potion used to regen cp, hp, mp or cure something
	 */
	POTION(3),
	/**
	 * Enchant scroll for weapons
	 */
	WEAPON_ENCHANT_SCROLL(4),
	/**
	 * Enchant scroll for armors
	 */
	ARMOR_ENCHANT_SCROLL(5),
	/**
	 * An all purpose scroll
	 */
	SCROLL(6),
	/**
	 * An item recipe
	 */
	RECIPE(7),
	/**
	 * An material used to craft another item
	 */
	MATERIAL(8),
	/**
	 * Pet collar
	 */
	PET_COLLAR(9),
	/**
	 * Castle guard?????
	 */
	CASTLE_GUARD(10),
	/**
	 * An lottery ticket
	 */
	LOTTERY_TICKET(11),
	/**
	 * An race ticket
	 */
	RACE_TICKET(12),
	/**
	 * An dye
	 */
	DYE(13),
	/**
	 * A seed
	 */
	SEED(14),
	/**
	 * A crop
	 */
	CROP(15),
	/**
	 * An mature crop
	 */
	MATURECROP(16),
	/**
	 * An harvest
	 */
	HARVEST(17),
	/**
	 * An seed
	 */
	SEED2(18),
	/**
	 * An ticket of lord
	 */
	TICKET_OF_LORD(19),
	/**
	 * An lure
	 */
	LURE(20),
	/**
	 * An blessed weapon enchant scroll
	 */
	BLESSED_WEAPON_ENCHANT_SCROLL(21),
	/**
	 * An blessed armor enchant scroll
	 */
	BLESSED_ARMOR_ENCHANT_SCROLL(22),
	/**
	 * An coupon
	 */
	COUPON(23),
	/**
	 * An elixir
	 */
	ELIXIR(24),
	/**
	 * Scroll used for enchanting attributes
	 */
	ATTRIBUTE_ENCHANT_SCROLL(25),
	/**
	 * Bolt? unk.
	 */
	BOLT(26), SCRL_INC_ENCHANT_PROP_WP(27), SCRL_INC_ENCHANT_PROP_AM(28), ANCIENT_CRYSTAL_ENCHANT_WP(
			29), ANCIENT_CRYSTAL_ENCHANT_AM(30), RUNE_SELECT(31), RUNE(32);

	/**
	 * The packet id for this item type
	 */
	public final int id;

	/**
	 * @param id
	 *            the numeric id
	 */
	ItemType(int id) {
		this.id = id;
	}
}
