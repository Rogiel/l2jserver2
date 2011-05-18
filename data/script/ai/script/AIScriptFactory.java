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

import script.ai.CharacterAI;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.WorldObject;
import com.l2jserver.service.game.ai.AIScript;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.service.network.NetworkService;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class AIScriptFactory {
	private final WorldEventDispatcher eventDispatcher;
	private final NetworkService networkService;

	@Inject
	public AIScriptFactory(WorldEventDispatcher eventDispatcher,
			NetworkService networkService) {
		this.eventDispatcher = eventDispatcher;
		this.networkService = networkService;
	}

	public AIScript create(WorldObject object) {
		if (object instanceof L2Character) {
			final Lineage2Connection conn = networkService
					.discover((CharacterID) object.getID());
			return new CharacterAI((L2Character) object, conn, eventDispatcher);
		}
		return null;
	}
}
