package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.service.database.sql.ddl.annotation.ColumnSize;
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
	private static final long serialVersionUID = 1592083511;

	public static final QClan clan = new QClan("clan");

	@ColumnSize(10)
	public final NumberPath<Integer> clanId = createNumber("clan_id",
			Integer.class);
	@ColumnSize(10)
	public final NumberPath<Integer> characterIdLeader = createNumber(
			"character_id_leader", Integer.class);

	public final PrimaryKey<Integer> primary = createPrimaryKey(clanId);

	public QClan(String variable) {
		super(Integer.class, forVariable(variable), "null", "clan");
	}

	public QClan(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "clan");
	}

	public QClan(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "clan");
	}

}
