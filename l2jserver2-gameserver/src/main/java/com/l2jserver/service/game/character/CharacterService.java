/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.game.character;

import com.l2jserver.model.game.CharacterFriend;
import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.service.Service;
import com.l2jserver.service.game.npc.NotAttackableNPCServiceException;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.geometry.Point3D;

/**
 * This service manages all in-game {@link L2Character characters}. Every move
 * or attack event must go through it.
 * <p>
 * This service is also used to create and delete characters. When deleting,
 * implementations should make sure they remove all traces (except for logs)
 * from the character, including {@link Item} and {@link CharacterFriend}
 * objects.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface CharacterService extends Service {
	/**
	 * 
	 * @param name
	 *            The name of the new character
	 * @param accountID
	 *            the account ID to be used
	 * @param sex
	 *            The sex of the new character
	 * @param characterClass
	 *            The class of the new character
	 * @param hairStyle
	 *            The new character hair style
	 * @param hairColor
	 *            The new character hair color
	 * @param face
	 *            The new character face
	 * @return the newly created {@link L2Character} object
	 * @throws CharacterInvalidNameException
	 *             if the character name contains invalid characters, too long
	 *             or not long enough
	 * @throws CharacterInvalidAppearanceException
	 *             if the appearance sent by the client is not valid
	 * @throws CharacterNameAlreadyExistsException
	 *             the character name is already being used
	 */
	L2Character create(String name, AccountID accountID, ActorSex sex,
			CharacterClass characterClass, CharacterHairStyle hairStyle,
			CharacterHairColor hairColor, CharacterFace face)
			throws CharacterInvalidNameException,
			CharacterInvalidAppearanceException,
			CharacterNameAlreadyExistsException;

	/**
	 * Perform all operations required to this character join the world
	 * 
	 * @param character
	 *            the character
	 * @throws SpawnPointNotFoundServiceException
	 *             if the character does not have an spawn point defined
	 * @throws AlreadySpawnedServiceException
	 *             if the character is already spawned in the world
	 */
	void enterWorld(L2Character character)
			throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException;

	/**
	 * Perform all operations required to this character leave the world
	 * 
	 * @param character
	 *            the character
	 * @throws NotSpawnedServiceException
	 *             if the object is not spawned in the world
	 */
	void leaveWorld(L2Character character) throws NotSpawnedServiceException;

	/**
	 * Set the target of this <tt>character</tt>
	 * 
	 * @param character
	 *            the character
	 * @param actor
	 *            the targeted actor
	 * @throws CannotSetTargetServiceException
	 *             if target cannot be set
	 */
	void target(L2Character character, Actor actor)
			throws CannotSetTargetServiceException;

	/**
	 * Attacks with the given <tt>character</tt> the <tt>target</tt>
	 * 
	 * @param character
	 *            the character
	 * @param target
	 *            the target
	 * @throws CannotSetTargetServiceException
	 *             if target cannot be set
	 * @throws ActorIsNotAttackableServiceException
	 *             if the target is not attackable
	 * @throws NotAttackableNPCServiceException
	 *             if the actor is not attackable
	 */
	void attack(L2Character character, Actor target)
			throws CannotSetTargetServiceException,
			ActorIsNotAttackableServiceException,
			NotAttackableNPCServiceException;

	/**
	 * Jails the given <tt>character</tt> for <tt>time</tt> seconds.
	 * 
	 * @param character
	 *            the character
	 * @param time
	 *            the jail time, in seconds
	 * @param reason
	 *            the jail reason
	 * @throws CharacterInJailServiceException
	 *             if the character is already in jail
	 */
	void jail(L2Character character, long time, String reason)
			throws CharacterInJailServiceException;

	/**
	 * Unjails the given <tt>character</tt>
	 * 
	 * @param character
	 *            the character to be unjailed
	 * @throws CharacterNotInJailServiceException
	 *             if character is not in jail
	 */
	void unjail(L2Character character)
			throws CharacterNotInJailServiceException;

	/**
	 * Moves the given <tt>character</tt> to <tt>coordinate</tt>
	 * 
	 * @param character
	 *            the character
	 * @param coordinate
	 *            the coordinate
	 */
	void move(L2Character character, Coordinate coordinate);

	/**
	 * Validates the position of an character
	 * 
	 * @param character
	 *            the character
	 * @param point
	 *            the validated point
	 */
	void validate(L2Character character, Point3D point);

	/**
	 * Called when received the validation of the position of an character
	 * 
	 * @param character
	 *            the character
	 * @param point
	 *            the validated point
	 */
	void receivedValidation(L2Character character, Point3D point);

	/**
	 * Set the character to walking mode
	 * 
	 * @param character
	 *            the character
	 * @throws CharacterAlreadyWalkingServiceException
	 *             if the character is already walking
	 */
	void walk(L2Character character)
			throws CharacterAlreadyWalkingServiceException;

	/**
	 * Set the character to run mode
	 * 
	 * @param character
	 *            the character
	 * @throws CharacterAlreadyRunningServiceException
	 *             if the character is already running
	 */
	void run(L2Character character)
			throws CharacterAlreadyRunningServiceException;
}
