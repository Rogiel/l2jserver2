package com.l2jserver.game.net.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.l2jserver.game.net.packet.ClientPacket;

public class Lineage2PacketHandler extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		final Object msg = e.getMessage();
		if (!(msg instanceof ClientPacket))
			return;
		final ClientPacket packet = (ClientPacket) msg;
		packet.process(null);
		super.messageReceived(ctx, e);
	}

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.writeRequested(ctx, e);
	}
}
