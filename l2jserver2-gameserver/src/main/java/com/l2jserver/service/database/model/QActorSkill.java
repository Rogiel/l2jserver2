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
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default entity for {@link QActorSkill}
	 */
	public static final QActorSkill actorSkill = new QActorSkill("actor_skill");

	/**
	 * Column: <code>actor_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> actorId = createNumber("actor_id",
			Integer.class);
	/**
	 * Column: <code>skill_id</code>
	 */
	@ColumnSize(6)
	public final NumberPath<Integer> skillId = createNumber("skill_id",
			Integer.class);

	/**
	 * Column: <code>level</code>
	 */
	@ColumnSize(4)
	public final NumberPath<Integer> level = createNumber("level",
			Integer.class);

	/**
	 * The entity primary key
	 */
	public final PrimaryKey<Skill> primary = createPrimaryKey(actorId, skillId);

	/**
	 * Creates a new instance
	 * 
	 * @param variable
	 *            the query variable
	 */
	public QActorSkill(String variable) {
		super(Skill.class, forVariable(variable), "null", "actor_skill");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QActorSkill(Path<? extends Skill> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "actor_skill");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QActorSkill(PathMetadata<?> metadata) {
		super(Skill.class, metadata, "null", "actor_skill");
	}

}
