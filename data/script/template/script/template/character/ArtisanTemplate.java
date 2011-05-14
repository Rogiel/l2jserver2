package script.template.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class ArtisanTemplate extends DwarvenFighterTemplate {
	@Inject
	public ArtisanTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.ARTISAN.id),
				CharacterClass.ARTISAN,
				// ATTRIBUTES
				20,// INT
				39,// STR
				45,// CON
				27,// MEN
				29,// DEX
				10,// WIT
				4,// physical attack
				6,// magical attack
				80,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				33,// accuracy
				43,// critical
				33,// evasion
				115,// move speed
				83000,// max inventory weight
				true,// can craft
				Point.fromXYZ(108512, -174026, -400)// spawn location
		);
	}

	protected ArtisanTemplate(CharacterTemplateID id,
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
