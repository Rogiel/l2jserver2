/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package script.template.item;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.provider.ItemTemplateIDProvider;
import com.l2jserver.model.template.item.EtcItemTemplate;

public class AdenaItemTemplate extends EtcItemTemplate {
	public static final int ID = 57;

	@Inject
	public AdenaItemTemplate(ItemTemplateIDProvider factory) {
		super(factory.createID(ID), "icon.etc_adena_i00", ItemMaterial.GOLD);
		this.immediateEffect = true;
		this.price = 1;
	}
}
