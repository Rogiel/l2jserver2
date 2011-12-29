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
package com.l2jserver.service.game.item;

import com.l2jserver.service.ServiceConfiguration;
import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;

/**
 * Defines configurations for {@link ItemService} implementations
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface ItemServiceConfiguration extends ServiceConfiguration {
	/**
	 * Gets the item drop mode
	 * 
	 * @return the drop mode
	 */
	@ConfigurationPropertyGetter(defaultValue = "ALL")
	@ConfigurationXPath("drop/@persistent")
	ItemServiceDropMode getItemDropMode();

	/**
	 * Sets the item drop mode
	 * 
	 * @param mode
	 *            the drop mode
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("drop/@persistent")
	void setItemDropMode(ItemServiceDropMode mode);

	/**
	 * The drop modes available
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum ItemServiceDropMode {
		/**
		 * All types of drops are stored into the database.
		 */
		ALL,
		/**
		 * Only items dropped by characters are stored in the database.
		 */
		CHARACTER_ONLY,
		/**
		 * None of the dropped items are saved into the database.
		 */
		NONE;
	}
}
