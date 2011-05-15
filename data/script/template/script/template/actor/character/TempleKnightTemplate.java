package script.template.actor.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class TempleKnightTemplate extends ElvenKnightTemplate {
	@Inject
	public TempleKnightTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.TEMPLE_KNIGHT.id),
				CharacterClass.TEMPLE_KNIGHT,
				// ATTRIBUTES
				23,// INT
				36,// STR
				36,// CON
				26,// MEN
				35,// DEX
				14,// WIT
				4,// physical attack
				6,// magical attack
				80,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				36,// accuracy
				46,// critical
				36,// evasion
				125,// move speed
				73000,// max inventory weight
				false,// can craft
				Point.fromXYZ(45978, 41196, -3440)// spawn location
		);
	}

	protected TempleKnightTemplate(CharacterTemplateID id,
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
