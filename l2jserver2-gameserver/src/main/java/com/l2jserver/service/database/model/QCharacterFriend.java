package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.id.FriendID;
import com.l2jserver.service.database.ddl.annotation.ColumnSize;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;

/**
 * Maps <code>character_friend</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QCharacterFriend extends
		com.mysema.query.sql.RelationalPathBase<FriendID> {
	private static final long serialVersionUID = 1488651942;

	public static final QCharacterFriend characterFriend = new QCharacterFriend(
			"character_friend");

	@ColumnSize(10)
	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);
	@ColumnSize(10)
	public final NumberPath<Integer> characterIdFriend = createNumber(
			"character_id_friend", Integer.class);

	public final PrimaryKey<FriendID> primary = createPrimaryKey(characterId,
			characterIdFriend);

	public QCharacterFriend(String variable) {
		super(FriendID.class, forVariable(variable), "null", "character_friend");
	}

	public QCharacterFriend(Path<? extends FriendID> entity) {
		super(entity.getType(), entity.getMetadata(), "null",
				"character_friend");
	}

	public QCharacterFriend(PathMetadata<?> metadata) {
		super(FriendID.class, metadata, "null", "character_friend");
	}

}
