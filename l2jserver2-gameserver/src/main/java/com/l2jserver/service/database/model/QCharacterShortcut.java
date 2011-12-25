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
	private static final long serialVersionUID = 1450964558;

	public static final QCharacterShortcut characterShortcut = new QCharacterShortcut(
			"character_shortcut");

	@ColumnSize(10)
	@ColumnAutoIncrement
	public final NumberPath<Integer> shortcutId = createNumber("shortcut_id",
			Integer.class);

	@ColumnSize(10)
	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> characterType = createNumber(
			"character_type", Integer.class);
	@ColumnSize(3)
	@ColumnNullable
	public final NumberPath<Integer> level = createNumber("level",
			Integer.class);
	@ColumnSize(10)
	public final NumberPath<Integer> objectId = createNumber("object_id",
			Integer.class);
	@ColumnSize(2)
	public final NumberPath<Integer> page = createNumber("page", Integer.class);
	@ColumnSize(2)
	public final NumberPath<Integer> slot = createNumber("slot", Integer.class);

	public final EnumPath<ShortcutType> type = createEnum("type",
			ShortcutType.class);

	public final PrimaryKey<Integer> primary = createPrimaryKey(shortcutId);

	public QCharacterShortcut(String variable) {
		super(Integer.class, forVariable(variable), "null",
				"character_shortcut");
	}

	public QCharacterShortcut(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null",
				"character_shortcut");
	}

	public QCharacterShortcut(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "character_shortcut");
	}

}
