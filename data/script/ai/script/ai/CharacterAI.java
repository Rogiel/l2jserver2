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
package script.ai;

import script.AIInterest;

import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.ActorMovementPacket;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Positionable;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.service.game.ai.AIScript;
import com.l2jserver.service.game.ai.script.AttackAIScript;
import com.l2jserver.service.game.ai.script.WalkingAIScript;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.util.dimensional.Coordinate;

/**
 * This {@link AIScript} is for {@link L2Character} object instances
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterAI implements AIScript, WalkingAIScript, AttackAIScript {
	/**
	 * The {@link L2Character} being controlled by this AI
	 */
	private final L2Character character;
	/**
	 * The {@link Lineage2Connection} instance for this character
	 */
	private final Lineage2Connection conn;
	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;
	/**
	 * The AI interest
	 */
	private AIInterest interest;

	// walking
	/**
	 * Walking destination coordinate
	 */
	private Coordinate coordinate;

	public CharacterAI(L2Character character, Lineage2Connection conn,
			WorldEventDispatcher eventDispatcher) {
		this.character = character;
		this.conn = conn;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public void start() {
		// TODO implement listener
	}

	@Override
	public void run(double time) {
		if (interest == AIInterest.INTEREST_IDLE)
			return;

		switch (interest) {
		case INTEREST_MOVE_TO:
			final Coordinate source = character.getPosition();
			character.setPosition(coordinate);
			conn.write(new ActorMovementPacket(character, source));
			eventDispatcher.dispatch(new CharacterMoveEvent(character,
					coordinate.toPoint()));
			// double speed = character.getAttributes().getMoveSpeed();
			// double move = time * speed;
			// // Calculate movement angles needed
			// final double distance = coordinate.getDistance(character
			// .getPosition());
			// final int dy = coordinate.getY() - character.getPoint().getY();
			// final int dx = coordinate.getX() - character.getPoint().getX();
			//
			// double sin = dy / distance;
			// double cos = dx / distance;
			//
			// double angleTarget = Math.toDegrees(Math.atan2(sin, cos));
			// if (angleTarget < 0)
			// angleTarget = 360 + angleTarget;
			// final int angle = (int) (angleTarget * 182.044444444);
			this.interest = AIInterest.INTEREST_IDLE;
			break;
		default:
			break;
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void walk(Coordinate coordinate) {
		this.interest = AIInterest.INTEREST_MOVE_TO;
		this.coordinate = coordinate;
	}

	@Override
	public void follow(Positionable positionable) {
	}

	@Override
	public void attack(Attackable target) {
	}
}
