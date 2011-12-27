package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;
import com.l2jserver.model.world.character.CharacterInventory.ItemLocation;
import com.l2jserver.service.database.ddl.annotation.ColumnDefault;
import com.l2jserver.service.database.ddl.annotation.ColumnNullable;
import com.l2jserver.service.database.ddl.annotation.ColumnSize;
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
public class QItem extends com.mysema.query.sql.RelationalPathBase<Integer> {
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default entity for {@link QItem}
	 */
	public static final QItem item = new QItem("item");

	/**
	 * Column: <code>item_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> itemId = createNumber("item_id",
			Integer.class);
	/**
	 * Column: <code>template_id</code>
	 */
	@ColumnSize(5)
	public final NumberPath<Integer> templateId = createNumber("template_id",
			Integer.class);
	/**
	 * Column: <code>character_id</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);

	/**
	 * Column: <code>coord_x</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> coordX = createNumber("coord_x",
			Integer.class);
	/**
	 * Column: <code>coord_y</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> coordY = createNumber("coord_y",
			Integer.class);
	/**
	 * Column: <code>coord_z</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> coordZ = createNumber("coord_z",
			Integer.class);
	/**
	 * Column: <code>count</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Long> count = createNumber("count", Long.class);

	/**
	 * Column: <code>location</code>
	 */
	@ColumnDefault("INVENTORY")
	public final EnumPath<ItemLocation> location = createEnum("location",
			ItemLocation.class);

	/**
	 * Column: <code>paperdoll</code>
	 */
	@ColumnNullable
	public final EnumPath<InventoryPaperdoll> paperdoll = createEnum(
			"paperdoll", InventoryPaperdoll.class);

	/**
	 * The entity primary key
	 */
	public final PrimaryKey<Integer> primary = createPrimaryKey(itemId);

	/**
	 * Creates a new instance
	 * @param variable the query variable
	 */
	public QItem(String variable) {
		super(Integer.class, forVariable(variable), "null", "item");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QItem(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "item");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QItem(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "item");
	}

}
