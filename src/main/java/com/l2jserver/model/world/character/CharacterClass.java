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
	HUMAN_FIGHTER(0x00, ClassType.FIGHTER, Race.HUMAN), WARRIOR(0x01,
			HUMAN_FIGHTER), GLADIATOR(0x02, WARRIOR), WARLORD(0x03, WARRIOR), KNIGHT(
			0x04, HUMAN_FIGHTER), PALADIN(0x05, KNIGHT), DARK_AVENGER(0x06,
			KNIGHT), ROGUE(0x07, HUMAN_FIGHTER), TREASURE_HUNTER(0x08, ROGUE), HAWKEYE(
			0x09, ROGUE),
	// 3rd classes
	DUELIST(0x58, GLADIATOR), DREADNOUGHT(0x59, WARLORD), phoenixKnight(0x5a,
			PALADIN), hellKnight(0x5b, DARK_AVENGER), sagittarius(0x5c, HAWKEYE), adventurer(
			0x5d, TREASURE_HUNTER),

	/**
	 * Human mystic
	 */
	HUMAN_MYSTIC(0x0a, ClassType.MYSTIC, Race.HUMAN), WIZARD(0x0b, HUMAN_MYSTIC), SORCEROR(
			0x0c, WIZARD), NECROMANCER(0x0d, WIZARD), WARLOCK(0x0e, true,
			WIZARD), CLERIC(0x0f, ClassType.PRIEST, HUMAN_MYSTIC), BISHOP(0x10,
			CLERIC), PROPHET(0x11, CLERIC),
	// 3rd classes
	ARCHMAGE(0x5e, SORCEROR), SOULTAKER(0x5f, NECROMANCER), ARCANA_LORD(0x60,
			WARLOCK), CARDINAL(0x61, BISHOP), HIEROPHANT(0x62, PROPHET),

	/**
	 * Elven fighter
	 */
	ELVEN_FIGHTER(0x12, ClassType.FIGHTER, Race.ELF), ELVEN_KNIGHT(0x13,
			ELVEN_FIGHTER), TEMPLE_KNIGHT(0x14, ELVEN_KNIGHT), SWORD_SINGER(
			0x15, ELVEN_KNIGHT), ELVEN_SCOUT(0x16, ELVEN_FIGHTER), PLAINS_WALKER(
			0x17, ELVEN_SCOUT), SILVER_RANGER(0x18, ELVEN_SCOUT),
	// 3rd classes
	EVA_TEMPLAR(0x63, TEMPLE_KNIGHT), SWORD_MUSE(0x64, SWORD_SINGER), WIND_RIDER(
			0x65, PLAINS_WALKER), MOONLIGHT_SENTINEL(0x66, SILVER_RANGER),
	/**
	 * Elven mystic
	 */
	ELVEN_MYSTIC(0x19, ClassType.MYSTIC, Race.ELF), ELVEN_WIZARD(0x1a,
			ELVEN_MYSTIC), SPELLSINGER(0x1b, ELVEN_WIZARD), ELEMENTAL_SUMMONER(
			0x1c, true, ELVEN_WIZARD), ORACLE(0x1d, ClassType.PRIEST,
			ELVEN_MYSTIC), ELDER(0x1e, ORACLE),
	// 3rd classes
	MYSTIC_MUSE(0x67, SPELLSINGER), ELEMENTAL_MASTER(0x68, ELEMENTAL_SUMMONER), EVA_SAINT(
			0x69, ELDER),

	/**
	 * Dark elf fighter
	 */
	DARK_FIGHTER(0x1f, ClassType.FIGHTER, Race.DARK_ELF), PALUS_KNIGHT(0x20,
			DARK_FIGHTER), SHILLIEN_KNIGHT(0x21, PALUS_KNIGHT), BLADEDANCER(
			0x22, PALUS_KNIGHT), ASSASSIN(0x23, DARK_FIGHTER), ABYSS_WALKER(
			0x24, ASSASSIN), PHANTOM_RANGER(0x25, ASSASSIN),
	// 3rd classes
	SHILLIEN_TEMPLAR(0x6a, SHILLIEN_KNIGHT), spectralDancer(0x6b, BLADEDANCER), ghostHunter(
			0x6c, ABYSS_WALKER), ghostSentinel(0x6d, PHANTOM_RANGER),

	/**
	 * Dark elf mystic
	 */
	DARK_MYSTIC(0x26, ClassType.MYSTIC, Race.DARK_ELF), DARK_WIZARD(0x27,
			DARK_MYSTIC), SPELLHOWLER(0x28, DARK_WIZARD), PHANTOM_SUMMONER(
			0x29, true, DARK_WIZARD), SHILLIEN_ORACLE(0x2a, ClassType.PRIEST,
			DARK_MYSTIC), SHILLIEN_ELDER(0x2b, SHILLIEN_ORACLE),
	// 3rd classes
	STORM_SCREAMER(0x6e, SPELLHOWLER), SPECTRAL_MASTER(0x6f, PHANTOM_SUMMONER), SHILLIEAN_SAINT(
			0x70, SHILLIEN_ELDER),

	/**
	 * Orc fighter
	 */
	ORC_FIGHTER(0x2c, ClassType.FIGHTER, Race.ORC), ORC_RAIDER(0x2d,
			ORC_FIGHTER), DESTROYER(0x2e, ORC_RAIDER), ORC_MONK(0x2f,
			ORC_FIGHTER), TYRANT(0x30, ORC_RAIDER),
	// 3rd classes
	TITAN(0x71, DESTROYER), GRAND_KHAUATARI(0x72, TYRANT),

	/**
	 * Orc mystic
	 */
	ORC_MYSTIC(0x31, ClassType.MYSTIC, Race.ORC), ORC_SHAMAN(0x32, ORC_MYSTIC), OVERLORD(
			0x33, ORC_SHAMAN), WARCRYER(0x34, ORC_SHAMAN),
	// 3rd classes
	DOMINATOR(0x73, OVERLORD), DOOMCRYER(0x74, WARCRYER),

	/**
	 * Dwarf fighter
	 */
	DWARVEN_FIGHTER(0x35, ClassType.FIGHTER, Race.DWARF), SCAVENGER(0x36,
			DWARVEN_FIGHTER), BOUNTY_HUNTER(0x37, SCAVENGER), ARTISAN(0x38,
			DWARVEN_FIGHTER), WARSMITH(0x39, ARTISAN),
	// 3rd classes
	FORTUNE_SEEKER(0x75, BOUNTY_HUNTER), MAESTRO(0x76, WARSMITH),

	/**
	 * Kamael male soldier
	 */
	MALE_SOLDIER(0x7b, ClassType.FIGHTER, Race.KAMAEL), TROOPER(0x7D,
			MALE_SOLDIER), BERSEKER(0x7F, TROOPER), MALE_SOULBREAKER(0x80,
			TROOPER), DOOMBRINGER(0x83, BERSEKER), MALE_SOULDHOUND(0x84,
			MALE_SOULBREAKER),

	/**
	 * Kamael female soldier
	 */
	FEMALE_SOLDIER(0x7C, ClassType.FIGHTER, Race.KAMAEL), WARDER(0x7E,
			FEMALE_SOLDIER), FEMALE_SOULBREAKER(0x81, WARDER), ARBALESTER(0x82,
			WARDER), FEMALE_SOULDHOUND(0x85, FEMALE_SOULBREAKER), TRICKSTER(
			0x86, ARBALESTER), INSPECTOR(0x87, WARDER), JUDICATOR(0x88,
			INSPECTOR),

	// DUMMY ENTRIES, TRASH
	DUMMY_ENTRY_1(58, null, false, null, null), DUMMY_ENTRY_2(59, null, false,
			null, null), DUMMY_ENTRY_3(60, null, false, null, null), DUMMY_ENTRY_4(
			61, null, false, null, null), DUMMY_ENTRY_5(62, null, false, null,
			null), DUMMY_ENTRY_6(63, null, false, null, null), DUMMY_ENTRY_7(
			64, null, false, null, null), DUMMY_ENTRY_8(65, null, false, null,
			null), DUMMY_ENTRY_9(66, null, false, null, null), DUMMY_ENTRY_10(
			67, null, false, null, null), DUMMY_ENTRY_11(68, null, false, null,
			null), DUMMY_ENTRY_12(69, null, false, null, null), DUMMY_ENTRY_13(
			70, null, false, null, null), DUMMY_ENTRY_14(71, null, false, null,
			null), DUMMY_ENTRY_15(72, null, false, null, null), DUMMY_ENTRY_16(
			73, null, false, null, null), DUMMY_ENTRY_17(74, null, false, null,
			null), DUMMY_ENTRY_18(75, null, false, null, null), DUMMY_ENTRY_19(
			76, null, false, null, null), DUMMY_ENTRY_20(77, null, false, null,
			null), DUMMY_ENTRY_21(78, null, false, null, null), DUMMY_ENTRY_22(
			79, null, false, null, null), DUMMY_ENTRY_23(80, null, false, null,
			null), DUMMY_ENTRY_24(81, null, false, null, null), DUMMY_ENTRY_25(
			82, null, false, null, null), DUMMY_ENTRY_26(83, null, false, null,
			null), DUMMY_ENTRY_27(84, null, false, null, null), DUMMY_ENTRY_28(
			85, null, false, null, null), DUMMY_ENTRY_29(86, null, false, null,
			null), DUMMY_ENTRY_30(87, null, false, null, null), DUMMY_ENTRY_31(
			0x77, null, false, null, null), DUMMY_ENTRY_32(0x78, null, false,
			null, null), DUMMY_ENTRY_33(0x79, null, false, null, null), DUMMY_ENTRY_34(
			0x7a, null, false, null, null);

	public final int id;
	public final ClassType type;
	public final boolean summoner;
	public final Race race;
	public final CharacterClass parent;

	private CharacterClass(int id, ClassType type, boolean summoner, Race race,
			CharacterClass parent) {
		this.id = id;
		this.type = type;
		this.summoner = summoner;
		this.race = race;
		this.parent = parent;
	}

	private CharacterClass(int id, CharacterClass parent) {
		this(id, parent.type, parent.summoner, parent.race, parent);
	}

	private CharacterClass(int id, boolean summoner, CharacterClass parent) {
		this(id, parent.type, summoner, parent.race, parent);
	}

	private CharacterClass(int id, Race race, CharacterClass parent) {
		this(id, parent.type, parent.summoner, race, parent);
	}

	private CharacterClass(int id, ClassType type, Race race) {
		this(id, type, false, race, null);
	}

	private CharacterClass(int id, ClassType type, CharacterClass parent) {
		this(id, type, false, parent.race, parent);
	}

	private CharacterClass(int id, ClassType type, boolean summoner,
			CharacterClass parent) {
		this(id, type, summoner, parent.race, parent);
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

	public enum ClassType {
		FIGHTER, MYSTIC, PRIEST;
	}
}
