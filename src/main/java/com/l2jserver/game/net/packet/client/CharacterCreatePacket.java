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
import com.l2jserver.util.BufferUtil;

/**
 * Completes the creation of an character. Creates the object, inserts into the
 * database and notifies the client abou the status of the operation.
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
	private int dextry;
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
	public void read(ChannelBuffer buffer) {
		name = BufferUtil.readString(buffer);
		race = Race.fromOption(buffer.readInt());
		sex = Sex.fromOption(buffer.readInt());
		classId = buffer.readInt();

		intelligence = buffer.readInt();
		strength = buffer.readInt();
		concentration = buffer.readInt();
		mentality = buffer.readInt();
		dextry = buffer.readInt();
		witness = buffer.readInt();

		hairStyle = CharacterHairStyle.fromOption(buffer.readInt());
		hairColor = CharacterHairColor.fromOption(buffer.readInt());
		face = CharacterFace.fromOption(buffer.readInt());
	}

	@Override
	public void process(final Lineage2Connection conn) {
		log.debug("Creating a new character");
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
		if ((intelligence != template.getIntelligence())
				|| (strength != template.getStrength())
				|| (concentration != template.getConcentration())
				|| (mentality != template.getMentality())
				|| (dextry != template.getDextry())
				|| (witness != template.getWitness())
				|| (race != template.getRace())) {
			log.debug(
					"Values sent by client and sent from template does not match: {}",
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
}
