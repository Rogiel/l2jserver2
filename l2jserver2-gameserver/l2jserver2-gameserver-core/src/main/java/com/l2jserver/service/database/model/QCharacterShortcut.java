package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.game.CharacterShortcut.ShortcutType;
import com.l2jserver.service.database.ddl.annotation.ColumnAutoIncrement;
import com.l2jserver.service.database.ddl.annotation.ColumnNullable;
import com.l2jserver.service.database.ddl.annotation.ColumnSize;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;

/**
 * Maps <code>character_shortcut</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QCharacterShortcut extends
		com.mysema.query.sql.RelationalPathBase<Integer> {
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default entity for {@link QCharacterShortcut}
	 */
	public static final QCharacterShortcut characterShortcut = new QCharacterShortcut(
			"character_shortcut");

	/**
	 * Column: <code>shortcut_id</code>
	 */
	@ColumnSize(10)
	@ColumnAutoIncrement
	public final NumberPath<Integer> shortcutId = createNumber("shortcut_id",
			Integer.class);

	/**
	 * Column: <code>character_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);
	/**
	 * Column: <code>character_type</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> characterType = createNumber(
			"character_type", Integer.class);
	/**
	 * Column: <code>level</code>
	 */
	@ColumnSize(3)
	@ColumnNullable
	public final NumberPath<Integer> level = createNumber("level",
			Integer.class);
	/**
	 * Column: <code>object_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> objectId = createNumber("object_id",
			Integer.class);
	/**
	 * Column: <code>page</code>
	 */
	@ColumnSize(2)
	public final NumberPath<Integer> page = createNumber("page", Integer.class);
	/**
	 * Column: <code>slot</code>
	 */
	@ColumnSize(2)
	public final NumberPath<Integer> slot = createNumber("slot", Integer.class);

	/**
	 * Column: <code>type</code>
	 */
	public final EnumPath<ShortcutType> type = createEnum("type",
			ShortcutType.class);

	/**
	 * The entity primary key
	 */
	public final PrimaryKey<Integer> primary = createPrimaryKey(shortcutId);

	/**
	 * Creates a new instance
	 * @param variable the query variable
	 */
	public QCharacterShortcut(String variable) {
		super(Integer.class, forVariable(variable), "null",
				"character_shortcut");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QCharacterShortcut(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null",
				"character_shortcut");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QCharacterShortcut(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "character_shortcut");
	}

}
