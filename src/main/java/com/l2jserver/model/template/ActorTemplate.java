package com.l2jserver.model.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.capability.Actor;
import com.l2jserver.model.world.character.CharacterBaseAttributes;

/**
 * Template for {@link Actor}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ActorTemplate<T extends Actor> extends
		AbstractTemplate<T> {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ActorTemplate.class);

	/**
	 * The actor race
	 */
	protected final Race race;
	/**
	 * The base attributes instance
	 */
	protected final CharacterBaseAttributes baseAttributes;

	public ActorTemplate(CharacterTemplateID id, Race race, int intelligence,
			int strength, int concentration, int mentality, int dexterity,
			int witness, int physicalAttack, int magicalAttack,
			int physicalDefense, int magicalDefense, int attackSpeed,
			int castSpeed, int accuracy, int criticalChance, int evasionChance,
			int moveSpeed, int maxWeigth, boolean craft) {
		super(id);
		this.race = race;
		baseAttributes = new CharacterBaseAttributes(intelligence, strength,
				concentration, mentality, dexterity, witness, physicalAttack,
				magicalAttack, physicalDefense, magicalDefense, attackSpeed,
				castSpeed, accuracy, criticalChance, evasionChance, moveSpeed,
				maxWeigth, craft);
	}

	@Override
	public T create() {
		log.debug("Creating a new Actor instance with template {}", this);
		final T actor = createInstance();

		return actor;
	}

	public abstract T createInstance();

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
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
}
