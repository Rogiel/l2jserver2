package script.template.item;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.factory.ItemTemplateIDFactory;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.capability.Stackable;
import com.l2jserver.model.world.Item;

public class AdenaItemTemplate extends ItemTemplate implements Stackable<Item> {
	public static final int ID = 57;

	@Inject
	public AdenaItemTemplate(ItemTemplateIDFactory factory) {
		super(factory.createID(ID));
		this.icon = "icon.etc_adena_i00";
		this.immediateEffect = true;
		this.material = ItemMaterial.GOLD;
		this.price = 1;
	}
}
