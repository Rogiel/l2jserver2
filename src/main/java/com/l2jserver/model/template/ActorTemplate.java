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
package com.l2jserver.model.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.l2jserver.model.id.template.ActorTemplateID;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.actor.ActorAttributes;
import com.l2jserver.model.world.capability.Actor;

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
	protected ActorBaseAttributes attributes = new ActorBaseAttributes();

	public ActorTemplate(ActorTemplateID<?> id, Race race) {
		super(id);
		this.race = race;
	}

	@Override
	public T create() {
		log.debug("Creating a new Actor instance with template {}", this);
		final T actor = createInstance();

		return actor;
	}

	protected abstract T createInstance();

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @return the baseAttributes
	 */
	public ActorBaseAttributes getBaseAttributes() {
		return attributes;
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getIntelligence()
	 */
	public int getIntelligence() {
		return attributes.getIntelligence();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getStrength()
	 */
	public int getStrength() {
		return attributes.getStrength();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getConcentration()
	 */
	public int getConcentration() {
		return attributes.getConcentration();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getMentality()
	 */
	public int getMentality() {
		return attributes.getMentality();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getDexterity()
	 */
	public int getDextry() {
		return attributes.getDexterity();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getWitness()
	 */
	public int getWitness() {
		return attributes.getWitness();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getPhysicalAttack()
	 */
	public int getPhysicalAttack() {
		return attributes.getPhysicalAttack();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getMagicalAttack()
	 */
	public int getMagicalAttack() {
		return attributes.getMagicalAttack();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getPhysicalDefense()
	 */
	public int getPhysicalDefense() {
		return attributes.getPhysicalDefense();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getMagicalDefense()
	 */
	public int getMagicalDefense() {
		return attributes.getMagicalDefense();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getAttackSpeed()
	 */
	public int getAttackSpeed() {
		return attributes.getAttackSpeed();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getCastSpeed()
	 */
	public int getCastSpeed() {
		return attributes.getCastSpeed();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getAccuracy()
	 */
	public int getAccuracy() {
		return attributes.getAccuracy();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getCriticalChance()
	 */
	public int getCriticalChance() {
		return attributes.getCriticalChance();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getEvasionChance()
	 */
	public int getEvasionChance() {
		return attributes.getEvasionChance();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getMoveSpeed()
	 */
	public double getMoveSpeed() {
		return attributes.getMoveSpeed();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getMaxWeigth()
	 */
	public int getMaxWeigth() {
		return attributes.getMaxWeigth();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#canCraft()
	 */
	public boolean canCraft() {
		return attributes.canCraft();
	}

	/**
	 * Defines the attributes of an character
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public class ActorBaseAttributes implements ActorAttributes {
		/**
		 * The character intelligence
		 */
		public int intelligence;
		/**
		 * The character strength
		 */
		public int strength;
		/**
		 * The character concentration
		 */
		public int concentration;
		/**
		 * The character mentality power
		 */
		public int mentality;
		/**
		 * The character dexterity
		 */
		public int dexterity;
		/**
		 * The character witness
		 */
		public int witness;

		/**
		 * The default physical attack
		 */
		public int physicalAttack;
		/**
		 * The default magical attack
		 */
		public int magicalAttack;
		/**
		 * The physical defense
		 */
		public int physicalDefense;
		/**
		 * The magical defense
		 */
		public int magicalDefense;

		/**
		 * The physical attack speed
		 */
		public int attackSpeed;
		/**
		 * The "magical attack speed" (aka cast speed)
		 */
		public int castSpeed;

		/**
		 * The character accuracy
		 */
		public int accuracy;
		/**
		 * Chance of issuing an critical attack
		 */
		public int criticalChance;
		/**
		 * Chance of avoiding an attack
		 */
		public int evasionChance;
		/**
		 * The character's movement speed
		 */
		public float moveSpeed;
		/**
		 * The maximum weigth in items to be carried in the inventory
		 */
		public int maxWeigth;
		/**
		 * If this character can craft
		 */
		public boolean craft;

		/**
		 * @return the intelligence
		 */
		@Override
		public int getIntelligence() {
			return intelligence;
		}

		/**
		 * @return the strength
		 */
		@Override
		public int getStrength() {
			return strength;
		}

		/**
		 * @return the concentration
		 */
		@Override
		public int getConcentration() {
			return concentration;
		}

		/**
		 * @return the mentality
		 */
		@Override
		public int getMentality() {
			return mentality;
		}

		/**
		 * @return the dexterity
		 */
		@Override
		public int getDexterity() {
			return dexterity;
		}

		/**
		 * @return the witness
		 */
		@Override
		public int getWitness() {
			return witness;
		}

		/**
		 * @return the physicalAttack
		 */
		@Override
		public int getPhysicalAttack() {
			return physicalAttack;
		}

		/**
		 * @return the magicalAttack
		 */
		@Override
		public int getMagicalAttack() {
			return magicalAttack;
		}

		/**
		 * @return the physicalDefense
		 */
		@Override
		public int getPhysicalDefense() {
			return physicalDefense;
		}

		/**
		 * @return the magicalDefense
		 */
		@Override
		public int getMagicalDefense() {
			return magicalDefense;
		}

		/**
		 * @return the attackSpeed
		 */
		@Override
		public int getAttackSpeed() {
			return attackSpeed;
		}

		/**
		 * @return the castSpeed
		 */
		@Override
		public int getCastSpeed() {
			return castSpeed;
		}

		/**
		 * @return the accuracy
		 */
		@Override
		public int getAccuracy() {
			return accuracy;
		}

		/**
		 * @return the criticalChance
		 */
		@Override
		public int getCriticalChance() {
			return criticalChance;
		}

		/**
		 * @return the evasionChance
		 */
		@Override
		public int getEvasionChance() {
			return evasionChance;
		}

		/**
		 * @return the moveSpeed
		 */
		@Override
		public double getMoveSpeed() {
			return moveSpeed;
		}

		/**
		 * @return the maxWeigth
		 */
		@Override
		public int getMaxWeigth() {
			return maxWeigth;
		}

		/**
		 * @return the craft
		 */
		@Override
		public boolean canCraft() {
			return craft;
		}
	}

}