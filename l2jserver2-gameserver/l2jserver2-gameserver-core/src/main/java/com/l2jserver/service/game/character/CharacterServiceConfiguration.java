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
package com.l2jserver.service.game.character;

import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.service.ServiceConfiguration;
import com.l2jserver.service.configuration.XMLConfigurationService.ConfigurationXPath;

/**
 * Configuration interface for {@link CharacterService}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface CharacterServiceConfiguration extends ServiceConfiguration {
	/**
	 * @return whether the creation of new characters is available
	 */
	@ConfigurationPropertyGetter(defaultValue = "true")
	@ConfigurationXPath("creation/@allow")
	boolean isCharacterCreationAllowed();

	/**
	 * @param state
	 *            whether the creation of new characters is available
	 * 
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("creation/@allow")
	void setCharacterCreationAllowed(boolean state);

	/**
	 * @return the allowed races for new characters
	 */
	@ConfigurationPropertyGetter(defaultValue = "HUMAN|ELF|DARK_ELF|ORC|DWARF|KAMAEL")
	@ConfigurationXPath("creation/@allowed-races")
	CharacterRace[] getAllowedNewCharacterRaces();

	/**
	 * @param allowedRaces
	 *            the allowed races for new characters
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("creation/@allowed-races")
	void setAllowedNewCharacterRaces(CharacterRace[] allowedRaces);

	/**
	 * @return the allowed gender for new characters
	 */
	@ConfigurationPropertyGetter(defaultValue = "MALE|FEMALE")
	@ConfigurationXPath("creation/@allowed-genders")
	ActorSex[] getAllowedNewCharacterGenders();

	/**
	 * @param allowedGenders
	 *            the allowed genders
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("creation/@allowed-genders")
	void setAllowedNewCharacterGenders(ActorSex[] allowedGenders);

	/**
	 * @return the maximum number of character per account on this server
	 */
	@ConfigurationPropertyGetter(defaultValue = "8")
	@ConfigurationXPath("creation/limits/@max-per-account")
	int getMaxCharactersPerAccount();

	/**
	 * @param maxCharactersPerAccount
	 *            the maximum number of character per account on this server
	 */
	@ConfigurationPropertySetter
	@ConfigurationXPath("creation/limits/@max-per-account")
	void setMaxCharactersPerAccount(int maxCharactersPerAccount);
}
