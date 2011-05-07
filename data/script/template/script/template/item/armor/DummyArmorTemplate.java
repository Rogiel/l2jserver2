package script.template.item.armor;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Damagable;

public class DummyArmorTemplate extends AbstractNoGradeArmorTemplate {
	public static final int ID = 20;

	private static final int REDUCED_DAMAGE_PHYSICAL = 10;
	private static final int REDUCED_DAMAGE_MAGICAL = 10;

	@Inject
	public DummyArmorTemplate(ItemTemplateIDFactory factory) {
		super(factory.createID(ID));
	}

	@Override
	public void defend(Attacker source, Attackable target) {
	}

	@Override
	public void interceptIncomingDamage(Damagable target) {
	}

	@Override
	public int getPhysicalDefense() {
		return REDUCED_DAMAGE_PHYSICAL;
	}

	@Override
	public int getMagicalDefense() {
		return REDUCED_DAMAGE_MAGICAL;
	}
}
