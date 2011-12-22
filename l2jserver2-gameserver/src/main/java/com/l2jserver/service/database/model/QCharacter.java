package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.l2jserver.model.template.actor.ActorSex;
import com.l2jserver.model.template.character.CharacterClass;
import com.l2jserver.model.template.character.CharacterRace;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
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
public class QCharacter extends RelationalPathBase<L2Character> {
	private static final long serialVersionUID = -59499032;

	public static final QCharacter character = new QCharacter("l2character");

	public final NumberPath<Integer> characterId = createNumber("character_id",
			Integer.class);
	public final StringPath accountId = createString("account_id");
	public final NumberPath<Integer> clanId = createNumber("clan_id",
			Integer.class);

	public final StringPath name = createString("name");
	public final EnumPath<CharacterRace> race = createEnum("race",
			CharacterRace.class);
	public final EnumPath<ActorSex> sex = createEnum("sex", ActorSex.class);

	public final EnumPath<CharacterClass> characterClass = createEnum("class",
			CharacterClass.class);
	public final NumberPath<Integer> level = createNumber("level",
			Integer.class);
	public final NumberPath<Long> experience = createNumber("experience",
			Long.class);
	public final NumberPath<Integer> sp = createNumber("sp", Integer.class);

	public final NumberPath<Double> cp = createNumber("cp", Double.class);
	public final NumberPath<Double> hp = createNumber("hp", Double.class);
	public final NumberPath<Double> mp = createNumber("mp", Double.class);

	public final NumberPath<Double> pointAngle = createNumber("point_angle",
			Double.class);

	public final NumberPath<Integer> pointX = createNumber("point_x",
			Integer.class);

	public final NumberPath<Integer> pointY = createNumber("point_y",
			Integer.class);

	public final NumberPath<Integer> pointZ = createNumber("point_z",
			Integer.class);

	public final EnumPath<CharacterHairColor> appearanceHairColor = createEnum(
			"appearance_hair_color", CharacterHairColor.class);
	public final EnumPath<CharacterHairStyle> appearanceHairStyle = createEnum(
			"appearance_hair_style", CharacterHairStyle.class);
	public final EnumPath<CharacterFace> apperanceFace = createEnum(
			"apperance_face", CharacterFace.class);

	public final PrimaryKey<L2Character> primary = createPrimaryKey(characterId);

	public QCharacter(String variable) {
		super(L2Character.class, forVariable(variable), "null", "character");
	}

	public QCharacter(Path<? extends L2Character> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "character");
	}

	public QCharacter(PathMetadata<?> metadata) {
		super(L2Character.class, metadata, "null", "character");
	}

}
