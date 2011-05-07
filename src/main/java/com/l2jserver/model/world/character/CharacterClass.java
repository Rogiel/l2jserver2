package com.l2jserver.model.world.character;

import com.l2jserver.model.world.AbstractActor.Race;

/**
 * Defines all the possible classes for an character
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum CharacterClass {
	/**
	 * Human fighter
	 */
	HUMAN_FIGHTER(0x00, Race.HUMAN), WARRIOR(0x01, HUMAN_FIGHTER), GLADIATOR(
			0x02, WARRIOR), WARLORD(0x03, WARRIOR), KNIGHT(0x04, HUMAN_FIGHTER), PALADIN(
			0x05, KNIGHT), DARK_AVENGER(0x06, KNIGHT), ROGUE(0x07,
			HUMAN_FIGHTER), REASURE_HUNTER(0x08, ROGUE), HAWKEYE(0x09, ROGUE),
	/**
	 * Human mystic
	 */
	HUMAN_MYSTIC(0x0a, true, Race.HUMAN), WIZARD(0x0b, HUMAN_MYSTIC), SORCEROR(
			0x0c, WIZARD), NECROMANCER(0x0d, WIZARD), WARLOCK(0x0e, true, true,
			WIZARD), CLERIC(0x0f, HUMAN_MYSTIC), BISHOP(0x10, CLERIC), PROPHET(
			0x11, CLERIC),

	/**
	 * Elven fighter
	 */
	ELVEN_FIGHTER(0x12, Race.ELF), ELVEN_KNIGHT(0x13, ELVEN_FIGHTER), TEMPLE_KNIGHT(
			0x14, ELVEN_KNIGHT), SWORD_SINGER(0x15, ELVEN_KNIGHT), ELVEN_SCOUT(
			0x16, ELVEN_FIGHTER), PLAINS_WALKER(0x17, ELVEN_SCOUT), SILVER_RANGER(
			0x18, ELVEN_SCOUT),
	/**
	 * Elven mystic
	 */
	ELVEN_MYSTIC(0x19, true, Race.ELF), ELVEN_WIZARD(0x1a, ELVEN_MYSTIC), SPELLSINGER(
			0x1b, ELVEN_WIZARD), ELEMENTAL_SUMMONER(0x1c, true, true,
			ELVEN_WIZARD), ORACLE(0x1d, ELVEN_MYSTIC), ELDER(0x1e, ORACLE),

	/**
	 * Dark elf fighter
	 */
	DARK_FIGHTER(0x1f, Race.DARK_ELF), PALUS_KNIGHT(0x20, DARK_FIGHTER), SHILLIEN_KNIGHT(
			0x21, PALUS_KNIGHT), BLADEDANCER(0x22, PALUS_KNIGHT), ASSASSIN(
			0x23, DARK_FIGHTER), ABYSS_WALKER(0x24, ASSASSIN), PHANTOM_RANGER(
			0x25, ASSASSIN),

	/**
	 * Dark elf mystic
	 */
	DARK_MYSTIC(0x26, true, Race.DARK_ELF), DARK_WIZARD(0x27, DARK_MYSTIC), SPELLHOWLER(
			0x28, DARK_WIZARD), PHANTOM_SUMMONER(0x29, true, true, DARK_WIZARD), SHILLIEN_ORACLE(
			0x2a, DARK_MYSTIC), SHILLIEN_ELDER(0x2b, SHILLIEN_ORACLE);

	// TODO dwarf classes
	// TODO kamael classes
	// TODO 3rd classes

	public final int id;
	public final boolean mystic;
	public final boolean summoner;
	public final Race race;
	public final CharacterClass parent;

	private CharacterClass(int id, boolean mystic, boolean summoner, Race race,
			CharacterClass parent) {
		this.id = id;
		this.mystic = mystic;
		this.summoner = summoner;
		this.race = race;
		this.parent = parent;
	}

	private CharacterClass(int id, CharacterClass parent) {
		this(id, parent.mystic, parent.summoner, parent.race, parent);
	}

	private CharacterClass(int id, Race race, CharacterClass parent) {
		this(id, false, false, race, parent);
	}

	private CharacterClass(int id, Race race) {
		this(id, false, false, race, null);
	}

	private CharacterClass(int id, boolean mystic, Race race) {
		this(id, mystic, false, race, null);
	}

	private CharacterClass(int id, boolean mystic, boolean summoner,
			CharacterClass parent) {
		this(id, mystic, summoner, parent.race, parent);
	}

	public CharacterClass fromID(int id) {
		for (final CharacterClass c : values()) {
			if (c.id == id)
				return c;
		}
		return null;
	}

	/**
	 * Calculates the level of the class. Base class is zero.
	 * 
	 * @return the class level
	 */
	public final int level() {
		if (parent == null)
			return 0;
		return 1 + parent.level();
	}
}
