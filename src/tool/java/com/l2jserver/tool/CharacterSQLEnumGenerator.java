package com.l2jserver.tool;

import com.l2jserver.model.world.character.CharacterClass;

public class CharacterSQLEnumGenerator {
	public static void main(String[] args) {
		System.out.println("== 'Character' SQL STATEMENT ==");
		System.out.println(createClassStatement());
	}

	private static String createClassStatement() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ALTER TABLE `character` CHANGE `class` `class` ENUM(");
		for (CharacterClass c : CharacterClass.values()) {
			builder.append("'" + c.name() + "',");
		}
		builder.replace(builder.length() - 1, builder.length(), "");
		builder.append(") CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'HUMAN_FIGHTER';");
		return builder.toString();
	}
}
