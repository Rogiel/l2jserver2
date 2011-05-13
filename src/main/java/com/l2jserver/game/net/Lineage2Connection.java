package com.l2jserver.game.net;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.l2jserver.game.net.codec.Lineage2Decrypter;
import com.l2jserver.game.net.codec.Lineage2Encrypter;
import com.l2jserver.game.net.packet.ServerPacket;
import com.l2jserver.model.world.L2Character;

/**
 * This object connects the model (structure objects normally stored in the
 * database) to the controller (protocol stuff).
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2Connection {
	private final Channel channel;
	private L2Character character;
	private Lineage2Session session;
	private ConnectionState state = ConnectionState.CONNECTED;

	public enum ConnectionState {
		CONNECTED, AUTHENTICATED, IN_GAME;
	}

	public Lineage2Connection(Channel channel) {
		this.channel = channel;
	}

	/**
	 * @return the character
	 */
	public boolean hasCharacter() {
		return character != null;
	}

	/**
	 * @return the character
	 */
	public L2Character getCharacter() {
		return character;
	}

	/**
	 * @param character
	 *            the character to set
	 */
	public void setCharacter(L2Character character) {
		this.character = character;
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
}
