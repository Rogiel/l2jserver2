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
package com.l2jserver.tool.conversor.itemtemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXB;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ItemTemplateConversor {
	private static String template;

	public static void main(String[] args) throws IOException {
		template = IOUtils.toString(ItemTemplateConversor.class
				.getResourceAsStream("ItemTemplateBase.txt"));

		@SuppressWarnings("unchecked")
		Collection<File> files = FileUtils
				.listFiles(
						new File(
								"/home/rogiel/Documents/Eclipse/lineage2/L2J_DataPack_BETA/data/stats/items"),
						new String[] { "xml" }, false);

		for (final File file : files) {
			processFile(file.getAbsolutePath());
		}
	}

	private static void processFile(String file) throws FileNotFoundException {
		ItemListXml xml = JAXB.unmarshal(new FileInputStream(file),
				ItemListXml.class);
		for (final ItemXml item : xml.getItems()) {
			String template = ItemTemplateConversor.template;
			String values = "";
			for (final ItemValueXml val : item.getValues()) {
				values += generateSet(val.getName(), val.getValue());
			}
			template = replace(template, "values", values);
			template = replace(template, "package", "");
			template = replace(template, "import", "");
			template = replace(template, "className", camelCase(item.getName()));
			template = replace(template, "extends", " extends "
					+ camelCase(item.getType()) + "Template");
			template = replace(template, "implements", "");
			template = replace(template, "id", item.getId() + "");
		}
	}

	private static String generateSet(String property, String value) {
		value = convertValue(property, value);
		if (value == null)
			return "";
		property = convertPropertyName(property);
		if (property == null)
			return "";

		if (property.contains("_")) {
			System.out.println(property + " -> " + value);
			System.exit(0);
		}

		return "this." + convertPropertyName(property) + " = " + value + ";\n";
	}

	private static String convertPropertyName(String property) {
		if ("default_action".equals(property))
			return null;
		if ("is_tradable".equals(property))
			return null;
		if ("is_dropable".equals(property))
			return null;
		if ("is_sellable".equals(property))
			return null;
		if ("is_destroyable".equals(property))
			return null;
		if ("is_depositable".equals(property))
			return null;
		if ("is_questitem".equals(property))
			return null;
		if ("enchant_enabled".equals(property))
			return null;
		if ("element_enabled".equals(property))
			return null;
		if ("enchant4_skill".equals(property))
			return null;
		if ("is_premium".equals(property))
			return null;
		if ("is_oly_restricted".equals(property))
			return null;
		if ("change_weaponId".equals(property))
			return null;
		if ("use_condition".equals(property))
			return null;
		if ("oncast_chance".equals(property))
			return "castChance";
		if ("oncast_skill".equals(property))
			return "castSkill";
		if ("oncrit_skill".equals(property))
			return "criticalSkill";
		if ("unequip_skill".equals(property))
			return "unequipSkill";
		if ("equip_condition".equals(property))
			return null;
		if ("reduced_soulshot".equals(property))
			return null;
		if ("reduced_mp_consume".equals(property))
			return null;
		if ("equip_reuse_delay".equals(property))
			return null;
		if ("recipe_id".equals(property))
			return null;
		if ("shared_reuse_group".equals(property))
			return null;
		if ("ex_immediate_effect".equals(property))
			return null;
		if ("oncrit_chance".equals(property))
			return null; // TODO
		if ("capsuled_items".equals(property))
			return "capsuled";
		if ("mp_consume".equals(property))
			return "mpConsume";
		if ("reuse_delay".equals(property))
			return "reuseDelay";
		if ("item_skill".equals(property))
			return "itemSkill";
		if ("is_magic_weapon".equals(property))
			return null;
		if ("immediate_effect".equals(property))
			return "immediateEffect";
		if ("bodypart".equals(property))
			return "paperdoll";
		if ("is_stackable".equals(property))
			return null;
		if ("etcitem_type".equals(property))
			return null;
		if ("weapon_type".equals(property))
			return "type";
		if ("armor_type".equals(property))
			return "type";
		if ("attack_range".equals(property))
			return "attackRange";
		if ("damage_range".equals(property))
			return "damageRange";
		if ("random_damage".equals(property))
			return "randomDamage";
		if ("crystal_count".equals(property))
			return "crystals";
		if ("crystal_type".equals(property))
			return "crystal";
		return property;
	}

	private static String convertValue(String property, String value) {
		if ("material".equals(property))
			return "ItemMaterial." + value.toUpperCase();
		if ("weapon_type".equals(property))
			return "WeaponType." + value.toUpperCase();
		if ("bodypart".equals(property))
			return "InventoryPaperdoll." + value.toUpperCase();
		if ("icon".equals(property))
			return convertString(value);
		if ("damage_range".equals(property))
			return convertArray(value, "int");
		return value;
	}

	public static String convertString(String value) {
		return "\"" + value + "\"";
	}

	public static String convertArray(String value, String type) {
		String v = "new " + type + "[] {";
		String[] args = value.split(";");
		for (final String r : args) {
			v += r + ",";
		}
		v = v.substring(0, v.length() - 1);
		return v + "}";
	}

	private static String replace(String template, String key, String value) {
		return template.replaceAll("\\$\\{" + key + "\\}", value);
	}

	private static String camelCase(String c) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]+");
		Matcher m = p.matcher(c.replaceAll("_", " "));
		StringBuffer result = new StringBuffer();
		String word;
		while (m.find()) {
			word = m.group();
			result.append(word.substring(0, 1).toUpperCase()
					+ word.substring(1).toLowerCase());
		}
		return result.toString();
	}
}
