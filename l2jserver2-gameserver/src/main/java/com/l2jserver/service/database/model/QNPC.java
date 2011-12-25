package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.world.NPC;
import com.l2jserver.service.database.sql.ddl.annotation.ColumnSize;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;

/**
 * Maps <code>npc</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QNPC extends com.mysema.query.sql.RelationalPathBase<NPC> {
	private static final long serialVersionUID = 2129578208;

	public static final QNPC npc = new QNPC("npc");

	@ColumnSize(10)
	public final NumberPath<Integer> npcId = createNumber("npc_id",
			Integer.class);
	@ColumnSize(4)
	public final NumberPath<Integer> npcTemplateId = createNumber(
			"npc_template_id", Integer.class);

	public final NumberPath<Double> hp = createNumber("hp", Double.class);
	public final NumberPath<Double> mp = createNumber("mp", Double.class);

	public final NumberPath<Double> pointAngle = createNumber("point_angle",
			Double.class);
	@ColumnSize(10)
	public final NumberPath<Integer> pointX = createNumber("point_x",
			Integer.class);
	@ColumnSize(10)
	public final NumberPath<Integer> pointY = createNumber("point_y",
			Integer.class);
	@ColumnSize(10)
	public final NumberPath<Integer> pointZ = createNumber("point_z",
			Integer.class);

	@ColumnSize(8)
	public final NumberPath<Long> respawnTime = createNumber("respawn_time",
			Long.class);

	public final PrimaryKey<NPC> primary = createPrimaryKey(npcId);

	public QNPC(String variable) {
		super(NPC.class, forVariable(variable), "null", "npc");
	}

	public QNPC(Path<? extends NPC> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "npc");
	}

	public QNPC(PathMetadata<?> metadata) {
		super(NPC.class, metadata, "null", "npc");
	}

}
