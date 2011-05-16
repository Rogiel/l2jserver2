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

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.l2jserver.game.ProtocolVersion;
import com.l2jserver.game.net.codec.Lineage2Decrypter;
import com.l2jserver.game.net.codec.Lineage2Encrypter;
import com.l2jserver.game.net.codec.Lineage2PacketReader;
import com.l2jserver.game.net.codec.Lineage2PacketWriter;
import com.l2jserver.game.net.packet.ServerPacket;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.model.world.L2Character;

/**
 * This object connects the model (structure objects normally stored in the
 * database) to the controller (protocol stuff).
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

	/**
	 * Creates a new instance
	 * 
	 * @param channel
	 *            the channel
	 */
	public Lineage2Connection(Channel channel) {
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
	 * @see com.l2jserver.game.ProtocolVersion#supports(com.l2jserver.game.ProtocolVersion)
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

	public boolean isOpen() {
		return channel.isOpen();
	}

	public boolean isConnected() {
		return channel.isConnected();
	}

	public ChannelFuture write(ServerPacket message) {
		return channel.write(message);
	}

	public ChannelFuture disconnect() {
		return channel.disconnect();
	}

	public ChannelFuture close() {
		return channel.close();
	}

	public Lineage2Decrypter getDecrypter() {
		return (Lineage2Decrypter) channel.getPipeline().get(
				Lineage2Decrypter.HANDLER_NAME);
	}

	public Lineage2Encrypter getEncrypter() {
		return (Lineage2Encrypter) channel.getPipeline().get(
				Lineage2Encrypter.HANDLER_NAME);
	}

	public Lineage2PacketReader getPacketReader() {
		return (Lineage2PacketReader) channel.getPipeline().get(
				Lineage2PacketReader.HANDLER_NAME);
	}

	public Lineage2PacketWriter getPacketWriter() {
		return (Lineage2PacketWriter) channel.getPipeline().get(
				Lineage2PacketWriter.HANDLER_NAME);
	}
}
