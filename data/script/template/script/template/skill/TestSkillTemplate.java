package script.template.skill;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.SkillTemplateIDFactory;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Caster;
import com.l2jserver.model.world.character.CharacterClass;

public class TestSkillTemplate extends SkillTemplate {
	public static final int ID = 10;

	@Inject
	public TestSkillTemplate(SkillTemplateIDFactory factory) {
		super(factory.createID(ID));
		this.maximumLevel = 1;
	}

	@Override
	public CharacterClass[] getClasses() {
		return new CharacterClass[] { CharacterClass.HUMAN_FIGHTER };
	}

	@Override
	public void cast(Caster caster, Castable... targets) {
		// TODO do casting
	}
}
