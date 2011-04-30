package script.template.armor;

import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.capability.Damagable;

public class DummyArmorTemplate extends AbstractNoGradeArmorTemplate {
	private static final int REDUCED_DAMAGE_PHYSICAL = 10;
	private static final int REDUCED_DAMAGE_MAGICAL = 10;

	public DummyArmorTemplate() {
		super(null);
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
