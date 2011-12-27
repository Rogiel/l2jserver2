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
package com.l2jserver.service.database.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.model.id.CharacterShortcutID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.ItemID;
import com.l2jserver.model.id.object.provider.CharacterIDProvider;
import com.l2jserver.model.id.object.provider.ItemIDProvider;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.template.item.ItemTemplate;
import com.l2jserver.model.world.Item;
import com.l2jserver.service.database.dao.AbstractMapper;
import com.l2jserver.service.database.dao.DatabaseRow;
import com.l2jserver.service.database.dao.PrimaryKeyMapper;
import com.l2jserver.service.database.dao.WritableDatabaseRow;
import com.l2jserver.service.database.model.QItem;
import com.l2jserver.util.geometry.Coordinate;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class ItemMapper extends AbstractMapper<Item, Integer, ItemID, QItem> {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The primary key mapper
	 */
	private final PrimaryKeyMapper<ItemID, Integer> idMapper = new PrimaryKeyMapper<ItemID, Integer>() {
		@Override
		public ItemID createID(Integer raw) {
			return idProvider.resolveID(raw);
		}
	};

	/**
	 * The {@link CharacterShortcutID} provider
	 */
	private final ItemIDProvider idProvider;
	/**
	 * The {@link ItemTemplateID} provider
	 */
	private final ItemTemplateIDProvider templateIdProvider;

	/**
	 * The {@link CharacterID} provider
	 */
	private final CharacterIDProvider charIdProvider;

	/**
	 * @param idProvider
	 *            the {@link ItemID} provider
	 * @param templateIdProvider
	 *            the item template id provider
	 * @param charIdProvider
	 *            the character ID provider
	 */
	@Inject
	public ItemMapper(final ItemIDProvider idProvider,
			ItemTemplateIDProvider templateIdProvider,
			CharacterIDProvider charIdProvider) {
		this.idProvider = idProvider;
		this.templateIdProvider = templateIdProvider;
		this.charIdProvider = charIdProvider;
	}

	@Override
	public Item select(QItem e, DatabaseRow row) {
		final ItemID id = idProvider.resolveID(row.get(e.itemId));
		final ItemTemplateID templateId = templateIdProvider.resolveID(row
				.get(e.templateId));
		final ItemTemplate template = templateId.getTemplate();
		if (template == null) {
			log.warn("No template found for {} while loading {}", templateId,
					id);
			return null;
		}
		final Item item = template.create();

		item.setID(id);
		if (!row.isNull(e.characterId))
			item.setOwnerID(charIdProvider.resolveID(row.get(e.characterId)));
		if (!row.isNull(e.location))
			item.setLocation(row.get(e.location));
		if (!row.isNull(e.paperdoll))
			item.setPaperdoll(row.get(e.paperdoll));

		item.setCount(row.get(e.count));

		if (!row.isNull(e.coordX) && !row.isNull(e.coordY)
				&& !row.isNull(e.coordZ))
			item.setPosition(Coordinate.fromXYZ(row.get(e.coordX),
					row.get(e.coordY), row.get(e.coordZ)));

		return item;
	}

	@Override
	public void insert(QItem e, Item object, WritableDatabaseRow row) {
		update(e, object, row);
	}

	@Override
	public void update(QItem e, Item object, WritableDatabaseRow row) {
		row.set(e.itemId, object.getID().getID())
				.set(e.templateId, object.getTemplateID().getID())
				.set(e.characterId,
						(object.getOwnerID() != null ? object.getOwnerID()
								.getID() : null))
				.set(e.location, object.getLocation())
				.set(e.paperdoll, object.getPaperdoll())
				.set(e.count, object.getCount())
				.set(e.coordX,
						(object.getPoint() != null ? object.getPoint().getX()
								: null))
				.set(e.coordY,
						(object.getPoint() != null ? object.getPoint().getY()
								: null))
				.set(e.coordZ,
						(object.getPoint() != null ? object.getPoint().getZ()
								: null));
	}

	@Override
	public PrimaryKeyMapper<ItemID, Integer> getPrimaryKeyMapper() {
		return idMapper;
	}
}