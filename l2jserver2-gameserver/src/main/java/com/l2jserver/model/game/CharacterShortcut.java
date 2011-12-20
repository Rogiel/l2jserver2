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
package com.l2jserver.model.game;

import com.l2jserver.model.AbstractModel;
import com.l2jserver.model.id.CharacterShortcutID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.Pet;

/**
 * An shortcut in Lineage II game interface
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterShortcut extends AbstractModel<CharacterShortcutID> {
	/**
	 * The character id
	 */
	private CharacterID characterID;
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
		/**
		 * Item shortcut
		 */
		ITEM(1),
		/**
		 * Skill shortcut
		 */
		SKILL(2),
		/**
		 * Social action shortcut
		 */
		ACTION(3),
		/**
		 * Macro shortcut
		 */
		MACRO(4),
		/**
		 * Recipe shortcut
		 */
		RECIPE(5),
		/**
		 * Bookmark shortcut
		 */
		TPBOOKMARK(6);

		/**
		 * The shortcut type id
		 */
		public final int id;

		/**
		 * @param id
		 *            the numeric id
		 */
		ShortcutType(int id) {
			this.id = id;
		}

		/**
		 * 
		 * @param id
		 *            the type id
		 * @return the {@link ShortcutType}
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
	 * The kind of actor that this shortcut is attached to
	 */
	private ShortcutActorType actorType;

	/**
	 * Enum with all supported actor types
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum ShortcutActorType {
		/**
		 * Shortcut is for an {@link L2Character}
		 */
		CHARACTER(1),
		/**
		 * Shortcut is for an {@link Pet}
		 */
		PET(2);

		/**
		 * The shortcut type id
		 */
		public final int id;

		/**
		 * @param id
		 *            the numeric id
		 */
		ShortcutActorType(int id) {
			this.id = id;
		}

		/**
		 * 
		 * @param id
		 *            the type id
		 * @return the {@link ShortcutType}
		 */
		public static ShortcutActorType fromID(int id) {
			for (final ShortcutActorType shortcut : values()) {
				if (shortcut.id == id)
					return shortcut;
			}
			return null;
		}
	}

	/**
	 * Creates a new instance
	 */
	public CharacterShortcut() {
	}

	/**
	 * Creates a new instance
	 * 
	 * @param characterID
	 *            the character id
	 */
	public CharacterShortcut(CharacterID characterID) {
		this.characterID = characterID;
	}

	/**
	 * Creates a new Item Shortcut
	 * 
	 * @param characterID
	 *            the character id
	 * @param itemID
	 *            the item id
	 * @param actorType
	 *            the actor type
	 */
	public CharacterShortcut(CharacterID characterID, ItemID itemID,
			ShortcutActorType actorType) {
		this.type = ShortcutType.ITEM;
		this.characterID = characterID;
		this.itemID = itemID;
		this.actorType = actorType;
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
	 * @param actorType
	 *            the actor type
	 */
	public CharacterShortcut(CharacterID characterID, SkillTemplateID skillID,
			int level, ShortcutActorType actorType) {
		this.type = ShortcutType.SKILL;
		this.characterID = characterID;
		this.skillID = skillID;
		this.level = level;
		this.actorType = actorType;
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
	 * @param actorType
	 *            the actor type
	 */
	public CharacterShortcut(CharacterID characterID, ShortcutType type,
			int slot, int page, ShortcutActorType actorType) {
		this.characterID = characterID;
		this.slot = slot;
		this.page = page;
		this.type = type;
		this.actorType = actorType;
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

	/**
	 * @param characterID
	 *            the character ID to set
	 */
	public void setCharacterID(CharacterID characterID) {
		this.characterID = characterID;
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
		desireUpdate();
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
		desireUpdate();
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
		desireUpdate();
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
		desireUpdate();
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
		desireUpdate();
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
		desireUpdate();
		this.level = level;
	}

	/**
	 * @return the actorType
	 */
	public ShortcutActorType getCharacterType() {
		return actorType;
	}

	/**
	 * @param actorType
	 *            the actorType to set
	 */
	public void setCharacterType(ShortcutActorType actorType) {
		desireUpdate();
		this.actorType = actorType;
	}
}
