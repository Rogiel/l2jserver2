package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.game.CharacterShortcut;
import com.l2jserver.model.game.CharacterShortcut.ShortcutType;
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
public class QCharacterShortcut extends com.mysema.query.sql.RelationalPathBase<CharacterShortcut> {
    private static final long serialVersionUID = 1450964558;

    public static final QCharacterShortcut characterShortcut = new QCharacterShortcut("character_shortcut");

    public final NumberPath<Integer> characterId = createNumber("character_id", Integer.class);

    public final NumberPath<Integer> characterType = createNumber("character_type", Integer.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final NumberPath<Integer> objectId = createNumber("object_id", Integer.class);

    public final NumberPath<Integer> page = createNumber("page", Integer.class);

    public final NumberPath<Integer> shortcutId = createNumber("shortcut_id", Integer.class);

    public final NumberPath<Integer> slot = createNumber("slot", Integer.class);

    public final EnumPath<ShortcutType> type = createEnum("type", ShortcutType.class);

    public final PrimaryKey<CharacterShortcut> primary = createPrimaryKey(shortcutId);

    public QCharacterShortcut(String variable) {
        super(CharacterShortcut.class, forVariable(variable), "null", "character_shortcut");
    }

    public QCharacterShortcut(Path<? extends CharacterShortcut> entity) {
        super(entity.getType(), entity.getMetadata(), "null", "character_shortcut");
    }

    public QCharacterShortcut(PathMetadata<?> metadata) {
        super(CharacterShortcut.class, metadata, "null", "character_shortcut");
    }

}

