package script.template.item.weapon;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.world.capability.Attackable;
import com.l2jserver.model.world.capability.Attacker;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;

public class LongSwordTemplate extends AbstractNoGradeWeaponTemplate {
	public static final int ID = 57;

	@Inject
	public LongSwordTemplate(ItemTemplateIDFactory factory) {
		super(factory.createID(ID), "icon.weapon_long_sword_i00",
				ItemMaterial.STEEL, InventoryPaperdoll.RIGHT_HAND,
				WeaponType.SWORD);

		attribute.set(WeaponAttributeType.PHYSICAL_ATTACK, 0x08, 24);
		attribute.set(WeaponAttributeType.MAGICAL_ATTACK, 0x08, 17);
		attribute.set(WeaponAttributeType.R_CRITICAL, 0x08, 8);
		attribute.set(WeaponAttributeType.PHYSICAL_ATTACK_SPEED, 0x08, 379);

		this.randomDamage = 10;
		this.attackRange = 40;
		this.damageRange = new int[] { 0, 0, 40, 120 };
		this.weight = 1560;
		this.price = 105000;
		this.soulshots = 2;
		this.spiritshots = 2;
	}

	@Override
	public void attack(Attacker source, Attackable target) {
		source.attack(target, this);
		target.receiveAttack(source, this);
	}
}
