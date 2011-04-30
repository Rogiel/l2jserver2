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
		FACE1((byte) 0x00),

		FACE2((byte) 0x01),

		FACE3((byte) 0x02),

		FACE4((byte) 0x03);

		public final byte option;

		CharacterFace(byte option) {
			this.option = option;
		}

		public static CharacterFace fromOption(byte option) {
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
		COLOR1((byte) 0x00),

		COLOR2((byte) 0x01),

		COLOR3((byte) 0x02),

		COLOR4((byte) 0x03);

		public final byte option;

		CharacterHairColor(byte option) {
			this.option = option;
		}

		public static CharacterHairColor fromOption(byte option) {
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
		STYLE1((byte) 0x00),

		STYLE2((byte) 0x01),

		STYLE3((byte) 0x02),

		STYLE4((byte) 0x03);

		public final byte option;

		CharacterHairStyle(byte option) {
			this.option = option;
		}

		public static CharacterHairStyle fromOption(byte option) {
			for (CharacterHairStyle style : values()) {
				if (style.option == option)
					return style;
			}
			return null;
		}
	}

	/**
	 * The character sex
	 */
	private CharacterSex sex;

	/**
	 * Represent the sex of an character.
	 * <p>
	 * TODO this will be moved soon: not only characters have sex, NPC can
	 * have'em too.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum CharacterSex {
		MALE, FEMALE;
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
	 */
	private RGBColor nameColor = new RGBColor((byte) 0xFF, (byte) 0xFF,
			(byte) 0xFF);
	/**
	 * The title color
	 */
	private RGBColor titleColor = new RGBColor((byte) 0xFF, (byte) 0xFF,
			(byte) 0x77);

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
	 * @return the character sex
	 */
	public CharacterSex getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the character sex to set
	 */
	public void setSex(CharacterSex sex) {
		this.sex = sex;
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
