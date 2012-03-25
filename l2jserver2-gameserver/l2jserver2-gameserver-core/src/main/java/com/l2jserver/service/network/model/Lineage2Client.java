/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General License for more details.
 *
 * You should have received a copy of the GNU General License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.network.model;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.network.model.packet.ServerPacket;
import com.l2jserver.util.html.markup.HtmlTemplate;

/**
 * This object connects the model (structure objects normally stored in the
 * database) to the controller (protocol stuff).
 * <p>
 * This class also provides handy methods for {@link #write(ServerPacket)
 * writing} packets.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public interface Lineage2Client {
	/**
	 * @return the character
	 */
	boolean hasCharacter();

	/**
	 * @return the character ID
	 */
	CharacterID getCharacterID();

	/**
	 * @return the character
	 */
	L2Character getCharacter();

	/**
	 * @param characterID
	 *            the character ID to set
	 */
	void setCharacterID(CharacterID characterID);

	/**
	 * @return the session
	 */
	Lineage2Session getSession();

	/**
	 * @param session
	 *            the session to set
	 */
	void setSession(Lineage2Session session);

	/**
	 * @return the version
	 */
	ProtocolVersion getVersion();

	/**
	 * @param version
	 *            the version to set
	 */
	void setVersion(ProtocolVersion version);

	/**
	 * Check if the client supports an given version of the protocol. Note that
	 * if the protocol is not known false will always be returned.
	 * 
	 * @param version
	 *            the protocol version to test for support
	 * @return true if version is supported by the client
	 * @see ProtocolVersion#supports(ProtocolVersion)
	 */
	boolean supports(ProtocolVersion version);

	/**
	 * Get the channel
	 * 
	 * @return the channel
	 */
	Channel getChannel();

	/**
	 * Test if the client is still open.
	 * <p>
	 * Please note that the channel can be open but disconnected!
	 * 
	 * @return true if the client is still open
	 * @see Channel#isOpen()
	 */
	boolean isOpen();

	/**
	 * Test if the client is still connected
	 * <p>
	 * Please note that the channel can be open but disconnected!
	 * 
	 * @return true if the client is still connected
	 * @see Channel#isConnected()
	 */
	boolean isConnected();

	/**
	 * Writes an packet to this client
	 * <p>
	 * Please note that this method will <b>not</b> block for the packets to be
	 * sent. It is possible to check if the packet was sent successfully using
	 * the {@link ChannelFuture}.
	 * 
	 * @param packet
	 *            the packet
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture write(ServerPacket packet);

	/**
	 * Sends a string message to this client
	 * 
	 * @param message
	 *            the message
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture sendMessage(String message);

	/**
	 * Sends a {@link SystemMessage} to this client
	 * 
	 * @param message
	 *            the {@link SystemMessage}
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture sendSystemMessage(SystemMessage message);

	/**
	 * Sends a {@link SystemMessage} to this client
	 * 
	 * @param message
	 *            the {@link SystemMessage}
	 * @param args
	 *            the arguments of the message, they will be automatically
	 *            detected and inserted. See <code>SM_SYSTEM_MESSAGE</code> for
	 *            more about supported formats.
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 * @see SystemMessage
	 */
	ChannelFuture sendSystemMessage(SystemMessage message, Object... args);

	/**
	 * Sends a <code>SM_ACTION_FAILED</code> to the client.
	 * <p>
	 * This is an convenience method for <blockquote><code>
	 * conn.write(ActionFailedPacket.SHARED_INSTANCE);</code></blockquote>
	 * 
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture sendActionFailed();

	/**
	 * Sends a <code>SM_HTML</code> packet to the client. In the packet, the NPC
	 * will be null. If you wish to send the HTML with an NPC, you should send
	 * the packet directly.
	 * <p>
	 * This is an convenience method for <blockquote><code>
	 * conn.write(new SM_HTML(null, template));</code></blockquote>
	 * 
	 * @param template
	 *            the HTML template to be sent
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture sendHTML(HtmlTemplate template);

	/**
	 * Sends a <code>SM_COMMUNITY_HTML</code> packet to the client. HTML code is
	 * not displayed in the regular chat window, they will appear in a large
	 * one.
	 * <p>
	 * This is an convenience method for <blockquote><code>
	 * conn.write(new SM_COMMUNITY_HTML(template));</code></blockquote>
	 * 
	 * @param template
	 *            the HTML template to be sent
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture sendCommunityHTML(HtmlTemplate template);

	/**
	 * Sends the whole user inventory to the client. This is a very bandwidth
	 * consuming process and should be avoided.
	 * 
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture updateEntireInventoryItems();

	/**
	 * Removes <code>items</code> from the game client UI.
	 * 
	 * @param items
	 *            the list of items to be removed
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture removeInventoryItems(Item... items);

	/**
	 * Updates <code>items</code> in the game client UI.
	 * 
	 * @param items
	 *            the list of items to be updated
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture updateInventoryItems(Item... items);

	/**
	 * Adds <code>items</code> in the game client UI.
	 * 
	 * @param items
	 *            the list of items to be added
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	ChannelFuture addInventoryItems(Item... items);

	/**
	 * Disconnects this client, without closing the channel.
	 * 
	 * @return the {@link ChannelFuture}
	 */
	ChannelFuture disconnect();

	/**
	 * Closes the channel of this client.
	 * 
	 * @return the {@link ChannelFuture}
	 */
	ChannelFuture close();

	@Override
	String toString();
}
