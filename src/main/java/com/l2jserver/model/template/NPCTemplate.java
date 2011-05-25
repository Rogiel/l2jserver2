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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.world.Actor.ActorSex;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.controller.NPCController;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.NPCTemplateIDAdapter;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "npc")
@XmlType(namespace = "npc", name = "npc")
@XmlAccessorType(XmlAccessType.FIELD)
public class NPCTemplate extends ActorTemplate<NPC> {
	@XmlAttribute(name = "id")
	@XmlJavaTypeAdapter(value = NPCTemplateIDAdapter.class)
	protected NPCTemplateID id = null;
	@XmlAttribute(name = "controller")
	protected Class<? extends NPCController> controller;

	@XmlElement(name = "info")
	protected NPCInformationMetadata info = null;

	@XmlType(namespace = "npc")
	protected static class NPCInformationMetadata {
		@XmlElement(name = "name")
		public NPCNameMetadata nameMetadata = null;

		@XmlType(namespace = "npc")
		protected static class NPCNameMetadata {
			@XmlValue
			protected String name = null;
			@XmlAttribute(name = "send")
			protected Boolean send = null;
			@XmlAttribute(name = "display")
			protected Boolean display = null;
		}

		@XmlElement(name = "title")
		protected NPCTitleMetadata titleMetadata = null;

		@XmlType(namespace = "npc")
		protected static class NPCTitleMetadata {
			@XmlValue
			protected String title = null;
			@XmlAttribute(name = "send")
			protected Boolean send = null;
		}

		@XmlElement(name = "level")
		protected int level = 0;
		@XmlElement(name = "sex")
		protected ActorSex sex = null;

		@XmlAttribute(name = "attackable")
		protected boolean attackable;
		@XmlAttribute(name = "targetable")
		protected boolean targetable;
		@XmlAttribute(name = "aggressive")
		protected boolean aggressive;

		@XmlElement(name = "stats")
		protected NPCStatsMetadata stats = null;

		@XmlType(namespace = "npc")
		protected static class NPCStatsMetadata {
			@XmlElement(name = "hp")
			protected Stat hp = null;
			@XmlElement(name = "mp")
			protected Stat mp = null;

			@XmlType(namespace = "npc")
			protected static class Stat {
				@XmlAttribute(name = "max")
				protected double max = 0;
				@XmlAttribute(name = "regen")
				protected double regen = 0;
			}

			@XmlElement(name = "attack")
			protected AttackMetadata attack = null;

			@XmlType(namespace = "npc")
			protected static class AttackMetadata {
				@XmlAttribute(name = "range")
				protected int range = 0;
				@XmlAttribute(name = "evasion")
				protected int evasion = 0;
				@XmlAttribute(name = "critical")
				protected int critical = 0;

				@XmlElement(name = "physical")
				protected AttackValueMetadata physical = null;
				@XmlElement(name = "magical")
				protected AttackValueMetadata magical = null;

				@XmlType(namespace = "npc")
				protected static class AttackValueMetadata {
					@XmlAttribute(name = "damage")
					protected double damage = 0;
					@XmlAttribute(name = "speed")
					protected double speed = 0;
				}
			}

			@XmlElement(name = "defense")
			protected DefenseMetadata defense = null;

			@XmlType(namespace = "npc")
			protected static class DefenseMetadata {
				@XmlElement(name = "physical")
				protected DefenseValueMetadata physical = null;
				@XmlElement(name = "magical")
				protected DefenseValueMetadata magical = null;

				@XmlType(namespace = "npc")
				protected static class DefenseValueMetadata {
					@XmlAttribute(name = "value")
					protected double value = 0;
				}
			}

			@XmlElement(name = "move")
			protected MoveMetadata move = null;

			@XmlType(namespace = "npc")
			protected static class MoveMetadata {
				@XmlAttribute(name = "run")
				protected double run = 0;
				@XmlAttribute(name = "walk")
				protected double walk = 0;
			}

			@XmlElement(name = "base")
			public BaseMetadata base = null;

			@XmlType(namespace = "npc")
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
		}

		@XmlElement(name = "experience")
		protected long experience = 0;
		@XmlElement(name = "sp")
		protected int sp = 0;

		@XmlElement(name = "item")
		protected ItemMetadata item = null;

		@XmlType(namespace = "npc")
		protected static class ItemMetadata {
			@XmlAttribute(name = "righthand")
			@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
			protected ItemTemplateID rightHand = null;
			@XmlAttribute(name = "lefthand")
			@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
			protected ItemTemplateID leftHand = null;
		}

		@XmlElement(name = "collision")
		protected CollisionMetadata collision = null;

		@XmlType(namespace = "npc")
		protected static class CollisionMetadata {
			@XmlAttribute(name = "radius")
			protected double radius = 0;
			@XmlAttribute(name = "heigth")
			protected double height = 0;
		}
	}

	@XmlElement(name = "ai")
	protected AIMetadata ai = null;

	@XmlType(namespace = "npc")
	protected static class AIMetadata {
		@XmlAttribute(name = "script")
		protected String script = null;
	}

	@XmlElement(name = "talk")
	protected TalkMetadata talk = null;

	@XmlType(namespace = "npc")
	protected static class TalkMetadata {
		@XmlAttribute(name = "default")
		protected String defaultChat = null;

		@XmlElement(name = "chat")
		protected List<Chat> chats = null;
	}

	@XmlType(namespace = "npc")
	public static class Chat {
		@XmlAttribute(name = "id")
		protected String id = null;
		@XmlValue
		@XmlCDATA
		protected String html = null;
	}

	@XmlElementWrapper(name = "droplist")
	@XmlElement(name = "item")
	protected List<DropItemMetadata> droplist = null;

	@XmlType(namespace = "npc")
	protected static class DropItemMetadata {
		@XmlAttribute(name = "id")
		@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
		protected ItemTemplateID item = null;
		@XmlAttribute(name = "min")
		protected int min = 0;
		@XmlAttribute(name = "max")
		protected int max = 0;

		@XmlAttribute(name = "category")
		protected DropCategory category = null;

		public enum DropCategory {
			KILL;
		}

		@XmlAttribute(name = "chance")
		protected int chance = 0;
	}

	@Override
	protected NPC createInstance() {
		return new NPC(this.id);
	}

	/**
	 * @return the controller class
	 */
	public Class<? extends NPCController> getControllerClass() {
		return controller;
	}

	public String getName() {
		if (info == null)
			return null;
		if (info.nameMetadata == null)
			return null;
		return info.nameMetadata.name;
	}

	public boolean getSendName() {
		if (info == null)
			return false;
		if (info.nameMetadata == null)
			return false;
		return info.nameMetadata.send;
	}

	public boolean getDisplayName() {
		if (info == null)
			return false;
		if (info.nameMetadata == null)
			return false;
		return info.nameMetadata.display;
	}

	public String getTitle() {
		if (info == null)
			return null;
		if (info.titleMetadata == null)
			return null;
		return info.titleMetadata.title;
	}

	public boolean getSendTitle() {
		if (info == null)
			return false;
		if (info.titleMetadata == null)
			return false;
		return info.titleMetadata.send;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		if (info == null)
			return -1;
		return info.level;
	}

	/**
	 * @return the sex
	 */
	public ActorSex getSex() {
		if (info == null)
			return null;
		return info.sex;
	}

	/**
	 * @return the attackable
	 */
	public boolean isAttackable() {
		if (info == null)
			return false;
		return info.attackable;
	}

	/**
	 * @return the targetable
	 */
	public boolean isTargetable() {
		if (info == null)
			return false;
		return info.targetable;
	}

	/**
	 * @return the aggressive
	 */
	public boolean isAggressive() {
		if (info == null)
			return false;
		return info.aggressive;
	}

	/**
	 * @return the experience
	 */
	public long getExperience() {
		if (info == null)
			return 0;
		return info.experience;
	}

	/**
	 * @return the sp
	 */
	public int getSP() {
		if (info == null)
			return 0;
		return info.sp;
	}

	public double getMaximumHP() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.hp == null)
			return 0;
		return info.stats.hp.max;
	}

	public double getHPRegeneration() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.hp == null)
			return 0;
		return info.stats.hp.regen;
	}

	public double getMaximumMP() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.mp == null)
			return 0;
		return info.stats.mp.max;
	}

	public double getMPRegeneration() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.mp == null)
			return 0;
		return info.stats.mp.regen;
	}

	/**
	 * @return the range
	 */
	public int getRange() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		return info.stats.attack.range;
	}

	/**
	 * @return the evasion
	 */
	public int getEvasion() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		return info.stats.attack.evasion;
	}

	/**
	 * @return the critical
	 */
	public int getCritical() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		return info.stats.attack.critical;
	}

	/**
	 * @return the physical attack
	 */
	public double getPhysicalAttack() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.physical == null)
			return 0;
		return info.stats.attack.physical.damage;
	}

	/**
	 * @return the physical defense
	 */
	public double getPhysicalDefense() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.defense == null)
			return 0;
		if (info.stats.defense.physical == null)
			return 0;
		return info.stats.defense.physical.value;
	}

	/**
	 * @return the physical attack speed
	 */
	public double getPhysicalAttackSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.physical == null)
			return 0;
		return info.stats.attack.physical.speed;
	}

	/**
	 * @return the magical attack
	 */
	public double getMagicalAttack() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.magical == null)
			return 0;
		return info.stats.attack.magical.damage;
	}

	/**
	 * @return the magical attack
	 */
	public double getMagicalDefense() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.defense == null)
			return 0;
		if (info.stats.defense.magical == null)
			return 0;
		return info.stats.defense.magical.value;
	}

	/**
	 * @return the magical attack speed
	 */
	public double getMagicalAttackSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.attack == null)
			return 0;
		if (info.stats.attack.magical == null)
			return 0;
		return info.stats.attack.magical.speed;
	}

	public double getRunSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.move == null)
			return 0;
		return info.stats.move.run;
	}

	public double getWalkSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.move == null)
			return 0;
		return info.stats.move.walk;
	}

	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.intelligence;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.strength;
	}

	/**
	 * @return the concentration
	 */
	public int getConcentration() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.concentration;
	}

	/**
	 * @return the mentality
	 */
	public int getMentality() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.mentality;
	}

	/**
	 * @return the dexterity
	 */
	public int getDexterity() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.dexterity;
	}

	/**
	 * @return the witness
	 */
	public int getWitness() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.base == null)
			return 0;
		return info.stats.base.witness;
	}

	public ItemTemplateID getRightHand() {
		if (info == null)
			return null;
		if (info.item == null)
			return null;
		return info.item.rightHand;
	}

	public ItemTemplateID getLeftHand() {
		if (info == null)
			return null;
		if (info.item == null)
			return null;
		return info.item.leftHand;
	}

	public double getCollisionRadius() {
		if (info == null)
			return 0;
		if (info.collision == null)
			return 0;
		return info.collision.radius;
	}

	public double getCollisionHeight() {
		if (info == null)
			return 0;
		if (info.collision == null)
			return 0;
		return info.collision.height;
	}

	public String getAIScriptName() {
		if (ai == null)
			return null;
		return ai.script;
	}

	public String getHTML(String id) {
		if (talk == null)
			return null;
		if (id == null)
			id = talk.defaultChat;
		for (final Chat chat : this.talk.chats) {
			if (chat.id.equals(id))
				return chat.html;
		}
		return null;
	}

	public String getDefaultHTML() {
		if (talk == null)
			return null;
		return getHTML(talk.defaultChat);
	}

	@Override
	public NPCTemplateID getID() {
		return id;
	}
}
