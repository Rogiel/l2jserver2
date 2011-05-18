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
package com.l2jserver.model.game;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.world.L2Character;

/**
 * An shortcut in Lineage II game interface
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Shortcut {
	/**
	 * The character id
	 */
	private final CharacterID characterID;
	/**
	 * The shortcut skill id (only if <tt>type</tt> is
	 * {@link ShortcutType#SKILL})
	 */
	private SkillTemplateID skillID;

	/**
	 * The shortcut item id (only if <tt>type</tt> is {@link ShortcutType#ITEM})
	 */
	private ItemID itemID;

	/**
	 * The shortcut slot (0 - 11 = 12 slots/page)
	 */
	private int slot;
	/**
	 * The shortcut page (0-3 = 4 pages)
	 */
	private int page;
	/**
	 * The shortcut type
	 */
	private ShortcutType type;

	/**
	 * Enum with all shortcut types supported
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum ShortcutType {
		ITEM(1), SKILL(2), ACTION(3), MACRO(4), RECIPE(5), TPBOOKMARK(6);

		/**
		 * The shortcut type id
		 */
		public final int id;

		ShortcutType(int id) {
			this.id = id;
		}

		/**
		 * 
		 * @param id
		 *            the type id
		 * @return
		 */
		public static ShortcutType fromID(int id) {
			for (final ShortcutType shortcut : values()) {
				if (shortcut.id == id)
					return shortcut;
			}
			return null;
		}
	}

	/**
	 * The skill level (only if <tt>type</tt> is {@link ShortcutType#SKILL})
	 */
	private int level;
	/**
	 * unknown!
	 */
	private int characterType;

	/**
	 * Creates a new instance
	 * 
	 * @param character
	 *            the character
	 */
	public Shortcut(CharacterID characterID) {
		this.characterID = characterID;
	}

	/**
	 * Creates a new Item Shortcut
	 * 
	 * @param characterID
	 *            the character id
	 * @param itemID
	 *            the item id
	 * @param characterType
	 *            the character type
	 */
	public Shortcut(CharacterID characterID, ItemID itemID, int characterType) {
		this.type = ShortcutType.ITEM;
		this.characterID = characterID;
		this.itemID = itemID;
		this.characterType = characterType;
	}

	/**
	 * Creates a new Skill Shortcut
	 * 
	 * @param characterID
	 *            the character id
	 * @param skillID
	 *            the skill id
	 * @param level
	 *            the skill level
	 * @param characterType
	 *            the character type
	 */
	public Shortcut(CharacterID characterID, SkillTemplateID skillID,
			int level, int characterType) {
		this.type = ShortcutType.SKILL;
		this.characterID = characterID;
		this.skillID = skillID;
		this.level = level;
		this.characterType = characterType;
	}

	/**
	 * Creates another type of shortcut
	 * 
	 * @param characterID
	 *            the character id
	 * @param type
	 *            the shortcut type
	 * @param slot
	 *            the shortcut slot
	 * @param page
	 *            the shortcut page
	 * @param characterType
	 *            the character type
	 */
	public Shortcut(CharacterID characterID, ShortcutType type, int slot,
			int page, int characterType) {
		this.characterID = characterID;
		this.slot = slot;
		this.page = page;
		this.type = type;
		this.characterType = characterType;
	}

	/**
	 * @return the skillID
	 */
	public SkillTemplateID getSkillID() {
		return skillID;
	}

	/**
	 * @param skillID
	 *            the skillID to set
	 */
	public void setSkillID(SkillTemplateID skillID) {
		this.skillID = skillID;
	}

	/**
	 * @return the itemID
	 */
	public ItemID getItemID() {
		return itemID;
	}

	/**
	 * @param itemID
	 *            the itemID to set
	 */
	public void setItemID(ItemID itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * @param slot
	 *            the slot to set
	 */
	public void setSlot(int slot) {
		this.slot = slot;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the type
	 */
	public ShortcutType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ShortcutType type) {
		this.type = type;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the characterType
	 */
	public int getCharacterType() {
		return characterType;
	}

	/**
	 * @param characterType
	 *            the characterType to set
	 */
	public void setCharacterType(int characterType) {
		this.characterType = characterType;
	}

	/**
	 * @return the character id
	 */
	public CharacterID getCharacterID() {
		return characterID;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return characterID.getObject();
	}
}
