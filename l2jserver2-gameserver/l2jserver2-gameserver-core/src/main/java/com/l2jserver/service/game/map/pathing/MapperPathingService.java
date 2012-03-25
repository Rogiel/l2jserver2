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
package com.l2jserver.service.game.map.pathing;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;

import javolution.io.Struct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.world.PositionableObject;
import com.l2jserver.model.world.character.event.CharacterMoveEvent;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.core.vfs.VFSService;
import com.l2jserver.service.game.character.CharacterService;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.event.TypedWorldListener;
import com.l2jserver.service.game.world.event.WorldEventDispatcherService;
import com.l2jserver.util.geometry.Coordinate;
import com.l2jserver.util.geometry.Point3D;

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
@Depends({ VFSService.class, CharacterService.class, WorldService.class })
public class MapperPathingService extends AbstractService implements
		PathingService {
	/**
	 * The database file for the pathing engine
	 */
	private static final java.nio.file.Path file = Paths.get("pathing.db");

	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The {@link WorldService} event dispatcher
	 */
	private final WorldEventDispatcherService eventDispatcher;
	/**
	 * The {@link VFSService} implementation
	 */
	private final VFSService vfsService;

	/**
	 * The database channel, will remain open until service is stopped.
	 */
	private SeekableByteChannel channel;

	/**
	 * Creates a new instance
	 * 
	 * @param eventDispatcher
	 *            the world event dispatcher
	 * @param vfsService
	 *            the VFS service implementation
	 */
	@Inject
	public MapperPathingService(WorldEventDispatcherService eventDispatcher,
			VFSService vfsService) {
		this.eventDispatcher = eventDispatcher;
		this.vfsService = vfsService;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		try {
			final java.nio.file.Path dbFile = vfsService.resolveDataFile(file);
			this.channel = Files.newByteChannel(dbFile, CREATE, APPEND, WRITE);
		} catch (IOException e) {
			throw new ServiceStartException(
					"Could not open pathing database file", e);
		}
		eventDispatcher.addListener(new TypedWorldListener<CharacterMoveEvent>(
				CharacterMoveEvent.class) {
			@Override
			protected boolean dispatch(CharacterMoveEvent e) {
				final Point3D point = e.getPoint();
				final CoordinateStruct struct = CoordinateStruct
						.fromCoordinate(point.getCoordinate());
				try {
					channel.write(struct.getByteBuffer());
					// channel.force(true);
				} catch (IOException e1) {
					log.warn("Error writing pathing file!", e1);
				}
				return true;
			}
		});
	}

	@Override
	public Path findPath(PositionableObject object, Point3D point) {
		return new VoidPath(object.getPoint(), point);
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
		/**
		 * The x cord
		 */
		public final Signed32 x = new Signed32();
		/**
		 * The y cord
		 */
		public final Signed32 y = new Signed32();
		/**
		 * The z cord
		 */
		public final Signed32 z = new Signed32();

		/**
		 * Creates a new Coordinate Struct from another {@link Coordinate}
		 * object
		 * 
		 * @param coordinate
		 *            the coordinate object
		 * @return the {@link CoordinateStruct} object with the same coordinates
		 *         as <tt>coordinate</tt>
		 */
		public static CoordinateStruct fromCoordinate(Coordinate coordinate) {
			final CoordinateStruct struct = new CoordinateStruct();
			struct.x.set(coordinate.getX());
			struct.y.set(coordinate.getY());
			struct.z.set(coordinate.getZ());
			return struct;
		}
	}

	/**
	 * A pseudo-path. It will always trace the path in straight line.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class VoidPath implements Path {
		/**
		 * The source point
		 */
		private final Point3D source;
		/**
		 * The target point
		 */
		private final Point3D target;

		/**
		 * @param source
		 *            the source point
		 * @param target
		 *            the taget point
		 */
		public VoidPath(Point3D source, Point3D target) {
			this.source = source;
			this.target = target;
		}

		@Override
		public Iterator<Point3D> iterator() {
			return Arrays.asList(target).iterator();
		}

		@Override
		public double getDistance() {
			return source.getDistance(target);
		}

		@Override
		public Point3D getSource() {
			return source;
		}

		@Override
		public Point3D getTarget() {
			return target;
		}

		@Override
		public int getPointCount() {
			return 1;
		}
	}
}
