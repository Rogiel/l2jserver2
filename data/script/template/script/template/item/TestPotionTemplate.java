package script.template.item;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.template.PotionTemplate;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;

public class TestPotionTemplate extends PotionTemplate {
	public static final int ID = 15;

	@Inject
	public TestPotionTemplate(ItemTemplateIDFactory factory) {
		super(factory.createID(ID));
	}

	@Override
	public void consume(Attacker source, Attackable target) {
		// TODO Auto-generated method stub
	}
}
