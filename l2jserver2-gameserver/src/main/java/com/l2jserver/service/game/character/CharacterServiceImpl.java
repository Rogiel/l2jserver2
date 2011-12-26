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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.l2jserver.model.dao.CharacterDAO;
import com.l2jserver.model.dao.CharacterShortcutDAO;
import com.l2jserver.model.dao.ItemDAO;
import com.l2jserver.model.id.AccountID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.Actor.ActorState;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.L2Character.CharacterMoveType;
import com.l2jserver.model.world.NPC;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.model.world.character.event.CharacterEnterWorldEvent;
import com.l2jserver.model.world.character.event.CharacterLeaveWorldEvent;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.model.world.character.event.CharacterRunningEvent;
import com.l2jserver.model.world.character.event.CharacterStartMovingEvent;
import com.l2jserver.model.world.character.event.CharacterTargetDeselectedEvent;
import com.l2jserver.model.world.character.event.CharacterTargetSelectedEvent;
import com.l2jserver.model.world.character.event.CharacterWalkingEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.game.AttackService;
import com.l2jserver.service.game.npc.NPCService;
import com.l2jserver.service.game.npc.NotAttackableNPCServiceException;
import com.l2jserver.service.game.spawn.AlreadySpawnedServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnPointNotFoundServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.broadcast.BroadcastService;
import com.l2jserver.service.network.gameguard.GameGuardService;
import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.geometry.Point3D;

/**
 * Default implementation for {@link CharacterService}.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ WorldService.class, SpawnService.class, AttackService.class })
public class CharacterServiceImpl extends AbstractService implements
		CharacterService {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(CharacterServiceImpl.class);

	/**
	 * The {@link BroadcastService}
	 */
	private final BroadcastService broadcastService;
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The {@link SpawnService}
	 */
	private final SpawnService spawnService;
	/**
	 * The {@link NPCService}
	 */
	private final NPCService npcService;
	/**
	 * The {@link GameGuardService}
	 */
	private final GameGuardService ggService;

	/**
	 * The {@link CharacterDAO}
	 */
	private final CharacterDAO characterDao;
	/**
	 * The {@link ItemDAO}
	 */
	private final ItemDAO itemDao;
	/**
	 * The {@link CharacterShortcutDAO}
	 */
	private final CharacterShortcutDAO shortcutDao;

	/**
	 * The character ID provider
	 */
	private final CharacterIDProvider charIdProvider;
	/**
	 * The character template ID provider
	 */
	private final CharacterTemplateIDProvider charTemplateIdProvider;

	/**
	 * An map containing all currently online characters
	 */
	private final Map<CharacterID, L2Character> onlineCharacters = CollectionFactory
			.newMap();

	// /**
	// * The {@link AIService}
	// */
	// private final AIService aiService;

	/**
	 * @param broadcastService
	 *            the broadcast service
	 * @param eventDispatcher
	 *            the world service event dispatcher
	 * @param spawnService
	 *            the spawn service
	 * @param npcService
	 *            the npc service
	 * @param ggService
	 *            the gm service
	 * @param characterDao
	 *            the character DAO
	 * @param itemDao
	 *            the item DAO
	 * @param shortcutDao
	 *            the shortcut DAO
	 * @param charTemplateIdProvider
	 *            the character template id provider
	 * @param charIdProvider
	 *            the character id provider
	 */
	@Inject
	public CharacterServiceImpl(BroadcastService broadcastService,
			WorldEventDispatcher eventDispatcher, SpawnService spawnService,
			NPCService npcService, GameGuardService ggService,
			CharacterDAO characterDao, ItemDAO itemDao,
			CharacterShortcutDAO shortcutDao,
			CharacterTemplateIDProvider charTemplateIdProvider,
			CharacterIDProvider charIdProvider) {
		this.broadcastService = broadcastService;
		this.eventDispatcher = eventDispatcher;
		this.spawnService = spawnService;
		this.npcService = npcService;
		this.ggService = ggService;
		this.characterDao = characterDao;
		this.itemDao = itemDao;
		this.shortcutDao = shortcutDao;
		this.charTemplateIdProvider = charTemplateIdProvider;
		this.charIdProvider = charIdProvider;
	}

	@Override
	public L2Character create(String name, AccountID accountID, ActorSex sex,
			CharacterClass characterClass, CharacterHairStyle hairStyle,
			CharacterHairColor hairColor, CharacterFace face)
			throws CharacterInvalidNameException,
			CharacterInvalidAppearanceException,
			CharacterNameAlreadyExistsException {
		log.debug(
				"Requested creation of new character (name={}, sex={}, class={}, hairStyle={}, hairColor={}, face={})",
				new Object[] { name, sex, characterClass, hairStyle, hairColor,
						face });

		if ((name.length() < 1) || (name.length() > 16)) {
			throw new CharacterInvalidNameException();
		}
		// TODO check alphanumeric name
		if (sex == null || hairStyle == null || hairColor == null
				|| face == null)
			// if some of those attributes is null, something wrong happened.
			// Maybe we don't support the value sent!
			throw new CharacterInvalidAppearanceException();

		// existence check
		log.debug("Checking name existence {}", name);
		final L2Character existenceCheck = characterDao.selectByName(name);
		if (existenceCheck != null)
			throw new CharacterNameAlreadyExistsException();

		// create template id and lookup for the template instance
		final CharacterTemplateID templateId = charTemplateIdProvider
				.resolveID(characterClass.id);
		final CharacterTemplate template = templateId.getTemplate();
		log.debug("Creating character with template {}", template);

		// everything is fine, allocate a new ID
		final CharacterID id = charIdProvider.createID();
		// create the instance from the template
		final L2Character character = template.create();

		log.debug("Character object created, ID: {}, Object: {}", id, character);

		// set parameters
		character.setID(id);
		character.setName(name);
		character.setAccountID(accountID);

		character.setSex(sex);
		character.setPoint(Point3D.fromXYZA(146783, 25808, -2008, 0));

		character.getAppearance().setHairStyle(hairStyle);
		character.getAppearance().setHairColor(hairColor);
		character.getAppearance().setFace(face);

		if (characterDao.save(character) > 0)
			return character;
		return null;
	}

	@Override
	public void enterWorld(final L2Character character)
			throws SpawnPointNotFoundServiceException,
			AlreadySpawnedServiceException {
		Preconditions.checkNotNull(character, "character");

		log.debug("Character {} is entering world", character);

		// load character data
		character.getInventory().load(itemDao.selectByCharacter(character));
		character.getShortcuts().load(shortcutDao.selectByCharacter(character));

		onlineCharacters.put(character.getID(), character);
		// inventory interfere on calculators
		character.getStats().updateCalculator();

		// query client game guard -- if key is invalid, the connection will be
		// closed as soon as possible
		ggService.query(character);
		// start broadcasting -- will broadcast all nearby objects
		broadcastService.broadcast(character);

		// characters start in run mode
		try {
			run(character);
		} catch (CharacterAlreadyRunningServiceException e1) {
			// we can ignore this one
		}

		// spawn the player -- this will also dispatch a spawn event
		// here the object is registered in the world
		spawnService.spawn(character, null);

		// dispatch enter world event
		eventDispatcher.dispatch(new CharacterEnterWorldEvent(character));

		// update character
		characterDao.saveObjectsAsync(character);
	}

	@Override
	public void leaveWorld(L2Character character)
			throws NotSpawnedServiceException {
		Preconditions.checkNotNull(character, "character");

		log.debug("Character {} is leaving world", character);

		spawnService.unspawn(character);
		eventDispatcher.dispatch(new CharacterLeaveWorldEvent(character));
		onlineCharacters.remove(character.getID());

		characterDao.saveObjectsAsync(character);
	}

	@Override
	public boolean isOnline(L2Character character) {
		return onlineCharacters.containsKey(character.getID());
	}

	@Override
	public boolean isOnline(AccountID accountID) {
		for (final L2Character character : onlineCharacters.values()) {
			if (character.getAccountID().equals(accountID))
				return true;
		}
		return false;
	}

	@Override
	public void target(L2Character character, Actor target)
			throws CannotSetTargetServiceException {
		Preconditions.checkNotNull(character, "character");

		log.debug("Setting {} target to {}", character, target);

		if (target == null && character.getTargetID() != null) {
			// if is trying to select null (remove target) and the character has
			// an target, trigger an deselect
			final Actor oldTarget = character.getTarget();
			character.setTargetID(null);
			eventDispatcher.dispatch(new CharacterTargetDeselectedEvent(
					character, oldTarget));
		} else if (target != null && !target.getID().equals(character.getID())) {
			// if new target is not null and the current character target is
			// null or different, trigger the selection.
			if (character.getTargetID() != null) {
				// first deselect old target
				eventDispatcher.dispatch(new CharacterTargetDeselectedEvent(
						character, character.getTarget()));
			}
			// now select the new target
			character.setTargetID(target.getID());
			eventDispatcher.dispatch(new CharacterTargetSelectedEvent(
					character, target));
		} else {
			// this indicates an inconsistency: reset target and throws an
			// exception
			// this happens if tried deselect with no target
			character.setTargetID(null);
			throw new CannotSetTargetServiceException();
		}
	}

	@Override
	public void attack(L2Character character, Actor target)
			throws CannotSetTargetServiceException,
			ActorIsNotAttackableServiceException,
			NotAttackableNPCServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(target, "target");

		log.debug("Character {} is trying to attack {}", character, target);

		// check if this Actor can be attacked
		if (target instanceof NPC) {
			final NPC npc = (NPC) target;
			log.debug("{} is an NPC instance", npc);

			// first try to target this, if it is not already
			if (!npc.getID().equals(character.getTargetID())) {
				log.debug("{} is not targetted by {}", npc, character);
				target(character, target);
			}

			// now attack the npc
			log.debug("Sending {} attack request to NPCService", character);
			npcService.attack(npc, character);
		} else {
			throw new ActorIsNotAttackableServiceException();
		}
		characterDao.saveObjectsAsync(character);
	}

	@Override
	public void jail(L2Character character, long time, String reason)
			throws CharacterInJailServiceException {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkArgument(time > 0, "time <= 0");
		Preconditions.checkNotNull(reason, "reason");
		// TODO implement jailing
		throw new CharacterInJailServiceException();
	}

	@Override
	public void unjail(L2Character character)
			throws CharacterNotInJailServiceException {
		Preconditions.checkNotNull(character, "character");
		// TODO implement jailing
		throw new CharacterNotInJailServiceException();
	}

	@Override
	public void move(L2Character character, Coordinate coordinate) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(coordinate, "coordinate");

		log.debug("{} is moving to {}", character, coordinate);

		// we don't set the character coordinate here, this will be done by
		// validation packets, sent by client

		character.setState(ActorState.MOVING);
		character.setTargetLocation(coordinate.toPoint());

		// dispatch the start moving event. BroadcastService will catch it and
		// notify the client.
		eventDispatcher.dispatch(new CharacterStartMovingEvent(character,
				coordinate.toPoint()));
		characterDao.saveObjectsAsync(character);
	}

	@Override
	public void validate(L2Character character, Point3D point) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(point, "point");
		// TODO implement position validation
	}

	@Override
	public void receivedValidation(L2Character character, Point3D point) {
		Preconditions.checkNotNull(character, "character");
		Preconditions.checkNotNull(point, "point");
		if (character.isTeleporting())
			// ignore while teleporting, for some reason the client sends a
			// validation just before teleport packet
			return;

		log.debug("{} client is validating its position to {}", character,
				point);

		final Point3D old = character.getPoint();
		character.setPoint(point);
		// BroadcastService will catch this event and update the knownlist
		eventDispatcher.dispatch(new CharacterMoveEvent(character, old));

		// target location is null if picking an item right after logging in and
		// has not moved the char.
		if (character.getTargetLocation() != null) {
			if (point.getCoordinate().equals(
					character.getTargetLocation().getCoordinate())) {
				character.setState(null);
				character.setTargetLocation(null);
				// TODO dispatch stop event
			}
		}

		characterDao.saveObjectsAsync(character);
	}

	@Override
	public void walk(L2Character character)
			throws CharacterAlreadyWalkingServiceException {
		Preconditions.checkNotNull(character, "character");
		// test if character is running
		if (character.getMoveType() == CharacterMoveType.WALK)
			throw new CharacterAlreadyWalkingServiceException();

		log.debug("{} move type is being set to WALK", character);

		// if running set mode to walk and broadcast packet
		character.setMoveType(CharacterMoveType.WALK);

		eventDispatcher.dispatch(new CharacterWalkingEvent(character));
	}

	@Override
	public void run(L2Character character)
			throws CharacterAlreadyRunningServiceException {
		Preconditions.checkNotNull(character, "character");
		// test if character is walking
		if (character.getMoveType() == CharacterMoveType.RUN)
			throw new CharacterAlreadyRunningServiceException();

		log.debug("{} move type is being set to RUN", character);

		// if running walking mode to run and broadcast packet
		character.setMoveType(CharacterMoveType.RUN);

		eventDispatcher.dispatch(new CharacterRunningEvent(character));
	}
}
