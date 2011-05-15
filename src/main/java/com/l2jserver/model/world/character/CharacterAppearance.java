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
package com.l2jserver.model.world.character;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.RGBColor;

/**
 * Defines how an character looks in-game.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterAppearance {
	/**
	 * The parent character
	 */
	private final L2Character character;

	/**
	 * The character face
	 */
	private CharacterFace face;

	/**
	 * Character possible faces
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum CharacterFace {
		FACE_A(0x00),

		FACE_B(0x01),

		FACE_C(0x02);

		public final int option;

		CharacterFace(int option) {
			this.option = option;
		}

		public static CharacterFace fromOption(int option) {
			for (CharacterFace face : values()) {
				if (face.option == option)
					return face;
			}
			return null;
		}
	}

	/**
	 * The character hair color
	 */
	private CharacterHairColor hairColor;

	/**
	 * Character possible hair colors
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum CharacterHairColor {
		COLOR_A(0x00),

		COLOR_B(0x01),

		COLOR_C(0x02),

		COLOR_D(0x03);

		public final int option;

		CharacterHairColor(int option) {
			this.option = option;
		}

		public static CharacterHairColor fromOption(int option) {
			for (CharacterHairColor color : values()) {
				if (color.option == option)
					return color;
			}
			return null;
		}
	}

	/**
	 * The character hair style
	 */
	private CharacterHairStyle hairStyle;

	/**
	 * Character possible hair styles
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum CharacterHairStyle {
		STYLE_A(0x00),

		STYLE_B(0x01),

		STYLE_C(0x02),

		STYLE_D(0x03),

		STYLE_E(0x04);

		public final int option;

		CharacterHairStyle(int option) {
			this.option = option;
		}

		public static CharacterHairStyle fromOption(int option) {
			for (CharacterHairStyle style : values()) {
				if (style.option == option)
					return style;
			}
			return null;
		}
	}

	/**
	 * An alternative name. It will be displayed in-game.
	 * <p>
	 * <b>This is not persisted!</b>
	 */
	private String alternativeName;
	/**
	 * An alternative title. It will be displayed in-game.
	 * <p>
	 * <b>This is not persisted!</b>
	 */
	private String alternativeTitle;

	/**
	 * The name color
	 * <p>
	 * <b>This is not persisted!</b>
	 */
	private RGBColor nameColor = RGBColor.fromInteger(0xFFFFFF);
	/**
	 * The title color
	 * <p>
	 * <b>This is not persisted!</b>
	 */
	private RGBColor titleColor = RGBColor.fromInteger(0xFFFF77);

	public CharacterAppearance(L2Character character) {
		this.character = character;
	}

	/**
	 * @return the character face
	 */
	public CharacterFace getFace() {
		return face;
	}

	/**
	 * @param face
	 *            the character face to set
	 */
	public void setFace(CharacterFace face) {
		this.face = face;
	}

	/**
	 * @return the hair color
	 */
	public CharacterHairColor getHairColor() {
		return hairColor;
	}

	/**
	 * @param hairColor
	 *            the hair color to set
	 */
	public void setHairColor(CharacterHairColor hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * @return the hair style
	 */
	public CharacterHairStyle getHairStyle() {
		return hairStyle;
	}

	/**
	 * @param hairStyle
	 *            the hair style to set
	 */
	public void setHairStyle(CharacterHairStyle hairStyle) {
		this.hairStyle = hairStyle;
	}

	/**
	 * @return the alternative name
	 */
	public String getAlternativeName() {
		return alternativeName;
	}

	/**
	 * @param alternativeName
	 *            the alternative name to set
	 */
	public void setAlternativeName(String alternativeName) {
		this.alternativeName = alternativeName;
	}

	/**
	 * @return the alternative title
	 */
	public String getAlternativeTitle() {
		return alternativeTitle;
	}

	/**
	 * @param alternativeTitle
	 *            the alternative title to set
	 */
	public void setAlternativeTitle(String alternativeTitle) {
		this.alternativeTitle = alternativeTitle;
	}

	/**
	 * @return the name color
	 */
	public RGBColor getNameColor() {
		return nameColor;
	}

	/**
	 * @param nameColor
	 *            the name color to set
	 */
	public void setNameColor(RGBColor nameColor) {
		this.nameColor = nameColor;
	}

	/**
	 * @return the title color
	 */
	public RGBColor getTitleColor() {
		return titleColor;
	}

	/**
	 * @param titleColor
	 *            the title color to set
	 */
	public void setTitleColor(RGBColor titleColor) {
		this.titleColor = titleColor;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}
}
