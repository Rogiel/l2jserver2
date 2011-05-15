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
package com.l2jserver.game.net.packet.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.l2jserver.db.dao.CharacterDAO;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.AbstractClientPacket;
import com.l2jserver.game.net.packet.server.CharacterCreateFailPacket;
import com.l2jserver.game.net.packet.server.CharacterCreateOkPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.id.object.factory.CharacterIDFactory;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.template.CharacterTemplate;
import com.l2jserver.model.world.AbstractActor.Race;
import com.l2jserver.model.world.AbstractActor.Sex;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterFace;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairColor;
import com.l2jserver.model.world.character.CharacterAppearance.CharacterHairStyle;
import com.l2jserver.util.BufferUtils;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client about the status of the operation.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterCreatePacket extends AbstractClientPacket {
	public static final int OPCODE = 0x0c;

	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory
			.getLogger(CharacterCreatePacket.class);

	// services and daos
	private final CharacterDAO characterDao;
	private final CharacterIDFactory characterIdFactory;
	private final CharacterTemplateIDFactory characterTemplateIdFactory;

	// packet
	private String name;
	private Race race;
	private Sex sex;
	private int classId;

	private int intelligence;
	private int strength;
	private int concentration;
	private int mentality;
	private int dexterity;
	private int witness;

	private CharacterHairStyle hairStyle;
	private CharacterHairColor hairColor;
	private CharacterFace face;

	@Inject
	public CharacterCreatePacket(CharacterDAO characterDao,
			CharacterIDFactory characterIdFactory,
			CharacterTemplateIDFactory characterTemplateIdFactory) {
		this.characterDao = characterDao;
		this.characterIdFactory = characterIdFactory;
		this.characterTemplateIdFactory = characterTemplateIdFactory;
	}

	@Override
	public void read(Lineage2Connection conn, ChannelBuffer buffer) {
		name = BufferUtils.readString(buffer);
		race = Race.fromOption(buffer.readInt());
		sex = Sex.fromOption(buffer.readInt());
		classId = buffer.readInt();

		intelligence = buffer.readInt();
		strength = buffer.readInt();
		concentration = buffer.readInt();
		mentality = buffer.readInt();
		dexterity = buffer.readInt();
		witness = buffer.readInt();

		hairStyle = CharacterHairStyle.fromOption(buffer.readInt());
		hairColor = CharacterHairColor.fromOption(buffer.readInt());
		face = CharacterFace.fromOption(buffer.readInt());
	}

	@Override
	public void process(final Lineage2Connection conn) {
		log.debug("Creating a new character, race={}, sex={}, classid={}",
				new Object[] { race, sex, classId });
		if ((name.length() < 1) || (name.length() > 16)) {
			log.debug("Character name length invalid: {}. Aborting.", name);
			conn.write(new CharacterCreateFailPacket(
					CharacterCreateFailPacket.Reason.REASON_16_ENG_CHARS));
			return;
		}
		// TODO check alphanumeric name
		if (sex == null || hairStyle == null || hairColor == null
				|| face == null) {
			log.debug("Character appearance invalid. Aborting.");
			// if some of those attributes is null, something wrong happened.
			// Maybe we don't support the value sent!
			conn.write(new CharacterCreateFailPacket(
					CharacterCreateFailPacket.Reason.REASON_CREATION_FAILED));
			return;
		}
		// existence check
		final L2Character existenceCheck = characterDao.selectByName(name);
		if (existenceCheck != null) {
			log.debug("Character name already exists: {}. Aborting.", name);
			conn.write(new CharacterCreateFailPacket(
					CharacterCreateFailPacket.Reason.REASON_NAME_ALREADY_EXISTS));
			return;
		}

		// create template id and lookup for the template instance
		final CharacterTemplateID templateId = characterTemplateIdFactory
				.createID(classId);
		final CharacterTemplate template = templateId.getTemplate();
		log.debug("Creating character with template {}", template);

		// ensure parameters passed by the client are true
		if (/*
			 * (intelligence != template.getIntelligence()) || (strength !=
			 * template.getStrength()) || (concentration !=
			 * template.getConcentration()) || (mentality !=
			 * template.getMentality()) || (dextry != template.getDextry()) ||
			 * (witness != template.getWitness()) ||
			 */(race != template.getRace())) {
			// log.debug("intelligence, expected {}, received {}",
			// template.getIntelligence(), intelligence);
			// log.debug("strength, expected {}, received {}",
			// template.getStrength(), strength);
			// log.debug("concentration, expected {}, received {}",
			// template.getConcentration(), concentration);
			// log.debug("dextry, expected {}, received {}",
			// template.getDextry(),
			// dextry);
			// log.debug("witness, expected {}, received {}",
			// template.getWitness(), witness);
			log.debug("race, expected {}, received {}", template.getRace(),
					race);

			log.debug(
					"Values sent by client and from template does not match: {}",
					template);
			// some of the values didn't match, canceling creation
			conn.write(new CharacterCreateFailPacket(
					CharacterCreateFailPacket.Reason.REASON_CREATION_FAILED));
			return;
		}

		// everything is fine, allocate a new ID
		final CharacterID id = characterIdFactory.createID();
		// create the instance from the template
		final L2Character character = template.create();

		log.debug("Character object created, ID: {}, Object: {}", id, character);

		// set parameters
		character.setID(id);
		character.setName(name);

		character.setSex(sex);

		character.getAppearance().setHairStyle(hairStyle);
		character.getAppearance().setHairColor(hairColor);
		character.getAppearance().setFace(face);

		if (characterDao.save(character)) {
			log.debug("Character saved to database");
			conn.write(CharacterCreateOkPacket.INSTANCE);
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the race
	 */
	public Race getRace() {
		return race;
	}

	/**
	 * @param race
	 *            the race to set
	 */
	public void setRace(Race race) {
		this.race = race;
	}

	/**
	 * @return the sex
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/**
	 * @return the classId
	 */
	public int getClassId() {
		return classId;
	}

	/**
	 * @param classId
	 *            the classId to set
	 */
	public void setClassId(int classId) {
		this.classId = classId;
	}

	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * @param intelligence
	 *            the intelligence to set
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength
	 *            the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the concentration
	 */
	public int getConcentration() {
		return concentration;
	}

	/**
	 * @param concentration
	 *            the concentration to set
	 */
	public void setConcentration(int concentration) {
		this.concentration = concentration;
	}

	/**
	 * @return the mentality
	 */
	public int getMentality() {
		return mentality;
	}

	/**
	 * @param mentality
	 *            the mentality to set
	 */
	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	/**
	 * @return the dexterity
	 */
	public int getDexterity() {
		return dexterity;
	}

	/**
	 * @param dexterity
	 *            the dexterity to set
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	/**
	 * @return the witness
	 */
	public int getWitness() {
		return witness;
	}

	/**
	 * @param witness
	 *            the witness to set
	 */
	public void setWitness(int witness) {
		this.witness = witness;
	}

	/**
	 * @return the hairStyle
	 */
	public CharacterHairStyle getHairStyle() {
		return hairStyle;
	}

	/**
	 * @param hairStyle
	 *            the hairStyle to set
	 */
	public void setHairStyle(CharacterHairStyle hairStyle) {
		this.hairStyle = hairStyle;
	}

	/**
	 * @return the hairColor
	 */
	public CharacterHairColor getHairColor() {
		return hairColor;
	}

	/**
	 * @param hairColor
	 *            the hairColor to set
	 */
	public void setHairColor(CharacterHairColor hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * @return the face
	 */
	public CharacterFace getFace() {
		return face;
	}

	/**
	 * @param face
	 *            the face to set
	 */
	public void setFace(CharacterFace face) {
		this.face = face;
	}
}
