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
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default entity for {@link QCharacterFriend}
	 */
	public static final QCharacterFriend characterFriend = new QCharacterFriend(
			"character_friend");

	/**
	 * Column: <code>character_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);
	/**
	 * Column: <code>character_id_friend</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> characterIdFriend = createNumber(
			"character_id_friend", Integer.class);

	/**
	 * The entity primary key
	 */
	public final PrimaryKey<FriendID> primary = createPrimaryKey(characterId,
			characterIdFriend);

	/**
	 * Creates a new instance
	 * @param variable the query variable
	 */
	public QCharacterFriend(String variable) {
		super(FriendID.class, forVariable(variable), "null", "character_friend");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QCharacterFriend(Path<? extends FriendID> entity) {
		super(entity.getType(), entity.getMetadata(), "null",
				"character_friend");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QCharacterFriend(PathMetadata<?> metadata) {
		super(FriendID.class, metadata, "null", "character_friend");
	}

}
