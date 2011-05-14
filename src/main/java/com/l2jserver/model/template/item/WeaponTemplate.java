package com.l2jserver.model.template.item;

import com.l2jserver.model.id.template.ItemTemplateID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.template.capability.Attackable;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.character.CharacterInventory.InventoryPaperdoll;

/**
 * Template for Weapon {@link Item}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public abstract class WeaponTemplate extends ItemTemplate implements Attackable {
	protected InventoryPaperdoll paperdoll = null;
	protected WeaponType type;

	public enum WeaponType {
		FISHINGROD, CROSSBOW, BIG_SWORD, RAPIER, DUAL_FIST, ETC, DAGGER, BLUNT, BIG_BLUNT, DUAL_DAGGER, DUAL, FLAG, POLE, FIST, BOW, OWN_THING, SWORD, ANCIENT_SWORD;
	}

	protected final WeaponAttribute attribute = new WeaponAttribute();

	protected int randomDamage = 0;
	protected int attackRange = 0;
	protected int[] damageRange = new int[] {};

	protected int soulshots = 0;
	protected int spiritshots = 0;

	protected int crystals;
	protected CrystalType crystal;

	public enum CrystalType {
		GRADE_A, GRADE_B, GRADE_C, GRADE_D;
	}

	public WeaponTemplate(ItemTemplateID id, String icon,
			ItemMaterial material, InventoryPaperdoll paperdoll, WeaponType type) {
		super(id, icon, material);
		this.paperdoll = paperdoll;
		this.type = type;
	}

	public enum WeaponAttributeType {
		PHYSICAL_ATTACK, MAGICAL_ATTACK, R_CRITICAL, PHYSICAL_ATTACK_SPEED;
	}

	public class WeaponAttribute {
		public void add(WeaponAttributeType type, int order, int value) {
		}

		public void set(WeaponAttributeType type, int order, int value) {
		}

		public void enchant(WeaponAttributeType type, int order, int value) {
		}
	}

	/**
	 * @return the paperdoll
	 */
	public InventoryPaperdoll getPaperdoll() {
		return paperdoll;
	}

	/**
	 * @param paperdoll
	 *            the paperdoll to set
	 */
	public void setPaperdoll(InventoryPaperdoll paperdoll) {
		this.paperdoll = paperdoll;
	}

	/**
	 * @return the type
	 */
	public WeaponType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(WeaponType type) {
		this.type = type;
	}

	/**
	 * @return the randomDamage
	 */
	public int getRandomDamage() {
		return randomDamage;
	}

	/**
	 * @param randomDamage
	 *            the randomDamage to set
	 */
	public void setRandomDamage(int randomDamage) {
		this.randomDamage = randomDamage;
	}

	/**
	 * @return the attackRange
	 */
	public int getAttackRange() {
		return attackRange;
	}

	/**
	 * @param attackRange
	 *            the attackRange to set
	 */
	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	/**
	 * @return the damageRange
	 */
	public int[] getDamageRange() {
		return damageRange;
	}

	/**
	 * @param damageRange
	 *            the damageRange to set
	 */
	public void setDamageRange(int[] damageRange) {
		this.damageRange = damageRange;
	}

	/**
	 * @return the soulshots
	 */
	public int getSoulshots() {
		return soulshots;
	}

	/**
	 * @param soulshots
	 *            the soulshots to set
	 */
	public void setSoulshots(int soulshots) {
		this.soulshots = soulshots;
	}

	/**
	 * @return the spiritshots
	 */
	public int getSpiritshots() {
		return spiritshots;
	}

	/**
	 * @param spiritshots
	 *            the spiritshots to set
	 */
	public void setSpiritshots(int spiritshots) {
		this.spiritshots = spiritshots;
	}

	/**
	 * @return the crystals
	 */
	public int getCrystals() {
		return crystals;
	}

	/**
	 * @param crystals
	 *            the crystals to set
	 */
	public void setCrystals(int crystals) {
		this.crystals = crystals;
	}

	/**
	 * @return the crystal
	 */
	public CrystalType getCrystal() {
		return crystal;
	}

	/**
	 * @param crystal
	 *            the crystal to set
	 */
	public void setCrystal(CrystalType crystal) {
		this.crystal = crystal;
	}

	/**
	 * @return the attribute
	 */
	public WeaponAttribute getAttribute() {
		return attribute;
	}
}
