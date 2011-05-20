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
package com.l2jserver.service.game.pathing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javolution.io.Struct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.game.CharacterService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.TypedWorldListener;
import com.l2jserver.service.game.world.event.WorldEventDispatcher;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.dimensional.Point;

/**
 * <h1>This implementation does not validate pathing!</h1>
 * <p>
 * This service does not handle pathing yet, instead in monitors client packets
 * that send a location validation. With those packets, a database will be
 * generated will all valid points in terrain. Later on, an system that allows
 * uploading this data will be implemented and allowing the pathing data to be
 * shared across all servers.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ CharacterService.class, WorldService.class })
public class MapperPathingService extends AbstractService implements
		PathingService {
	/**
	 * The database file for the pathing engine
	 */
	private static final File file = new File("data/pathing.db");

	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcher eventDispatcher;

	/**
	 * The database channel, will reamain open until service is stopped.
	 */
	private FileChannel channel;

	/**
	 * Creates a new instance
	 * 
	 * @param eventDispatcher
	 *            the world event dispatcher
	 */
	@Inject
	public MapperPathingService(WorldEventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		try {
			this.channel = new FileOutputStream(file).getChannel();
		} catch (FileNotFoundException e) {
			throw new ServiceStartException(
					"Could not open pathing database file", e);
		}
		eventDispatcher.addListener(new TypedWorldListener<CharacterMoveEvent>(
				CharacterMoveEvent.class) {
			@Override
			protected boolean dispatch(CharacterMoveEvent e) {
				final Point point = e.getPoint();
				final CoordinateStruct struct = CoordinateStruct
						.fromCoordinate(point.getCoordinate());
				try {
					channel.write(struct.getByteBuffer());
				} catch (IOException e1) {
					log.warn("Error writing pathing file!", e1);
				}
				return true;
			}
		});
	}

	@Override
	protected void doStop() throws ServiceStopException {
		try {
			this.channel.close();
		} catch (IOException e) {
			throw new ServiceStopException(
					"Could not close the pathing database file", e);
		} finally {
			this.channel = null;
		}
	}

	/**
	 * This is an Javolution {@link Struct} that represents a set of coordinate
	 * stored in the database file
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public static class CoordinateStruct extends Struct {
		public final Signed32 x = new Signed32();
		public final Signed32 y = new Signed32();
		public final Signed32 z = new Signed32();

		public static CoordinateStruct fromCoordinate(Coordinate coordinate) {
			final CoordinateStruct struct = new CoordinateStruct();
			struct.x.set(coordinate.getX());
			struct.y.set(coordinate.getY());
			struct.z.set(coordinate.getZ());
			return struct;
		}
	}
}
