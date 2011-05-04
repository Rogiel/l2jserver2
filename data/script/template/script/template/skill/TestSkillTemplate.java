package script.template.skill;

import com.l2jserver.model.id.template.factory.SkillTemplateIDFactory;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.model.world.capability.Castable;
import com.l2jserver.model.world.capability.Caster;

public class TestSkillTemplate extends SkillTemplate {
	public static final int ID = 10;

	public TestSkillTemplate(SkillTemplateIDFactory factory) {
		super(factory.createID(ID));
	}

	@Override
	public void cast(Caster caster, Castable... targets) {
		// TODO do casting
	}
}
