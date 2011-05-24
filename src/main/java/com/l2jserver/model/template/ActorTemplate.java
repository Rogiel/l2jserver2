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
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.ActorAttributes;
import com.l2jserver.model.world.actor.stat.Stats;

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
	 * The movement speed multiplier
	 */
	protected double movementSpeedMultiplier = 1.0;
	/**
	 * The attack speed multiplier
	 */
	protected double attackSpeedMultiplier = 1.0;

	/**
	 * The Actor maximum HP
	 */
	protected double maxHP;
	/**
	 * The Actor maximum MP
	 */
	protected double maxMP;

	/**
	 * The base attributes instance
	 */
	protected ActorBaseAttributes attributes = new ActorBaseAttributes();

	public ActorTemplate(ActorTemplateID<?> id) {
		super(id);
	}

	@Override
	public T create() {
		log.debug("Creating a new Actor instance with template {}", this);
		final T actor = createInstance();

		return actor;
	}

	protected abstract T createInstance();
	
	public abstract Stats getTemplateStat();

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
	public double getPhysicalAttack() {
		return attributes.getPhysicalAttack();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getMagicalAttack()
	 */
	public double getMagicalAttack() {
		return attributes.getMagicalAttack();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getPhysicalDefense()
	 */
	public double getPhysicalDefense() {
		return attributes.getPhysicalDefense();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getMagicalDefense()
	 */
	public double getMagicalDefense() {
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
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getRunSpeed()
	 */
	public double getRunSpeed() {
		return attributes.getRunSpeed();
	}

	/**
	 * @return
	 * @see com.l2jserver.model.template.ActorBaseAttributes#getWalkSpeed()
	 */
	public double getWalkSpeed() {
		return attributes.getWalkSpeed();
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
	 * @return the movementSpeedMultiplier
	 */
	public double getMovementSpeedMultiplier() {
		return movementSpeedMultiplier;
	}

	/**
	 * @return the attackSpeedMultiplier
	 */
	public double getAttackSpeedMultiplier() {
		return attackSpeedMultiplier;
	}

	/**
	 * @return the max hp
	 */
	public double getMaxHP() {
		return maxHP;
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
		public double physicalAttack;
		/**
		 * The default magical attack
		 */
		public double magicalAttack;
		/**
		 * The physical defense
		 */
		public double physicalDefense;
		/**
		 * The magical defense
		 */
		public double magicalDefense;

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
		 * The character's run speed
		 */
		public double runSpeed;
		/**
		 * The character's walk speed
		 */
		public double walkSpeed;
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
		public double getPhysicalAttack() {
			return physicalAttack;
		}

		/**
		 * @return the magicalAttack
		 */
		@Override
		public double getMagicalAttack() {
			return magicalAttack;
		}

		/**
		 * @return the physicalDefense
		 */
		@Override
		public double getPhysicalDefense() {
			return physicalDefense;
		}

		/**
		 * @return the magicalDefense
		 */
		@Override
		public double getMagicalDefense() {
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
		public double getRunSpeed() {
			return runSpeed;
		}

		@Override
		public double getWalkSpeed() {
			return walkSpeed;
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