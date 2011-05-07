package script.template.item.armor;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Damagable;
import com.l2jserver.model.world.capability.Enchantable;

public class Dummy2ArmorTemplate extends AbstractGradeAArmorTemplate {
	public static final int ID = 10;

	private static final int REDUCED_DAMAGE_PHYSICAL = 10;
	private static final int REDUCED_DAMAGE_MAGICAL = 10;
	private static final int MAX_ENCHANT = 10;

	@Inject
	public Dummy2ArmorTemplate(ItemTemplateIDFactory factory) {
		super(factory.createID(ID));
	}

	@Override
	public void defend(Attacker source, Attackable target) {
	}

	@Override
	public void interceptIncomingDamage(Damagable target) {
	}

	@Override
	public void enchant(Enchantable target) {
		if (target.getEnchantLevel() >= MAX_ENCHANT)
			return;
		super.enchant(target);
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
