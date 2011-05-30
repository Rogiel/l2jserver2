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
package com.l2jserver.game.net;

import java.util.Set;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.l2jserver.game.net.codec.Lineage2Decrypter;
import com.l2jserver.game.net.codec.Lineage2Encrypter;
import com.l2jserver.game.net.codec.Lineage2PacketReader;
import com.l2jserver.game.net.codec.Lineage2PacketWriter;
import com.l2jserver.game.net.packet.ServerPacket;
import com.l2jserver.game.net.packet.server.SM_ACTION_FAILED;
import com.l2jserver.game.net.packet.server.SM_SYSTEM_MESSAGE;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.template.ItemTemplate;
import com.l2jserver.model.world.Item;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.game.world.filter.impl.CharacterBroadcastFilter;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * This object connects the model (structure objects normally stored in the
 * database) to the controller (protocol stuff).
 * <p>
 * This class also provides handy methods for {@link #write(ServerPacket)
 * writing} and {@link #broadcast(ServerPacket) broadcasting} packets.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2Connection {
	/**
	 * The connection channel
	 */
	private final Channel channel;
	/**
	 * The character object
	 */
	private CharacterID characterID;
	/**
	 * The Lineage 2 session
	 */
	private Lineage2Session session;
	/**
	 * The connection state
	 */
	private ConnectionState state = ConnectionState.CONNECTED;

	/**
	 * Each connection is represented by an state: connected, authenticated and
	 * in-game.
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	public enum ConnectionState {
		CONNECTED, AUTHENTICATED, IN_GAME;
	}

	/**
	 * The client supported protocol version
	 */
	private ProtocolVersion version;

	// services
	/**
	 * The {@link NetworkService} instance. This service is used to retrieve the
	 * {@link Lineage2Connection} based on an {@link CharacterID}.
	 */
	private final NetworkService networkService;
	/**
	 * The {@link WorldService} instance. This service is used to dynamically
	 * generate knownlists.
	 */
	private final WorldService worldService;

	/**
	 * Creates a new instance
	 * 
	 * @param worldService
	 *            the world service
	 * @param channel
	 *            the channel
	 */
	public Lineage2Connection(WorldService worldService,
			NetworkService networkService, Channel channel) {
		this.worldService = worldService;
		this.networkService = networkService;
		this.channel = channel;
	}

	/**
	 * @return the character
	 */
	public boolean hasCharacter() {
		return characterID != null;
	}

	/**
	 * @return the character ID
	 */
	public CharacterID getCharacterID() {
		return characterID;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return characterID.getObject();
	}

	/**
	 * @param characterID
	 *            the character ID to set
	 */
	public void setCharacterID(CharacterID characterID) {
		this.characterID = characterID;
	}

	/**
	 * @return the session
	 */
	public Lineage2Session getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Lineage2Session session) {
		if (this.session != null)
			throw new IllegalStateException("Session is already set!");
		this.session = session;
	}

	/**
	 * @return the state
	 */
	public ConnectionState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(ConnectionState state) {
		this.state = state;
	}

	/**
	 * @return the version
	 */
	public ProtocolVersion getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(ProtocolVersion version) {
		this.version = version;
	}

	/**
	 * Check if the client supports an given version of the protocol. Note that
	 * if the protocol is not known false will always be returned.
	 * 
	 * @param version
	 * @return true if version is supported by the client
	 * @see com.l2jserver.game.net.ProtocolVersion#supports(com.l2jserver.game.net.ProtocolVersion)
	 */
	public boolean supports(ProtocolVersion version) {
		if (version == null)
			return false;
		return version.supports(version);
	}

	/**
	 * Get the channel
	 * 
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * Test if the client is still open.
	 * <p>
	 * Please note that the channel can be open but disconnected!
	 * 
	 * @return true if the client is still open
	 * @see Channel#isOpen()
	 */
	public boolean isOpen() {
		return channel.isOpen();
	}

	/**
	 * Test if the client is still connected
	 * <p>
	 * Please note that the channel can be open but disconnected!
	 * 
	 * @return true if the client is still connected
	 * @see Channel#isConnected()
	 */
	public boolean isConnected() {
		return channel.isConnected();
	}

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
	public ChannelFuture write(ServerPacket packet) {
		return channel.write(packet);
	}

	/**
	 * Sends a string message to this client
	 * 
	 * @param message
	 *            the message
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	public ChannelFuture sendMessage(String message) {
		return write(new SM_SYSTEM_MESSAGE(SystemMessage.S1)
				.addString(message));
	}

	/**
	 * Sends a {@link SystemMessage} to this client
	 * 
	 * @param message
	 *            the {@link SystemMessage}
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	public ChannelFuture sendSystemMessage(SystemMessage message) {
		return write(message.packet);
	}

	/**
	 * Sends a {@link SystemMessage} to this client
	 * 
	 * @param message
	 *            the {@link SystemMessage}
	 * @param args
	 *            the arguments of the message, they will be automatically
	 *            detected and inserted. See {@link SM_SYSTEM_MESSAGE} for
	 *            more about supported formats.
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 * @see SM_SYSTEM_MESSAGE
	 */
	public ChannelFuture sendSystemMessage(SystemMessage message,
			Object... args) {
		final SM_SYSTEM_MESSAGE packet = new SM_SYSTEM_MESSAGE(message);
		for (final Object obj : args) {
			if (obj instanceof String)
				packet.addString((String) obj);
			else if (obj instanceof ItemTemplate)
				packet.addItem((ItemTemplate) obj);
			else if (obj instanceof Item)
				packet.addItem((Item) obj);
		}
		return write(message.packet);
	}

	/**
	 * Sends a {@link SM_ACTION_FAILED} to the client.
	 * <p>
	 * This is an convenience method for <blockquote><code>
	 * conn.write(ActionFailedPacket.SHARED_INSTANCE);</code></blockquote>
	 * 
	 * @return the {@link ChannelFuture} that will be notified once the packet
	 *         has been written.
	 */
	public ChannelFuture sendActionFailed() {
		return write(SM_ACTION_FAILED.SHARED_INSTANCE);
	}

	/**
	 * Broadcast a packet to all characters in this character knownlist.
	 * <p>
	 * Note that in the broadcasting process, this client will be included.
	 * <p>
	 * Please note that this method will <b>not</b> block for all packets to be
	 * sent. It is possible to check if all packets were sent successfully using
	 * the {@link ChannelFuture} instances.
	 * 
	 * @param packet
	 *            the packet
	 * @return an {@link Set} containing all {@link ChannelFuture} instances.
	 */
	public Set<ChannelFuture> broadcast(ServerPacket packet) {
		final Set<ChannelFuture> futures = CollectionFactory.newSet();
		for (final L2Character character : worldService
				.iterable(new CharacterBroadcastFilter(characterID.getObject()))) {
			final Lineage2Connection conn = networkService.discover(character
					.getID());
			if (conn == null)
				continue;
			futures.add(conn.write(packet));
		}
		return futures;
	}

	/**
	 * Disconnects this client, without closing the channel.
	 * 
	 * @return the {@link ChannelFuture}
	 */
	public ChannelFuture disconnect() {
		return channel.disconnect();
	}

	/**
	 * Closes the channel of this client.
	 * 
	 * @return the {@link ChannelFuture}
	 */
	public ChannelFuture close() {
		return channel.close();
	}

	/**
	 * @return the client {@link Lineage2Decrypter}
	 */
	public Lineage2Decrypter getDecrypter() {
		return (Lineage2Decrypter) channel.getPipeline().get(
				Lineage2Decrypter.HANDLER_NAME);
	}

	/**
	 * @return the client {@link Lineage2Encrypter}
	 */
	public Lineage2Encrypter getEncrypter() {
		return (Lineage2Encrypter) channel.getPipeline().get(
				Lineage2Encrypter.HANDLER_NAME);
	}

	/**
	 * @return the client {@link Lineage2PacketReader}
	 */
	public Lineage2PacketReader getPacketReader() {
		return (Lineage2PacketReader) channel.getPipeline().get(
				Lineage2PacketReader.HANDLER_NAME);
	}

	/**
	 * @return the client {@link Lineage2PacketWriter}
	 */
	public Lineage2PacketWriter getPacketWriter() {
		return (Lineage2PacketWriter) channel.getPipeline().get(
				Lineage2PacketWriter.HANDLER_NAME);
	}
}
