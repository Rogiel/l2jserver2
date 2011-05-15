package script.template.actor.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class MaleSoldierTemplate extends AbstractKamaelCharacterTemplate {
	@Inject
	public MaleSoldierTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.MALE_SOLDIER.id),
				CharacterClass.MALE_SOLDIER,
				// ATTRIBUTES
				29,// INT
				41,// STR
				31,// CON
				25,// MEN
				33,// DEX
				11,// WIT
				4,// physical attack
				6,// magical attack
				80,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				35,// accuracy
				45,// critical
				35,// evasion
				122,// move speed
				87000,// max inventory weight
				false,// can craft
				Point.fromXYZ(-125464, 37776, 1176)// spawn location
		);
	}

	protected MaleSoldierTemplate(CharacterTemplateID id,
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
