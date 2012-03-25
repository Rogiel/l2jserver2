package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.service.database.ddl.annotation.ColumnSize;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;

/**
 * Maps <code>npc</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QNPC extends com.mysema.query.sql.RelationalPathBase<Integer> {
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default entity for {@link QNPC}
	 */
	public static final QNPC npc = new QNPC("npc");

	/**
	 * Column: <code>npc_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> npcId = createNumber("npc_id",
			Integer.class);
	/**
	 * Column: <code>npc_template_id</code>
	 */
	@ColumnSize(4)
	public final NumberPath<Integer> npcTemplateId = createNumber(
			"npc_template_id", Integer.class);

	/**
	 * Column: <code>hp</code>
	 */
	public final NumberPath<Double> hp = createNumber("hp", Double.class);
	/**
	 * Column: <code>mp</code>
	 */
	public final NumberPath<Double> mp = createNumber("mp", Double.class);

	/**
	 * Column: <code>point_x</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> pointX = createNumber("point_x",
			Integer.class);
	/**
	 * Column: <code>point_y</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> pointY = createNumber("point_y",
			Integer.class);
	/**
	 * Column: <code>point_z</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> pointZ = createNumber("point_z",
			Integer.class);
	/**
	 * Column: <code>point_angle</code>
	 */
	public final NumberPath<Double> pointAngle = createNumber("point_angle",
			Double.class);

	/**
	 * Column: <code>respawn_time</code>
	 */
	@ColumnSize(8)
	public final NumberPath<Long> respawnTime = createNumber("respawn_time",
			Long.class);

	/**
	 * The entity primary key
	 */
	public final PrimaryKey<Integer> primary = createPrimaryKey(npcId);

	/**
	 * Creates a new instance
	 * 
	 * @param variable
	 *            the query variable
	 */
	public QNPC(String variable) {
		super(Integer.class, forVariable(variable), "null", "npc");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QNPC(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "npc");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QNPC(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "npc");
	}

}
