/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
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
		/**
		 * Face type A
		 */
		FACE_A(0x00),

		/**
		 * Face type B
		 */
		FACE_B(0x01),

		/**
		 * Face type C
		 */
		FACE_C(0x02);

		/**
		 * The option id
		 */
		public final int option;

		/**
		 * @param option
		 *            the option id
		 */
		CharacterFace(int option) {
			this.option = option;
		}

		/**
		 * @param option
		 *            the option id
		 * @return the {@link CharacterFace} represented by <code>option</code>
		 */
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
		/**
		 * The hair color A
		 */
		COLOR_A(0x00),

		/**
		 * The hair color B
		 */
		COLOR_B(0x01),

		/**
		 * The hair color C
		 */
		COLOR_C(0x02),

		/**
		 * The hair color D
		 */
		COLOR_D(0x03);

		/**
		 * The hair color id
		 */
		public final int option;

		/**
		 * @param option
		 *            the hair color id
		 */
		CharacterHairColor(int option) {
			this.option = option;
		}

		/**
		 * @param option
		 *            the hair color id
		 * @return the {@link CharacterHairColor} from the given
		 *         <code>option</code>
		 */
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
		/**
		 * The hair style A
		 */
		STYLE_A(0x00),
		/**
		 * The hair style B
		 */
		STYLE_B(0x01),
		/**
		 * The hair style C
		 */
		STYLE_C(0x02),
		/**
		 * The hair style D
		 */
		STYLE_D(0x03),
		/**
		 * The hair style E
		 */
		STYLE_E(0x04);

		/**
		 * The hair style id
		 */
		public final int option;

		/**
		 * @param option
		 *            the hair style id
		 */
		CharacterHairStyle(int option) {
			this.option = option;
		}

		/**
		 * @param option
		 *            the hair style id
		 * @return the {@link CharacterHairStyle} represented by
		 *         <code>option</code>
		 */
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
	/**
	 * The visibility status
	 */
	private boolean visible;

	/**
	 * @param character
	 *            the parent character
	 */
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
	 * @return the visibility state
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            the visibility state to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}
}
