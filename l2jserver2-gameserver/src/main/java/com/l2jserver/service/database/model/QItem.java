package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;

/**
 * Maps <code>item</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QItem extends com.mysema.query.sql.RelationalPathBase<Item> {
	private static final long serialVersionUID = 1592270068;

	public static final QItem item = new QItem("item");

	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);

	public final NumberPath<Integer> coordX = createNumber("coord_x",
			Integer.class);

	public final NumberPath<Integer> coordY = createNumber("coord_y",
			Integer.class);

	public final NumberPath<Integer> coordZ = createNumber("coord_z",
			Integer.class);

	public final NumberPath<Long> count = createNumber("count",
			Long.class);

	public final NumberPath<Integer> itemId = createNumber("item_id",
			Integer.class);

	public final EnumPath<ItemLocation> location = createEnum("location",
			ItemLocation.class);

	public final EnumPath<InventoryPaperdoll> paperdoll = createEnum(
			"paperdoll", InventoryPaperdoll.class);

	public final NumberPath<Integer> templateId = createNumber("template_id",
			Integer.class);

	public final PrimaryKey<Item> primary = createPrimaryKey(itemId);

	public QItem(String variable) {
		super(Item.class, forVariable(variable), "null", "item");
	}

	public QItem(Path<? extends Item> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "item");
	}

	public QItem(PathMetadata<?> metadata) {
		super(Item.class, metadata, "null", "item");
	}

}
