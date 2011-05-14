package script.template.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class SpectralMasterTemplate extends PhantomSummonerTemplate {
	@Inject
	public SpectralMasterTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.SPECTRAL_MASTER.id),
				CharacterClass.SPECTRAL_MASTER,
				// ATTRIBUTES
				44,// INT
				23,// STR
				24,// CON
				37,// MEN
				23,// DEX
				19,// WIT
				3,// physical attack
				6,// magical attack
				54,// physical def
				41,// magical def
				300,// attack speed
				333,// cast speed
				29,// accuracy
				41,// critical
				29,// evasion
				122,// move speed
				61000,// max inventory weight
				false,// can craft
				Point.fromXYZ(28295, 11063, -4224)// spawn location
		);
	}

	protected SpectralMasterTemplate(CharacterTemplateID id,
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
