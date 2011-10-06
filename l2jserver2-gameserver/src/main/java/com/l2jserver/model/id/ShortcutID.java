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
package com.l2jserver.model.id;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.game.Shortcut;
import com.l2jserver.model.id.compound.AbstractCompoundID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.provider.IDProvider;
import com.l2jserver.model.id.template.SkillTemplateID;

/**
 * Each {@link Shortcut} is identified by an {@link ID}.
 * <p>
 * Please, do not directly instantiate this class, use an {@link IDProvider}
 * instead.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ShortcutID extends AbstractCompoundID<CharacterID, ID<?>> {
	/**
	 * Creates a new instance
	 * 
	 * @param id1
	 *            the first id
	 * @param id2
	 *            the second id
	 */
	@Inject
	public ShortcutID(@Assisted("id1") CharacterID id1,
			@Assisted("id2") ID<?> id2) {
		super(id1, id2);
	}

	/**
	 * @return the character ID
	 */
	public CharacterID getCharacterID() {
		return getID1();
	}

	/**
	 * @return the shortcut target ID
	 */
	public ID<?> getTargetID() {
		return getID2();
	}

	/**
	 * @return true if the {@link #getTargetID()} returns an
	 *         {@link SkillTemplateID}
	 */
	public boolean isSkill() {
		return getID2() instanceof SkillTemplateID;
	}

	/**
	 * @return return the {@link #getTargetID()} casted to
	 *         {@link SkillTemplateID}
	 * @throws ClassCastException
	 *             if {@link #isSkill()} returns null.
	 */
	public SkillTemplateID getSkillID() {
		return (SkillTemplateID) getTargetID();
	}

	/**
	 * @return true if the {@link #getTargetID()} returns an {@link ItemID}
	 */
	public boolean isItem() {
		return getID2() instanceof ItemID;
	}

	/**
	 * @return return the {@link #getTargetID()} casted to {@link ItemID}
	 * @throws ClassCastException
	 *             if {@link #isItem()} returns null.
	 */
	public ItemID getItemID() {
		return (ItemID) getTargetID();
	}
}
