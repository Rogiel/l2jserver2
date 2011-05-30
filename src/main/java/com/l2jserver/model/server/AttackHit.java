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
package com.l2jserver.model.server;

import java.util.Collections;
import java.util.List;

import com.l2jserver.model.world.Actor;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This class represents an attack hit.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class AttackHit {
	/**
	 * The actor attacking <tt>target</tt>
	 */
	private final Actor attacker;
	/**
	 * The actor being attacked by <tt>attacker</tt>
	 */
	private final Actor target;
	/**
	 * The damage dealt by this hit
	 */
	private double damage;
	/**
	 * This hit flags (i.e. critical)
	 */
	private List<AttackHitFlag> flags = CollectionFactory.newList();

	/**
	 * An enumeration containing all possible attack flags
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum AttackHitFlag {
		/**
		 * The hit used soulshot
		 */
		SOULSHOT((byte) 0x10),
		/**
		 * The attack dealt critical damage
		 */
		CRITICAL((byte) 0x20),
		/**
		 * The shield blocked this hit
		 */
		SHIELD_BLOCKED((byte) 0x40),
		/**
		 * The <tt>attacker</tt> missed the hit
		 */
		MISS((byte) 0x80);

		/**
		 * The byte value of the flag
		 */
		public final byte flag;

		AttackHitFlag(byte flag) {
			this.flag = flag;
		}
	}

	/**
	 * Creates a new instance
	 * 
	 * @param attacker
	 *            the actor attacking <tt>target</tt>
	 * @param target
	 *            the actor being attacked by <tt>attacker</tt>
	 * @param damage
	 *            the damage issued in this hit
	 */
	public AttackHit(Actor attacker, Actor target, double damage) {
		this.attacker = attacker;
		this.target = target;
		this.damage = damage;
	}

	/**
	 * Creates a new instance
	 * 
	 * @param attacker
	 *            the actor attacking <tt>target</tt>
	 * @param target
	 *            the actor being attacked by <tt>attacker</tt>
	 */
	public AttackHit(Actor attacker, Actor target) {
		this.attacker = attacker;
		this.target = target;
	}

	/**
	 * Creates a new instance
	 * 
	 * @param attacker
	 *            the actor attacking <tt>target</tt>
	 * @param target
	 *            the actor being attacked by <tt>attacker</tt>
	 * @param flags
	 *            the hit flags
	 */
	public AttackHit(Actor attacker, Actor target, AttackHitFlag... flags) {
		this.attacker = attacker;
		this.target = target;
		Collections.addAll(this.flags, flags);
	}

	/**
	 * @return the attacker
	 */
	public Actor getAttacker() {
		return attacker;
	}

	/**
	 * @return the target
	 */
	public Actor getTarget() {
		return target;
	}

	/**
	 * @return the damage
	 */
	public double getDamage() {
		return damage;
	}

	/**
	 * @return the flags
	 */
	public List<AttackHitFlag> getFlags() {
		return flags;
	}

	/**
	 * @return the flags as a byte value
	 */
	public byte getFlagsByte() {
		byte flags = 0;
		for (final AttackHitFlag flag : this.flags) {
			if (flag == AttackHitFlag.MISS)
				return AttackHitFlag.MISS.flag;
			flags |= flag.flag;
		}
		return flags;
	}
}
