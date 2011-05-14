package script.template.item.potion;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.template.item.PotionTemplate;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;

public class RedPotionTemplate extends PotionTemplate {
	public static final int ID = 15;

	@Inject
	public RedPotionTemplate(ItemTemplateIDFactory factory) {
		super(factory.createID(ID), "icon.etc_potion_red_i00");
		this.weight = 80;
		this.price = 40;

	}

	@Override
	public void consume(Attacker source, Attackable target) {
		// TODO Auto-generated method stub
	}
}
