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
package com.l2jserver.model.template.npc;

import java.util.Collection;
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

import com.l2jserver.model.game.Skill;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.id.template.SkillTemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.actor.ActorTemplate;
import com.l2jserver.model.template.npc.NPCTemplate.TalkMetadata.Chat;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.npc.NPCController;
import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.jaxb.ItemTemplateIDAdapter;
import com.l2jserver.util.jaxb.NPCTemplateIDAdapter;
import com.l2jserver.util.jaxb.SkillTemplateIDAdapter;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlRootElement(name = "npc", namespace = "http://schemas.l2jserver2.com/npc")
@XmlType(namespace = "http://schemas.l2jserver2.com/npc", name = "NPCType")
@XmlAccessorType(XmlAccessType.FIELD)
public class NPCTemplate extends ActorTemplate<NPC> {
	/**
	 * The template ID
	 */
	@XmlAttribute(name = "id", required = true)
	@XmlJavaTypeAdapter(value = NPCTemplateIDAdapter.class)
	protected NPCTemplateID id = null;
	/**
	 * The {@link NPC} controller class
	 */
	@XmlAttribute(name = "controller", required = true)
	protected Class<? extends NPCController> controller;

	/**
	 * The {@link NPC} information metadata
	 */
	@XmlElement(name = "info", required = true)
	protected NPCInformationMetadata info = null;

	/**
	 * The {@link NPC} information metadata model
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "NPCInfoType")
	protected static class NPCInformationMetadata {
		/**
		 * The {@link NPC} name metadata
		 */
		@XmlElement(name = "name", required = false)
		public NPCNameMetadata nameMetadata = null;

		/**
		 * Defines the {@link NPC}'s name information
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		@XmlType(name = "NPCNameType")
		protected static class NPCNameMetadata {
			/**
			 * The {@link NPC} name
			 */
			@XmlValue
			protected String name = null;
			/**
			 * Whether the {@link NPC} name should be sent to the client or not
			 */
			@XmlAttribute(name = "send")
			protected Boolean send = false;
			/**
			 * Whether the name should be displayed on the client or not
			 */
			@XmlAttribute(name = "display")
			protected Boolean display = false;
		}

		/**
		 * The {@link NPC} title metadata
		 */
		@XmlElement(name = "title", required = false)
		protected NPCTitleMetadata titleMetadata = null;

		/**
		 * Defines the {@link NPC}'s title information
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		@XmlType(name = "NPCTitleType")
		protected static class NPCTitleMetadata {
			/**
			 * The {@link NPC} title
			 */
			@XmlValue
			protected String title = null;
			/**
			 * Whether the {@link NPC} title should be sent to the client or not
			 */
			@XmlAttribute(name = "send")
			protected Boolean send = false;
		}

		/**
		 * The {@link NPC} level
		 */
		@XmlElement(name = "level", required = true)
		protected int level = 0;
		/**
		 * The {@link NPC} race
		 */
		@XmlElement(name = "race", required = false)
		protected NPCRace race = NPCRace.NONE;
		/**
		 * The {@link NPC} sex
		 */
		@XmlElement(name = "sex", required = false)
		protected ActorSex sex = null;

		/**
		 * Whether this {@link NPC} can be attacked or not
		 */
		@XmlAttribute(name = "attackable", required = false)
		protected boolean attackable = false;
		/**
		 * Whether this {@link NPC} can be targeted or not
		 */
		@XmlAttribute(name = "targetable", required = false)
		protected boolean targetable = false;
		/**
		 * Whether this {@link NPC} is aggressive
		 */
		@XmlAttribute(name = "aggressive", required = false)
		protected boolean aggressive = false;

		/**
		 * The {@link NPC} stats metadata, such as hp and mp
		 */
		@XmlElement(name = "stats", required = true)
		protected NPCStatsMetadata stats = null;

		/**
		 * Defines {@link NPC}'s stats, such as HP and MP
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 * 
		 */
		@XmlType(name = "NPCStatsType")
		protected static class NPCStatsMetadata {
			/**
			 * The {@link NPC} maximum HP
			 */
			@XmlElement(name = "hp", required = true)
			protected Stat hp = null;
			/**
			 * The {@link NPC} maximum MP
			 */
			@XmlElement(name = "mp", required = true)
			protected Stat mp = null;

			/**
			 * Defines an stat (HP or MP)
			 * 
			 * @author <a href="http://www.rogiel.com">Rogiel</a>
			 */
			@XmlType(name = "")
			protected static class Stat {
				/**
				 * The maximum value
				 */
				@XmlAttribute(name = "max", required = true)
				protected double max = 0;
				/**
				 * The regeneration speed
				 */
				@XmlAttribute(name = "regen", required = true)
				protected double regen = 0;
			}

			/**
			 * The {@link NPC} attack metadata
			 */
			@XmlElement(name = "attack", required = false)
			protected AttackMetadata attack = null;

			/**
			 * Defines {@link NPC}'s attacking metadata
			 * 
			 * @author <a href="http://www.rogiel.com">Rogiel</a>
			 */
			@XmlType(name = "NPCAttackType")
			protected static class AttackMetadata {
				/**
				 * The {@link NPC}'s attack range
				 */
				@XmlAttribute(name = "range", required = true)
				protected int range = 0;
				/**
				 * The {@link NPC}'s evasion
				 */
				@XmlAttribute(name = "evasion", required = true)
				protected int evasion = 0;
				/**
				 * The {@link NPC}'s attack critical chance
				 */
				@XmlAttribute(name = "critical", required = true)
				protected int critical = 0;

				/**
				 * The {@link NPC}'s attack physical damage
				 */
				@XmlElement(name = "physical", required = true)
				protected AttackValueMetadata physical = null;
				/**
				 * The {@link NPC}'s attack magical damage
				 */
				@XmlElement(name = "magical", required = true)
				protected AttackValueMetadata magical = null;

				/**
				 * Defines an attack value
				 * 
				 * @author <a href="http://www.rogiel.com">Rogiel</a>
				 */
				@XmlType(name = "")
				protected static class AttackValueMetadata {
					/**
					 * The damage dealt
					 */
					@XmlAttribute(name = "damage", required = true)
					protected double damage = 0;
					/**
					 * The attacking speed
					 */
					@XmlAttribute(name = "speed", required = true)
					protected double speed = 0;
				}
			}

			/**
			 * The {@link NPC} defense metadata
			 */
			@XmlElement(name = "defense", required = false)
			protected DefenseMetadata defense = null;

			/**
			 * Defines {@link NPC}'s defensive metadata
			 * 
			 * @author <a href="http://www.rogiel.com">Rogiel</a>
			 */
			@XmlType(name = "NPCDefenseType")
			protected static class DefenseMetadata {
				/**
				 * The {@link NPC}'s physical defense
				 */
				@XmlElement(name = "physical", required = true)
				protected DefenseValueMetadata physical = null;
				/**
				 * The {@link NPC}'s magical defense
				 */
				@XmlElement(name = "magical", required = true)
				protected DefenseValueMetadata magical = null;

				/**
				 * Defines an defense value
				 * 
				 * @author <a href="http://www.rogiel.com">Rogiel</a>
				 */
				@XmlType(name = "")
				protected static class DefenseValueMetadata {
					/**
					 * The defense value
					 */
					@XmlAttribute(name = "value", required = true)
					protected double value = 0;
				}
			}

			/**
			 * The {@link NPC} movement data
			 */
			@XmlElement(name = "move", required = false)
			protected MoveMetadata move = null;

			/**
			 * Defines {@link NPC} movement data
			 * 
			 * @author <a href="http://www.rogiel.com">Rogiel</a>
			 */
			@XmlType(name = "NPCMovementType")
			protected static class MoveMetadata {
				/**
				 * The run speed
				 */
				@XmlAttribute(name = "run", required = true)
				protected double run = 0;
				/**
				 * The walk speed
				 */
				@XmlAttribute(name = "walk", required = true)
				protected double walk = 0;
			}

			/**
			 * The {@link NPC} base stats
			 */
			@XmlElement(name = "base", required = true)
			public BaseMetadata base = null;

			/**
			 * Defines all base stats for {@link NPC} instances
			 * 
			 * @author <a href="http://www.rogiel.com">Rogiel</a>
			 */
			@XmlType(name = "NPCBaseStatsType")
			protected static class BaseMetadata {
				/**
				 * The {@link NPC} intelligence
				 */
				@XmlAttribute(name = "int", required = true)
				protected int intelligence = 0;
				/**
				 * The {@link NPC} strength
				 */
				@XmlAttribute(name = "str", required = true)
				protected int strength = 0;
				/**
				 * The {@link NPC} concentration
				 */
				@XmlAttribute(name = "con", required = true)
				protected int concentration = 0;
				/**
				 * The {@link NPC} mentality
				 */
				@XmlAttribute(name = "men", required = true)
				protected int mentality = 0;
				/**
				 * The {@link NPC} dexterity
				 */
				@XmlAttribute(name = "dex", required = true)
				protected int dexterity = 0;
				/**
				 * The {@link NPC} witness
				 */
				@XmlAttribute(name = "wit", required = true)
				protected int witness = 0;
			}
		}

		/**
		 * The {@link NPC} experience
		 */
		@XmlElement(name = "experience", required = true)
		protected long experience = 0;
		/**
		 * The {@link NPC} SP
		 */
		@XmlElement(name = "sp", required = true)
		protected int sp = 0;

		/**
		 * The {@link NPC} holding items
		 */
		@XmlElement(name = "item", required = false)
		protected ItemMetadata item = null;

		/**
		 * Describes what items the {@link NPC} is holding on its hand
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		@XmlType(name = "NPCItemsType")
		protected static class ItemMetadata {
			/**
			 * The {@link ItemTemplateID} on {@link NPC}'s right hand
			 */
			@XmlAttribute(name = "righthand", required = false)
			@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
			protected ItemTemplateID rightHand = null;
			/**
			 * The {@link ItemTemplateID} on {@link NPC}'s left hand
			 */
			@XmlAttribute(name = "lefthand", required = false)
			@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
			protected ItemTemplateID leftHand = null;
		}

		/**
		 * The {@link NPC} collision data
		 */
		@XmlElement(name = "collision", required = false)
		protected CollisionMetadata collision = null;

		/**
		 * Defines the {@link NPC} dimensions used for collisions
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		@XmlType(name = "NPCCollisionType")
		protected static class CollisionMetadata {
			/**
			 * The {@link NPC} radius
			 */
			@XmlAttribute(name = "radius", required = true)
			protected double radius = 0;
			/**
			 * The {@link NPC} height
			 */
			@XmlAttribute(name = "heigth", required = true)
			protected double height = 0;
		}
	}

	/**
	 * The {@link NPC} AI data
	 */
	@XmlElement(name = "ai", required = false)
	protected AIMetadata ai = null;

	/**
	 * Describes the {@link NPC} ai data
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "NPCAIType")
	protected static class AIMetadata {
		/**
		 * The ai script to be used
		 */
		@XmlAttribute(name = "script", required = true)
		protected String script = null;
	}

	/**
	 * Contains all {@link NPC} conversation HTML messages
	 */
	@XmlElement(name = "talk", required = false)
	protected TalkMetadata talk = null;

	/**
	 * Describes {@link NPC} talking capability as HTML messages
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "NPCTalkType")
	protected static class TalkMetadata {
		/**
		 * The default chat message
		 */
		@XmlAttribute(name = "default", required = true)
		protected String defaultChat = null;

		/**
		 * The list of {@link Chat} available
		 */
		@XmlElement(name = "chat", required = true)
		protected List<Chat> chats = null;

		/**
		 * Describes an "chat" message (an HTML page)
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		@XmlType(name = "")
		public static class Chat {
			/**
			 * The chat ID
			 */
			@XmlAttribute(name = "id", required = true)
			protected String id = null;
			/**
			 * The message content
			 */
			@XmlValue
			protected String html = null;
		}
	}

	/**
	 * The {@link NPC} drop list
	 */
	@XmlElementWrapper(name = "droplist", required = false)
	@XmlElement(name = "item", required = true)
	protected List<DropItemMetadata> droplist = null;

	/**
	 * Describes {@link NPC} dropping when it is killed
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "NPCDropType")
	protected static class DropItemMetadata {
		/**
		 * The item dropped
		 */
		@XmlAttribute(name = "id", required = true)
		@XmlJavaTypeAdapter(value = ItemTemplateIDAdapter.class)
		protected ItemTemplateID item = null;
		/**
		 * The minimum amount of items dropped
		 */
		@XmlAttribute(name = "min", required = true)
		protected int min = 0;
		/**
		 * The maximum amount of item dropped
		 */
		@XmlAttribute(name = "max", required = true)
		protected int max = 0;

		/**
		 * The drop category
		 */
		@XmlAttribute(name = "category", required = true)
		protected DropCategory category = null;

		/**
		 * The drop category
		 * 
		 * @author <a href="http://www.rogiel.com">Rogiel</a>
		 */
		@SuppressWarnings("javadoc")
		@XmlType(name = "NPCDropCategoryType")
		public enum DropCategory {
			DROP, SPOIL, UNK_1, UNK_2, UNK_3, UNK_4, UNK_5, UNK_6, UNK_7, UNK_8, UNK_9, UNK_10, UNK_11, UNK_12, UNK_13, UNK_14, UNK_15, UNK_16, UNK_17, UNK_18, UNK_19, UNK_20, UNK_21, UNK_22, UNK_23, UNK_24, UNK_25, UNK_26, UNK_27, UNK_28, UNK_29, UNK_30, UNK_31, UNK_32, UNK_33, UNK_34, UNK_35, UNK_36, UNK_100, UNK_101, UNK_102, UNK_200;
		}

		/**
		 * The drop chance
		 */
		@XmlAttribute(name = "chance", required = true)
		protected int chance = 0;
	}

	/**
	 * List of all available {@link NPC} skills
	 */
	@XmlElementWrapper(name = "skills", required = false)
	@XmlElement(name = "skill", required = true)
	protected List<SkillMetadata> skills = null;

	/**
	 * Describes an {@link NPC} skill
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	@XmlType(name = "NPCSkillType")
	protected static class SkillMetadata {
		/**
		 * The skill ID
		 */
		@XmlAttribute(name = "id", required = true)
		@XmlJavaTypeAdapter(value = SkillTemplateIDAdapter.class)
		protected SkillTemplateID skill = null;
		/**
		 * The skill level
		 */
		@XmlAttribute(name = "level", required = true)
		protected int level = 0;
	}

	@Override
	protected NPC createInstance() {
		final NPC npc = new NPC(this.id);

		// new npcs are full hp/mp
		npc.setHP(getMaximumHP());
		npc.setMP(getMaximumMP());

		// load skills
		final Collection<Skill> skills = CollectionFactory.newSet();
		for (final SkillMetadata metadata : this.skills) {
			final SkillTemplate template = metadata.skill.getTemplate();
			if (template == null)
				// FIXME this should thrown an exception!
				continue;
			final Skill skill = template.create(npc.getID());
			skill.setLevel(metadata.level);
			skills.add(skill);
		}
		npc.getSkills().load(skills);

		return npc;
	}

	/**
	 * @return the controller class
	 */
	public Class<? extends NPCController> getControllerClass() {
		return controller;
	}

	/**
	 * @return the {@link NPC} name
	 */
	public String getName() {
		if (info == null)
			return null;
		if (info.nameMetadata == null)
			return null;
		return info.nameMetadata.name;
	}

	/**
	 * @return true if the {@link NPC} name should be sent to the client
	 */
	public boolean getSendName() {
		if (info == null)
			return false;
		if (info.nameMetadata == null)
			return false;
		return info.nameMetadata.send;
	}

	/**
	 * @return true if the {@link NPC} name should be displayed on the client
	 */
	public boolean getDisplayName() {
		if (info == null)
			return false;
		if (info.nameMetadata == null)
			return false;
		return info.nameMetadata.display;
	}

	/**
	 * @return the npc title (if any)
	 */
	public String getTitle() {
		if (info == null)
			return null;
		if (info.titleMetadata == null)
			return null;
		return info.titleMetadata.title;
	}

	/**
	 * @return if the {@link NPC} title should be sent to the client
	 */
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
	 * @return the race
	 */
	public NPCRace getRace() {
		if (info == null)
			return NPCRace.NONE;
		return info.race;
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

	/**
	 * @return the {@link NPC}'s maximum HP
	 */
	public double getMaximumHP() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.hp == null)
			return 0;
		return info.stats.hp.max;
	}

	/**
	 * @return the {@link NPC}'s maximum HP regeneration
	 */
	public double getHPRegeneration() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.hp == null)
			return 0;
		return info.stats.hp.regen;
	}

	/**
	 * @return the {@link NPC}'s maximum MP
	 */
	public double getMaximumMP() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.mp == null)
			return 0;
		return info.stats.mp.max;
	}

	/**
	 * @return the {@link NPC}'s maximum MP regeneration
	 */
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

	/**
	 * @return the {@link NPC}'s run speed
	 */
	public double getRunSpeed() {
		if (info == null)
			return 0;
		if (info.stats == null)
			return 0;
		if (info.stats.move == null)
			return 0;
		return info.stats.move.run;
	}

	/**
	 * @return the {@link NPC}'s walk speed
	 */
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

	/**
	 * @return the item on {@link NPC}'s right hand
	 */
	public ItemTemplateID getRightHand() {
		if (info == null)
			return null;
		if (info.item == null)
			return null;
		return info.item.rightHand;
	}

	/**
	 * @return the item on {@link NPC}'s left hand
	 */
	public ItemTemplateID getLeftHand() {
		if (info == null)
			return null;
		if (info.item == null)
			return null;
		return info.item.leftHand;
	}

	/**
	 * @return the {@link NPC}'s collision radius
	 */
	public double getCollisionRadius() {
		if (info == null)
			return 0;
		if (info.collision == null)
			return 0;
		return info.collision.radius;
	}

	/**
	 * @return the {@link NPC}'s collision height
	 */
	public double getCollisionHeight() {
		if (info == null)
			return 0;
		if (info.collision == null)
			return 0;
		return info.collision.height;
	}

	/**
	 * @return the {@link NPC}'s AI script name
	 */
	public String getAIScriptName() {
		if (ai == null)
			return null;
		return ai.script;
	}

	/**
	 * @param id
	 *            the chat ID
	 * @return the {@link NPC}'s HTML chat by ID
	 */
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

	/**
	 * @return the {@link NPC}'s default chat, if any.
	 */
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
