/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General public License for more details.
 *
 * You should have received a copy of the GNU General public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.template;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.jaxb.CharacterTemplateIDAdapter;

/**
 * The {@link L2Character} template. Each character instance is backed by an
 * template.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "character")
@XmlType(namespace = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterTemplate extends ActorTemplate<L2Character> {
	@XmlJavaTypeAdapter(CharacterTemplateIDAdapter.class)
	@XmlAttribute(name = "class")
	protected CharacterTemplateID id = null;

	@XmlElement(name = "stats")
	protected CharacterStatsMetadata stats = null;

	@XmlType(namespace = "character")
	protected static class CharacterStatsMetadata {
		@XmlAttribute(name = "level")
		protected int level = 0;
		@XmlAttribute(name = "crafter")
		protected boolean crafter = false;

		@XmlElement(name = "hp")
		protected Stat hp = null;
		@XmlElement(name = "mp")
		protected Stat mp = null;
		@XmlElement(name = "cp")
		protected Stat cp = null;

		@XmlType(namespace = "character")
		protected static class Stat {
			@XmlAttribute(name = "base")
			protected double base = 0;
			@XmlAttribute(name = "modifier")
			protected double modifier = 0;
			@XmlAttribute(name = "add")
			protected double add = 0;
		}

		@XmlElement(name = "attack")
		protected AttackMetadata attack = null;

		@XmlType(namespace = "character")
		protected static class AttackMetadata {
			@XmlAttribute(name = "evasion")
			protected int evasion = 0;
			@XmlAttribute(name = "critical")
			protected int critical = 0;
			@XmlAttribute(name = "accuracy")
			protected int accuracy = 0;

			@XmlElement(name = "physical")
			protected AttackValueMetadata physical = null;
			@XmlElement(name = "magical")
			protected AttackValueMetadata magical = null;

			@XmlType(namespace = "character")
			protected static class AttackValueMetadata {
				@XmlAttribute(name = "damage")
				protected double damage = 0;
				@XmlAttribute(name = "speed")
				protected double speed = 0;
			}
		}

		@XmlElement(name = "defense")
		protected DefenseMetadata defense = null;

		@XmlType(namespace = "character")
		protected static class DefenseMetadata {
			@XmlElement(name = "physical")
			protected DefenseValueMetadata physical = null;
			@XmlElement(name = "magical")
			protected DefenseValueMetadata magical = null;

			@XmlType(namespace = "character")
			protected static class DefenseValueMetadata {
				@XmlAttribute(name = "value")
				protected double value = 0;
			}
		}

		@XmlElement(name = "move")
		protected MoveMetadata move = null;

		@XmlType(namespace = "character")
		protected static class MoveMetadata {
			@XmlAttribute(name = "run")
			protected double run = 0;
			@XmlAttribute(name = "walk")
			protected double walk = 0;
		}

		@XmlElement(name = "base")
		protected BaseMetadata base = null;

		@XmlType(namespace = "character")
		protected static class BaseMetadata {
			@XmlAttribute(name = "int")
			protected int intelligence = 0;
			@XmlAttribute(name = "str")
			protected int strength = 0;
			@XmlAttribute(name = "con")
			protected int concentration = 0;
			@XmlAttribute(name = "men")
			protected int mentality = 0;
			@XmlAttribute(name = "dex")
			protected int dexterity = 0;
			@XmlAttribute(name = "wit")
			protected int witness = 0;
		}

		@XmlElement(name = "maxload")
		protected int maximumLoad = 0;
	}

	@XmlElement(name = "collision")
	protected CollitionMetadataContainer collision = null;

	@XmlType(namespace = "character")
	protected static class CollitionMetadataContainer {
		@XmlElement(name = "male")
		protected CollisionMetadata male = null;

		@XmlElement(name = "female")
		protected CollisionMetadata female = null;

		@XmlType(namespace = "character")
		protected static class CollisionMetadata {
			@XmlAttribute(name = "radius")
			protected double radius = 0;
			@XmlAttribute(name = "heigth")
			protected double height = 0;
		}
	}

	@Override
	protected L2Character createInstance() {
		return new L2Character(id);
		// character.setPosition(null); // TODO spawn location
		// return null;
	}

	/**
	 * @return the character Class
	 */
	public CharacterClass getCharacterClass() {
		return CharacterClass.fromID(id.getID());
	}

	/**
	 * @return the character race
	 */
	public CharacterRace getRace() {
		return getCharacterClass().race;
	}

	/**
	 * @return the minimum level
	 */
	public int getMinimumLevel() {
		if (stats == null)
			return 0;
		return stats.level;
	}

	/**
	 * @return true if class is crafter
	 */
	public boolean isCrafter() {
		if (stats == null)
			return false;
		return stats.crafter;
	}

	/**
	 * @return the base HP
	 */
	public double getBaseHP() {
		if (stats == null)
			return 0;
		if (stats.hp == null)
			return 0;
		return stats.hp.base;
	}

	/**
	 * @return the base HP modifier
	 */
	public double getBaseHPModifier() {
		if (stats == null)
			return 0;
		if (stats.hp == null)
			return 0;
		return stats.hp.modifier;
	}

	/**
	 * @return the base HP add
	 */
	public double getBaseHPAdd() {
		if (stats == null)
			return 0;
		if (stats.hp == null)
			return 0;
		return stats.hp.add;
	}

	/**
	 * @return the base MP
	 */
	public double getBaseMP() {
		if (stats == null)
			return 0;
		if (stats.mp == null)
			return 0;
		return stats.mp.base;
	}

	/**
	 * @return the base MP modifier
	 */
	public double getBaseMPModifier() {
		if (stats == null)
			return 0;
		if (stats.mp == null)
			return 0;
		return stats.mp.modifier;
	}

	/**
	 * @return the base MP add
	 */
	public double getBaseMPAdd() {
		if (stats == null)
			return 0;
		if (stats.mp == null)
			return 0;
		return stats.mp.add;
	}

	/**
	 * @return the base CP
	 */
	public double getBaseCP() {
		if (stats == null)
			return 0;
		if (stats.cp == null)
			return 0;
		return stats.cp.base;
	}

	/**
	 * @return the base CP modifier
	 */
	public double getBaseCPModifier() {
		if (stats == null)
			return 0;
		if (stats.cp == null)
			return 0;
		return stats.cp.modifier;
	}

	/**
	 * @return the base CP add
	 */
	public double getBaseCPAdd() {
		if (stats == null)
			return 0;
		if (stats.cp == null)
			return 0;
		return stats.cp.add;
	}

	/**
	 * @return the evasion
	 */
	public int getBaseEvasion() {
		if (stats == null)
			return 0;
		if (stats.attack == null)
			return 0;
		return stats.attack.evasion;
	}

	/**
	 * @return the critical
	 */
	public int getBaseCritical() {
		if (stats == null)
			return 0;
		if (stats.attack == null)
			return 0;
		return stats.attack.critical;
	}

	/**
	 * @return the accuracy
	 */
	public int getBaseAccuracy() {
		if (stats == null)
			return 0;
		if (stats.attack == null)
			return 0;
		return stats.attack.accuracy;
	}

	/**
	 * @return the physical attack
	 */
	public double getBasePhysicalAttack() {
		if (stats == null)
			return 0;
		if (stats.attack == null)
			return 0;
		if (stats.attack.physical == null)
			return 0;
		return stats.attack.physical.damage;
	}

	/**
	 * @return the physical defense
	 */
	public double getBasePhysicalDefense() {
		if (stats == null)
			return 0;
		if (stats.defense == null)
			return 0;
		if (stats.defense.physical == null)
			return 0;
		return stats.defense.physical.value;
	}

	/**
	 * @return the physical attack speed
	 */
	public double getBasePhysicalAttackSpeed() {
		if (stats == null)
			return 0;
		if (stats.attack == null)
			return 0;
		if (stats.attack.physical == null)
			return 0;
		return stats.attack.physical.speed;
	}

	/**
	 * @return the magical attack
	 */
	public double getBaseMagicalAttack() {
		if (stats == null)
			return 0;
		if (stats.attack == null)
			return 0;
		if (stats.attack.magical == null)
			return 0;
		return stats.attack.magical.damage;
	}

	/**
	 * @return the magical attack
	 */
	public double getBaseMagicalDefense() {
		if (stats == null)
			return 0;
		if (stats.defense == null)
			return 0;
		if (stats.defense.magical == null)
			return 0;
		return stats.defense.magical.value;
	}

	/**
	 * @return the magical attack speed
	 */
	public double getBaseMagicalAttackSpeed() {
		if (stats == null)
			return 0;
		if (stats.attack == null)
			return 0;
		if (stats.attack.magical == null)
			return 0;
		return stats.attack.magical.speed;
	}

	/**
	 * @return the base run speed
	 */
	public double getBaseRunSpeed() {
		if (stats == null)
			return 0;
		if (stats.move == null)
			return 0;
		return stats.move.run;
	}

	/**
	 * @return the base walk speed
	 */
	public double getBaseWalkSpeed() {
		if (stats == null)
			return 0;
		if (stats.move == null)
			return 0;
		return stats.move.walk;
	}

	/**
	 * @return the intelligence
	 */
	public int getBaseIntelligence() {
		if (stats == null)
			return 0;
		if (stats.base == null)
			return 0;
		return stats.base.intelligence;
	}

	/**
	 * @return the strength
	 */
	public int getBaseStrength() {
		if (stats == null)
			return 0;
		if (stats.base == null)
			return 0;
		return stats.base.strength;
	}

	/**
	 * @return the concentration
	 */
	public int getBaseConcentration() {
		if (stats == null)
			return 0;
		if (stats.base == null)
			return 0;
		return stats.base.concentration;
	}

	/**
	 * @return the mentality
	 */
	public int getBaseMentality() {
		if (stats == null)
			return 0;
		if (stats.base == null)
			return 0;
		return stats.base.mentality;
	}

	/**
	 * @return the dexterity
	 */
	public int getBaseDexterity() {
		if (stats == null)
			return 0;
		if (stats.base == null)
			return 0;
		return stats.base.dexterity;
	}

	/**
	 * @return the witness
	 */
	public int getBaseWitness() {
		if (stats == null)
			return 0;
		if (stats.base == null)
			return 0;
		return stats.base.witness;
	}

	/**
	 * @return the witness
	 */
	public int getBaseMaximumLoad() {
		if (stats == null)
			return 0;
		return stats.maximumLoad;
	}

	/**
	 * @return the male collision radius
	 */
	public double getMaleCollisionRadius() {
		if (collision == null)
			return 0;
		if (collision.male == null)
			return 0;
		return collision.male.radius;
	}

	/**
	 * @return the male collision height
	 */
	public double getMaleCollisionHeight() {
		if (collision == null)
			return 0;
		if (collision.male == null)
			return 0;
		return collision.male.height;
	}

	/**
	 * @return the female collision radius
	 */
	public double getFemaleCollisionRadius() {
		if (collision == null)
			return 0;
		if (collision.female == null)
			return 0;
		return collision.female.radius;
	}

	/**
	 * @return the female collision height
	 */
	public double getFemaleCollisionHeight() {
		if (collision == null)
			return 0;
		if (collision.female == null)
			return 0;
		return collision.female.height;
	}

	@Override
	public CharacterTemplateID getID() {
		return id;
	}
}
