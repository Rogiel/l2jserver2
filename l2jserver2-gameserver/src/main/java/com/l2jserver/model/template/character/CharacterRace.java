package com.l2jserver.model.template.character;

import javax.xml.bind.annotation.XmlType;

/**
 * Represents the character race.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlType(name = "CharacterRaceType")
public enum CharacterRace {
	HUMAN(0x00), ELF(0x01), DARK_ELF(0x02), ORC(0x03), DWARF(0x04), KAMAEL(0x05);

	/**
	 * The numeric ID representing this race
	 */
	public final int id;

	/**
	 * @param id
	 *            the race numeric id
	 */
	CharacterRace(int id) {
		this.id = id;
	}

	/**
	 * Finds the race based on the <tt>id</tt>
	 * 
	 * @param id
	 *            the id
	 * @return the race constant
	 */
	public static CharacterRace fromOption(int id) {
		for (final CharacterRace race : values()) {
			if (race.id == id)
				return race;
		}
		return null;
	}
}