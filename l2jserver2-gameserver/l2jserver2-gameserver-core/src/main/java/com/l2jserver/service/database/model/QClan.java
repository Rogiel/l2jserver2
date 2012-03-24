package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.service.database.ddl.annotation.ColumnSize;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;

/**
 * Maps <code>clan</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QClan extends com.mysema.query.sql.RelationalPathBase<Integer> {
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default entity for {@link QClan}
	 */
	public static final QClan clan = new QClan("clan");

	/**
	 * Column: <code>clan_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> clanId = createNumber("clan_id",
			Integer.class);
	/**
	 * Column: <code>character_id_leader</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> characterIdLeader = createNumber(
			"character_id_leader", Integer.class);

	/**
	 * The entity primary key
	 */
	public final PrimaryKey<Integer> primary = createPrimaryKey(clanId);

	/**
	 * Creates a new instance
	 * @param variable the query variable
	 */
	public QClan(String variable) {
		super(Integer.class, forVariable(variable), "null", "clan");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QClan(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "clan");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QClan(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "clan");
	}

}
