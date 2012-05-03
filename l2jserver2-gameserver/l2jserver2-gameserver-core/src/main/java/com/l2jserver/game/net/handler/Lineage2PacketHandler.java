/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.game.net.handler;

import java.io.IOException;

import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.google.common.base.Throwables;
import com.l2jserver.game.net.Lineage2ClientImpl;
import com.l2jserver.service.network.AbstractNettyNetworkService;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.service.network.model.packet.ClientPacket;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * This handler dispatches the {@link ClientPacket#process(Lineage2Client)}
 * method and creates a new {@link Lineage2ClientImpl} once a new channel is open.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2PacketHandler extends SimpleChannelHandler {
	/**
	 * The {@link AbstractNettyNetworkService}
	 */
	private final AbstractNettyNetworkService nettyNetworkService;
	/**
	 * The timeout handler is responsible for disconnecting idle clients.
	 */
	private final Lineage2TimeoutHandler timeoutHandler;
	/**
	 * The Lineage 2 connection
	 */
	private Lineage2ClientImpl connection;

	/**
	 * Creates a new instance of the packet handler
	 * 
	 * @param nettyNetworkService
	 *            the netty network service
	 * @param timeoutHandler
	 *            the timeout handler
	 */
	public Lineage2PacketHandler(AbstractNettyNetworkService nettyNetworkService,
			Lineage2TimeoutHandler timeoutHandler) {
		this.nettyNetworkService = nettyNetworkService;
		this.timeoutHandler = timeoutHandler;
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		connection = new Lineage2ClientImpl(e.getChannel());
		connection.getPacketWriter().setConnection(connection);
		timeoutHandler.setConnection(connection);

		nettyNetworkService.register(connection);

		super.channelOpen(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		final Object msg = e.getMessage();
		if (!(msg instanceof ClientPacket))
			return;
		final ClientPacket packet = (ClientPacket) msg;
		packet.process(connection);
		super.messageReceived(ctx, e);
	}

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.writeRequested(ctx, e);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		nettyNetworkService.unregister(connection);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent event)
			throws Exception {
		final Throwable e = event.getCause();
		try {
			if (e instanceof ChannelException)
				return;
			if (e instanceof IOException)
				return;
			if (!connection.isConnected())
				// no point sending error messages if the client is disconnected
				return;

			// TODO only send exception stack trace in development mode!
			final String exception = Throwables.getStackTraceAsString(e)
					.replaceAll("\n", "<br>").replace("	", "");
			final HtmlTemplate template = new HtmlTemplate("Java Exception") {
				@Override
				public void build(MarkupTag body) {
					body.text(exception);
				}
			};
			connection.sendHTML(template);
			// order client not to wait any packet
			connection.sendActionFailed();

			final String[] lines = Throwables.getStackTraceAsString(e).split(
					"\n");
			for (final String line : lines) {
				connection.sendMessage(line);
			}
		} finally {
			e.printStackTrace();
		}
	}
}
