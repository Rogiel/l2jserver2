package script.template.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.Coordinate;

public class HumanFighterTemplate extends HumanCharacterTemplate {
	@Inject
	public HumanFighterTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.HUMAN_FIGHTER.id),
				CharacterClass.HUMAN_FIGHTER,
				// ATTRIBUTES
				21,// INT
				40,// STR
				43,// CON
				25,// MEN
				30,// DEX
				11,// WIT
				4,// physical attack
				6,// magical attack
				80,// physical def
				4,// magical def
				300,// attack speed
				333,// cast speed
				33,// accuracy
				44,// critical
				33,// evasion
				115,// move speed
				81900,// max inventory weight
				false,// can craft
				Coordinate.fromXYZ(-71338, 258271, -3104)// spawn location
		);
	}

	@Override
	public L2Character create() {
		final L2Character character = super.create();
		// TODO register skills
		return character;
	}
}
