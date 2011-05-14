package script.template.item.armor;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Enchantable;
import com.l2jserver.model.template.capability.Penalty;
import com.l2jserver.model.template.item.ArmorTemplate;
import com.l2jserver.model.world.capability.Equiper;

public abstract class AbstractGradeAArmorTemplate extends ArmorTemplate
		implements Enchantable, Penalty {
	public AbstractGradeAArmorTemplate(ItemTemplateID id) {
		super(id);
	}

	@Override
	public void enchant(com.l2jserver.model.world.capability.Enchantable target) {
		target.setEnchantLevel(target.getEnchantLevel() + 1);
	}

	@Override
	public void penalty(Equiper user) {
		// if (!(user instanceof Levelable) && !(user instanceof Castable))
		// return;
		// final Levelable levelable = (Levelable) user;
		// final Castable castable = (Castable) user;
		// if (levelable.getLevel() < 20) {
		//
		// }
	}
}
