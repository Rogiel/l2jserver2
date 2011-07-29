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
package com.l2jserver.game.net.packet.server;

import org.htmlparser.tags.Html;
import org.jboss.netty.buffer.ChannelBuffer;

import com.l2jserver.game.net.Lineage2Client;
import com.l2jserver.game.net.packet.AbstractServerPacket;
import com.l2jserver.util.BufferUtils;
import com.l2jserver.util.html.markup.HtmlTemplate;

/**
 * This packet sends an HTML message to be displayed in the client. As opposed
 * to {@link SM_HTML}, this one displays it in the community board window.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SM_COMMUNITY_HTML extends AbstractServerPacket {
	/**
	 * The packet OPCODE
	 */
	public static final int OPCODE = 0x7b;

	/**
	 * The HTML contents
	 */
	private final String html;

	public SM_COMMUNITY_HTML(String html) {
		super(OPCODE);
		this.html = html;
	}

	public SM_COMMUNITY_HTML(Html html) {
		super(OPCODE);
		this.html = html.toHtml();
	}

	public SM_COMMUNITY_HTML(HtmlTemplate template) {
		super(OPCODE);
		this.html = template.toHtmlString();
	}

	@Override
	public void write(Lineage2Client conn, ChannelBuffer buffer) {
		buffer.writeByte(0x01); // display or hide
		BufferUtils.writeString(buffer, html);
	}
}
