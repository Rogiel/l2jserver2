package com.l2jserver.game.net.packet.server;

import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.model.template.CharacterTemplate;

/**
 * An packet that sends all character templates to the client.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class CharacterTemplatePacket extends AbstractServerPacket {
	public static final int OPCODE = 0x0d;

	private CharacterTemplate[] templates;

	public CharacterTemplatePacket(CharacterTemplate... templates) {
		super(OPCODE);
		this.templates = templates;
	}

	@Override
	public void write(ChannelBuffer buffer) {
		buffer.writeInt(templates.length);
		for (final CharacterTemplate template : templates) {
			buffer.writeInt(template.getRace().option);
			buffer.writeInt(template.getCharacterClass().id);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseAttributes().getStrength());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseAttributes().getDextry());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseAttributes().getConcentration());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseAttributes().getIntelligence());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseAttributes().getWitness());
			buffer.writeInt(0x0a);
			buffer.writeInt(0x46);
			buffer.writeInt(template.getBaseAttributes().getMentality());
			buffer.writeInt(0x0a);
		}
	}
}
