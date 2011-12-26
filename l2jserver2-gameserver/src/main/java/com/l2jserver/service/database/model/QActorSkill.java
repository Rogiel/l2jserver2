package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.game.Skill;
import com.l2jserver.service.database.ddl.annotation.ColumnSize;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.NumberPath;

/**
 * Maps <code>actor_skill</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QActorSkill extends RelationalPathBase<Skill> {
	private static final long serialVersionUID = -146336131;

	public static final QActorSkill actorSkill = new QActorSkill("actor_skill");

	@ColumnSize(10)
	public final NumberPath<Integer> actorId = createNumber("actor_id",
			Integer.class);
	@ColumnSize(6)
	public final NumberPath<Integer> skillId = createNumber("skill_id",
			Integer.class);

	@ColumnSize(4)
	public final NumberPath<Integer> level = createNumber("level",
			Integer.class);

	public final PrimaryKey<Skill> primary = createPrimaryKey(actorId, skillId);

	public QActorSkill(String variable) {
		super(Skill.class, forVariable(variable), "null", "actor_skill");
	}

	public QActorSkill(Path<? extends Skill> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "actor_skill");
	}

	public QActorSkill(PathMetadata<?> metadata) {
		super(Skill.class, metadata, "null", "actor_skill");
	}

}
