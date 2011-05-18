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
package script;

/**
 * The {@link AIInterest} defines what the AI is interested in doing.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public enum AIInterest {
	/**
	 * Idle
	 */
	INTEREST_IDLE,
	/**
	 * Will scan for attackable targets, if mob is aggressive or if it is
	 * aggrided.
	 */
	INTEREST_ACTIVE,
	/**
	 * Rest (sit until attacked)
	 */
	INTEREST_REST,
	/**
	 * Attack target (cast combat magic, go to target, combat), may be ignored,
	 * if target is locked on another character or a peacefull zone and so on
	 */
	INTEREST_ATTACK,
	/**
	 * Cast a spell, depending on the spell - may start or stop attacking
	 */
	INTEREST_CAST,
	/**
	 * Just move to another location
	 */
	INTEREST_MOVE_TO,
	/**
	 * Like move, but check target's movement and follow it
	 */
	INTEREST_FOLLOW,
	/**
	 * PickUp and item, (got to item, pickup it, become idle
	 */
	INTEREST_PICK_UP,
	/**
	 * Move to target, then interact
	 */
	INTEREST_INTERACT;
}