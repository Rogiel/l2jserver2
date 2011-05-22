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
package com.l2jserver.model.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.capability.Depositable;
import com.l2jserver.model.template.capability.Dropable;
import com.l2jserver.model.template.capability.Enchantable;
import com.l2jserver.model.template.capability.Sellable;
import com.l2jserver.model.template.capability.Tradable;
import com.l2jserver.model.template.capability.Usable;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.network.NetworkService;

/**
 * Template for an {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class ItemTemplate extends AbstractTemplate<Item> {
	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(ItemTemplate.class);

	@Inject
	protected NetworkService networkService;

	protected int weight = 0;
	protected int price = 0;
	protected String icon;
	protected boolean immediateEffect = true;

	protected ItemMaterial material;

	public enum ItemMaterial {
		COTTON, WOOD, PAPER, FISH, ORIHARUKON, HORN, ADAMANTAITE, CHRYSOLITE, MITHRIL, COBWEB, RUNE_XP, CLOTH, SCALE_OF_DRAGON, BONE, GOLD, LEATHER, FINE_STEEL, SILVER, DYESTUFF, CRYSTAL, RUNE_REMOVE_PENALTY, STEEL, BRONZE, RUNE_SP, LIQUID, BLOOD_STEEL, DAMASCUS;
	}

	public ItemTemplate(ItemTemplateID id) {
		super(id);
	}

	public ItemTemplate(final ItemTemplateID id, String icon,
			ItemMaterial material) {
		super(id);
		this.icon = icon;
		this.material = material;
	}

	@Override
	public Item create() {
		log.debug("Creating a new Item instance with template {}", this);
		return new Item(this.getID());
	}

	public final void use(Item item, L2Character character) {
		final CharacterID id = character.getID();
		final Lineage2Connection conn = networkService.discover(id);
		this.use(item, character, conn);
	}

	protected void use(Item item, L2Character character, Lineage2Connection conn) {
		conn.sendActionFailed();
	}

	public void stack(Item... object) {
	}

	/**
	 * @return true if item is enchantable
	 */
	public boolean isEnchantable() {
		return (this instanceof Enchantable);
	}

	/**
	 * @return the sellable
	 */
	public boolean isSellable() {
		return (this instanceof Sellable);
	}

	/**
	 * @return the dropable
	 */
	public boolean isDropable() {
		return (this instanceof Dropable);
	}

	/**
	 * @return the tradable
	 */
	public boolean isTradable() {
		return (this instanceof Tradable);
	}

	/**
	 * @return the usable
	 */
	public boolean isUsable() {
		return (this instanceof Usable);
	}

	/**
	 * @return the usable
	 */
	public boolean isDepositable() {
		return (this instanceof Depositable);
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @return the immediateEffect
	 */
	public boolean isImmediateEffect() {
		return immediateEffect;
	}

	/**
	 * @return the material
	 */
	public ItemMaterial getMaterial() {
		return material;
	}

	@Override
	public ItemTemplateID getID() {
		return (ItemTemplateID) super.getID();
	}
}
