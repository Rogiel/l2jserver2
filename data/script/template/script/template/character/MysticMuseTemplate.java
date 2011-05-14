package script.template.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class MysticMuseTemplate extends SpellsingerTemplate {
	@Inject
	public MysticMuseTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.MYSTIC_MUSE.id),
				CharacterClass.MYSTIC_MUSE,
				// ATTRIBUTES
				37,// INT
				21,// STR
				25,// CON
				40,// MEN
				24,// DEX
				23,// WIT
				3,// physical attack
				6,// magical attack
				54,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				30,// accuracy
				41,// critical
				30,// evasion
				122,// move speed
				62400,// max inventory weight
				false,// can craft
				Point.fromXYZ(46182, 41198, -3440)// spawn location
		);
	}

	protected MysticMuseTemplate(CharacterTemplateID id,
			CharacterClass characterClass, int intelligence, int strength,
			int concentration, int mentality, int dexterity, int witness,
			int physicalAttack, int magicalAttack, int physicalDefense,
			int magicalDefense, int attackSpeed, int castSpeed, int accuracy,
			int criticalChance, int evasionChance, int moveSpeed,
			int maxWeigth, boolean craft, Point spawnLocation) {
		super(id, characterClass, intelligence, strength, concentration,
				mentality, dexterity, witness, physicalAttack, magicalAttack,
				physicalDefense, magicalDefense, attackSpeed, castSpeed,
				accuracy, criticalChance, evasionChance, moveSpeed, maxWeigth,
				craft, spawnLocation);
	}

	@Override
	public L2Character create() {
		final L2Character character = super.create();
		// TODO register skills
		return character;
	}
}
