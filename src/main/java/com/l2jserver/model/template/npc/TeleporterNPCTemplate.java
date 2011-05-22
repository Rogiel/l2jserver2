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
package com.l2jserver.model.template.npc;

import java.util.List;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.server.NPCHtmlMessagePacket;
import com.l2jserver.model.id.template.NPCTemplateID;
import com.l2jserver.model.template.NPCTemplate;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.NPC;
import com.l2jserver.service.game.spawn.NotSpawnedServiceException;
import com.l2jserver.service.game.spawn.SpawnService;
import com.l2jserver.util.dimensional.Coordinate;
import com.l2jserver.util.exception.L2Exception;
import com.l2jserver.util.factory.CollectionFactory;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class TeleporterNPCTemplate extends NPCTemplate {
	// TOWNS
	public static final Coordinate GLUDIN = Coordinate.fromXYZ(-80684, 149770,
			-3040);
	public static final Coordinate GLUDIO = Coordinate.fromXYZ(-12787, 122779,
			-3112);
	public static final Coordinate SCHUTTGART = Coordinate.fromXYZ(87126,
			-143520, -1288);

	// VILLAGES
	public static final Coordinate DARK_ELF_VILLAGE = Coordinate.fromXYZ(9709,
			15566, -4568);
	public static final Coordinate DWARVEN_VILLAGE = Coordinate.fromXYZ(115120,
			-178224, -880);
	public static final Coordinate ELVEN_VILLAGE = Coordinate.fromXYZ(46951,
			51550, -2976);
	public static final Coordinate ORC_VILLAGE = Coordinate.fromXYZ(-45158,
			-112583, -240);
	public static final Coordinate KAMAEL_VILLAGE = Coordinate.fromXYZ(-117251,
			46771, 360);
	public static final Coordinate TALKING_ISLAND_VILLAGE = Coordinate.fromXYZ(
			-84141, 244623, -3728);
	public static final Coordinate TALKING_ISLAND_NORTHERN_TERRITORY = Coordinate
			.fromXYZ(-106696, 214691, -3424);

	// TERRITORIES
	public static final Coordinate ELVEN_RUINS = Coordinate.fromXYZ(-112367,
			234703, -3688);
	public static final Coordinate SINGING_WATERFALL = Coordinate.fromXYZ(
			-111728, 244330, -3448);
	public static final Coordinate OBELISK_OF_VICTORY = Coordinate.fromXYZ(
			-99586, 237637, -3568);
	public static final Coordinate IMMORTAL_PLATEAU = Coordinate.fromXYZ(
			-10983, -117484, -2464);
	public static final Coordinate IMMORTAL_PLATEAU_SOUTHERN_REGION = Coordinate
			.fromXYZ(-4190, -80040, -2696);
	public static final Coordinate FROZEN_WATERFALL = Coordinate.fromXYZ(8652,
			-139941, -1144);
	public static final Coordinate CAVE_OF_TRIALS = Coordinate.fromXYZ(9340,
			-112509, -2536);

	@Inject
	protected SpawnService spawnService;

	/**
	 * The list of all possible teleportation locations
	 */
	protected final List<TeleportationMetadata> locations = CollectionFactory
			.newList();

	/**
	 * Creates a new instance of this template
	 * 
	 * @param id
	 *            the template id
	 */
	protected TeleporterNPCTemplate(NPCTemplateID id) {
		super(id);
	}

	@Override
	public void action(NPC npc, L2Character character, String... args)
			throws L2Exception {
		talk(npc, character, args);
	}

	public void talk(NPC npc, L2Character character, String... args)
			throws NotSpawnedServiceException {
		final Lineage2Connection conn = networkService.discover(character
				.getID());
		if (args.length == 0) {
			final HtmlTemplate template = new HtmlTemplate() {
				@Override
				protected void build(MarkupTag body) {
					body.textcode(556).br().br();
					// TODO this should not be hard coded!
					int i = 0;
					for (final TeleportationMetadata location : locations) {
						body.addLink(
								location.name + " - " + location.price
										+ " Adena",
								"npc_${npcid}_teleport " + i++).br();
					}
				}
			};
			template.register("npcid", String.valueOf(npc.getID().getID()));
			conn.write(new NPCHtmlMessagePacket(npc, template));
		} else if (args[0].equals("teleport")) {
			final int location = Integer.parseInt(args[1]);
			final TeleportationMetadata metadata = locations.get(location);
			if (metadata == null) {
				conn.sendActionFailed();
				return;
			}
			spawnService.teleport(character, metadata.point);
		}
		conn.sendActionFailed();
	}

	protected void addLocation(String name, Coordinate coordinate, int price) {
		this.locations.add(new TeleportationMetadata(name, coordinate, price));
	}

	protected static class TeleportationMetadata {
		public final String name;
		public final Coordinate point;
		public final int price;

		public TeleportationMetadata(String name, Coordinate point, int price) {
			this.name = name;
			this.point = point;
			this.price = price;
		}
	}
}
