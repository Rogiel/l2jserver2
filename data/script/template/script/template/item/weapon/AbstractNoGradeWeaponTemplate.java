package script.template.item.weapon;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.item.WeaponTemplate;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;

public abstract class AbstractNoGradeWeaponTemplate extends WeaponTemplate {
	public AbstractNoGradeWeaponTemplate(ItemTemplateID id, String icon,
			ItemMaterial material, InventoryPaperdoll paperdoll, WeaponType type) {
		super(id, icon, material, paperdoll, type);
	}
}
