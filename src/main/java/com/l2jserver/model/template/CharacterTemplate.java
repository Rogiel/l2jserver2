package com.l2jserver.model.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterBaseAttributes;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

/**
 * Template for {@link L2Character}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class CharacterTemplate extends AbstractTemplate<L2Character> {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(CharacterTemplate.class);

	/**
	 * The character race
	 */
	protected final Race race;
	/**
	 * The character class
	 */
	protected final CharacterClass characterClass;
	/**
	 * The initial location for the character to be spawned
	 */
	protected final Point spawnLocation;
	/**
	 * The base attributes instance
	 */
	protected final CharacterBaseAttributes baseAttributes;

	public CharacterTemplate(CharacterTemplateID id,
			CharacterClass characterClass, int intelligence, int strength,
			int concentration, int mentality, int dexterity, int witness,
			int physicalAttack, int magicalAttack, int physicalDefense,
			int magicalDefense, int attackSpeed, int castSpeed, int accuracy,
			int criticalChance, int evasionChance, int moveSpeed,
			int maxWeigth, boolean craft, Point spawnLocation) {
		super(id);
		this.race = characterClass.race;
		this.characterClass = characterClass;
		this.spawnLocation = spawnLocation;
		baseAttributes = new CharacterBaseAttributes(intelligence, strength,
				concentration, mentality, dexterity, witness, physicalAttack,
				magicalAttack, physicalDefense, magicalDefense, attackSpeed,
				castSpeed, accuracy, criticalChance, evasionChance, moveSpeed,
				maxWeigth, craft);
	}

	@Override
	public L2Character create() {
		log.debug("Creating a new Character instance with template {}", this);
		final L2Character character = new L2Character(baseAttributes);

		character.setRace(race);
		character.setCharacterClass(characterClass);
		character.setPoint(spawnLocation);

		// character.getBaseAttributes().setIntelligence(intelligence);
		// character.getBaseAttributes().setStrength(strength);
		// character.getBaseAttributes().setConcentration(concentration);
		// character.getBaseAttributes().setMentality(mentality);
		// character.getBaseAttributes().setDextry(dextry);
		// character.getBaseAttributes().setWitness(witness);

		return character;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
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

	/**
	 * @return the baseAttributes
	 */
	public CharacterBaseAttributes getBaseAttributes() {
		return baseAttributes;
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getIntelligence()
	 */
	public int getIntelligence() {
		return baseAttributes.getIntelligence();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getStrength()
	 */
	public int getStrength() {
		return baseAttributes.getStrength();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getConcentration()
	 */
	public int getConcentration() {
		return baseAttributes.getConcentration();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getMentality()
	 */
	public int getMentality() {
		return baseAttributes.getMentality();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getDexterity()
	 */
	public int getDextry() {
		return baseAttributes.getDexterity();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getWitness()
	 */
	public int getWitness() {
		return baseAttributes.getWitness();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getPhysicalAttack()
	 */
	public int getPhysicalAttack() {
		return baseAttributes.getPhysicalAttack();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getMagicalAttack()
	 */
	public int getMagicalAttack() {
		return baseAttributes.getMagicalAttack();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getPhysicalDefense()
	 */
	public int getPhysicalDefense() {
		return baseAttributes.getPhysicalDefense();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getMagicalDefense()
	 */
	public int getMagicalDefense() {
		return baseAttributes.getMagicalDefense();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getAttackSpeed()
	 */
	public int getAttackSpeed() {
		return baseAttributes.getAttackSpeed();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getCastSpeed()
	 */
	public int getCastSpeed() {
		return baseAttributes.getCastSpeed();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getAccuracy()
	 */
	public int getAccuracy() {
		return baseAttributes.getAccuracy();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getCriticalChance()
	 */
	public int getCriticalChance() {
		return baseAttributes.getCriticalChance();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getEvasionChance()
	 */
	public int getEvasionChance() {
		return baseAttributes.getEvasionChance();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getMoveSpeed()
	 */
	public int getMoveSpeed() {
		return baseAttributes.getMoveSpeed();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#getMaxWeigth()
	 */
	public int getMaxWeigth() {
		return baseAttributes.getMaxWeigth();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.world.character.CharacterBaseAttributes#canCraft()
	 */
	public boolean canCraft() {
		return baseAttributes.canCraft();
	}

	@Override
	public CharacterTemplateID getID() {
		return (CharacterTemplateID) super.getID();
	}
}
