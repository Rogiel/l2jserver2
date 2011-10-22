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
package com.l2jserver.model.template.effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.inject.Inject;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.effect.AbstractEffect;
import com.l2jserver.model.world.actor.effect.Effect;
import com.l2jserver.service.game.spawn.CharacterAlreadyTeleportingServiceException;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.jaxb.CoordinateAdapter;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TeleportEffect")
public class TeleportEffectTemplate extends EffectTemplate {
	public enum SkillTeleportEffectLocation {
		TARGET, OFFSET_FROM_TARGET, POINT;
	}

	@XmlAttribute(name = "type", required = false)
	private SkillTeleportEffectLocation type = SkillTeleportEffectLocation.TARGET;
	@XmlElement(name = "point")
	@XmlJavaTypeAdapter(CoordinateAdapter.class)
	private Coordinate coordinate;

	/**
	 * Creates a new instance
	 */
	private TeleportEffectTemplate() {
		super();
	}

	@Override
	public Effect createEffect(Actor actor) {
		return new AbstractEffect<TeleportEffectTemplate>(this, actor) {
			@Inject
			private SpawnService spawnService;

			@Override
			public void applyEffect() throws NotSpawnedServiceException,
					CharacterAlreadyTeleportingServiceException {
				spawnService.teleport(actor, template.coordinate);
			}
		};
	}

	/**
	 * @return the teleport effect type
	 */
	public SkillTeleportEffectLocation getType() {
		return type;
	}
}
