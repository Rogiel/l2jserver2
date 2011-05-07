package com.l2jserver.tool;

import com.l2jserver.model.world.character.CharacterClass;

public class ClassesSQLEnumGenerator {
	public static void main(String[] args) {
		final StringBuilder builder = new StringBuilder();
		for (CharacterClass c : CharacterClass.values()) {
			builder.append("'" + c.name() + "',");
		}
		System.out.println(builder.substring(0, builder.length() - 1));
	}
}
