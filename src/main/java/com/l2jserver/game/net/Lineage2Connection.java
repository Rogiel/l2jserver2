package com.l2jserver.game.net;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import com.l2jserver.game.net.codec.Lineage2Decrypter;
import com.l2jserver.game.net.codec.Lineage2Encrypter;
import com.l2jserver.game.net.packet.ServerPacket;

public class Lineage2Connection {
	private final Channel channel;

	public Lineage2Connection(Channel channel) {
		this.channel = channel;
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
