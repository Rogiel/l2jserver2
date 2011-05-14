package script.template.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class ArcanaLordTemplate extends WarlockTemplate {
	@Inject
	public ArcanaLordTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.ARCANA_LORD.id),
				CharacterClass.ARCANA_LORD,
				// ATTRIBUTES
				41,// INT
				22,// STR
				27,// CON
				39,// MEN
				21,// DEX
				20,// WIT
				3,// physical attack
				6,// magical attack
				54,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				28,// accuracy
				40,// critical
				28,// evasion
				120,// move speed
				62500,// max inventory weight
				false,// can craft
				Point.fromXYZ(-90890, 248027, -3570)// spawn location
		);
	}

	protected ArcanaLordTemplate(CharacterTemplateID id,
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
