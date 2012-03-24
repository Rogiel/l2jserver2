package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.service.database.ddl.annotation.ColumnNullable;
import com.l2jserver.service.database.ddl.annotation.ColumnSize;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.sql.RelationalPathBase;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

/**
 * Maps <code>character</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QCharacter extends RelationalPathBase<Integer> {
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default entity for {@link QCharacter}
	 */
	public static final QCharacter character = new QCharacter("l2character");

	/**
	 * Column: <code>character_id</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);
	/**
	 * Column: <code>account_id</code>
	 */
	@ColumnSize(50)
	public final StringPath accountId = createString("account_id");
	/**
	 * Column: <code>clan_id</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> clanId = createNumber("clan_id",
			Integer.class);

	/**
	 * Column: <code>name</code>
	 */
	@ColumnSize(100)
	public final StringPath name = createString("name");

	/**
	 * Column: <code>race</code>
	 */
	public final EnumPath<CharacterRace> race = createEnum("race",
			CharacterRace.class);
	/**
	 * Column: <code>sex</code>
	 */
	public final EnumPath<ActorSex> sex = createEnum("sex", ActorSex.class);

	/**
	 * Column: <code>character_class</code>
	 */
	public final EnumPath<CharacterClass> characterClass = createEnum("class",
			CharacterClass.class);
	/**
	 * Column: <code>level</code>
	 */
	@ColumnSize(4)
	public final NumberPath<Integer> level = createNumber("level",
			Integer.class);
	/**
	 * Column: <code>experience</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Long> experience = createNumber("experience",
			Long.class);
	/**
	 * Column: <code>sp</code>
	 */
	@ColumnSize(10)
	public final NumberPath<Integer> sp = createNumber("sp", Integer.class);

	/**
	 * Column: <code>cp</code>
	 */
	public final NumberPath<Double> cp = createNumber("cp", Double.class);
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
	 * Column: <code>appearance_hair_color</code>
	 */
	public final EnumPath<CharacterHairColor> appearanceHairColor = createEnum(
			"appearance_hair_color", CharacterHairColor.class);
	/**
	 * Column: <code>appearance_hair_style</code>
	 */
	public final EnumPath<CharacterHairStyle> appearanceHairStyle = createEnum(
			"appearance_hair_style", CharacterHairStyle.class);
	/**
	 * Column: <code>appearance_face</code>
	 */
	public final EnumPath<CharacterFace> apperanceFace = createEnum(
			"apperance_face", CharacterFace.class);

	/**
	 * The entity primary key
	 */
	public final PrimaryKey<Integer> primary = createPrimaryKey(characterId);

	// public final ForeignKey<Clan> clanIdKey = createForeignKey(clanId, "");

	/**
	 * Creates a new instance
	 * 
	 * @param variable
	 *            the query variable
	 */
	public QCharacter(String variable) {
		super(Integer.class, forVariable(variable), "null", "character");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QCharacter(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "character");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QCharacter(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "character");
	}

}
