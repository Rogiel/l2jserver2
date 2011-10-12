package com.l2jserver.model.template.item;

import javax.xml.bind.annotation.XmlType;

import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Enum of all available weapon types
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@XmlType(name = "WeaponType")
public enum WeaponType {
	/**
	 * The Sword weapon type
	 */
	SWORD(StatType.SWORD_WPN_VULN),
	/**
	 * The blunt weapon type
	 */
	BLUNT(StatType.BLUNT_WPN_VULN),
	/**
	 * The dagger weapon type
	 */
	DAGGER(StatType.DAGGER_WPN_VULN),
	/**
	 * The bow weapon type
	 */
	BOW(StatType.BOW_WPN_VULN),
	/**
	 * The pole weapon type
	 */
	POLE(StatType.POLE_WPN_VULN),
	/**
	 * An dummy entry for a none-weapon
	 */
	NONE(StatType.NONE_WPN_VULN),
	/**
	 * The dual sword weapon type
	 */
	DUAL(StatType.DUAL_WPN_VULN),
	/**
	 * An other type of weapon
	 */
	ETC(StatType.ETC_WPN_VULN),
	/**
	 * The fist weapon type
	 */
	FIST(StatType.FIST_WPN_VULN),
	/**
	 * The dual fist weapon type
	 */
	DUALFIST(StatType.DUALFIST_WPN_VULN),
	/**
	 * The fishing tools
	 */
	FISHINGROD(null),
	/**
	 * The rapier weapon type
	 */
	RAPIER(StatType.RAPIER_WPN_VULN),
	/**
	 * The ancient sword type
	 */
	ANCIENTSWORD(StatType.ANCIENT_WPN_VULN),
	/**
	 * The crossbow type
	 */
	CROSSBOW(StatType.CROSSBOW_WPN_VULN),
	/**
	 * Unk
	 */
	FLAG(null),
	/**
	 * Unk
	 */
	OWNTHING(null),
	/**
	 * The dual dagger weapon type
	 */
	DUALDAGGER(StatType.DUALDAGGER_WPN_VULN);

	/**
	 * This weapon type of weaknesses
	 */
	public final StatType weaknessesStat;

	/**
	 * @param weaknessesStat
	 *            the weapon weaknesses
	 */
	WeaponType(StatType weaknessesStat) {
		this.weaknessesStat = weaknessesStat;
	}
}