package com.l2jserver.model.template;

import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

/**
 * Template for {@link L2Character}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class CharacterTemplate extends ActorTemplate<L2Character> {
	/**
	 * The character class
	 */
	protected final CharacterClass characterClass;
	/**
	 * The initial location for the character to be spawned
	 */
	protected final Point spawnLocation;

	protected CharacterTemplate(CharacterTemplateID id,
			CharacterClass characterClass, int intelligence, int strength,
			int concentration, int mentality, int dexterity, int witness,
			int physicalAttack, int magicalAttack, int physicalDefense,
			int magicalDefense, int attackSpeed, int castSpeed, int accuracy,
			int criticalChance, int evasionChance, int moveSpeed,
			int maxWeigth, boolean craft, Point spawnLocation) {
		super(id, characterClass.race, intelligence, strength, concentration,
				mentality, dexterity, witness, physicalAttack, magicalAttack,
				physicalDefense, magicalDefense, attackSpeed, castSpeed,
				accuracy, criticalChance, evasionChance, moveSpeed, maxWeigth,
				craft);
		this.characterClass = characterClass;
		this.spawnLocation = spawnLocation;
	}

	@Override
	public L2Character createInstance() {
		final L2Character character = new L2Character(baseAttributes);

		character.setRace(race);
		character.setCharacterClass(characterClass);
		character.setPoint(spawnLocation);

		return character;
	}

	/**
	 * @return the characterClass
	 */
	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	/**
	 * @return the initial spawn location
	 */
	public Point getSpawnLocation() {
		return spawnLocation;
	}

	@Override
	public CharacterTemplateID getID() {
		return (CharacterTemplateID) super.getID();
	}
}
