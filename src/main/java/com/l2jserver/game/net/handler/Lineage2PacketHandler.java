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
package com.l2jserver.game.net.handler;

import java.io.IOException;

import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.google.common.base.Throwables;
import com.l2jserver.game.net.Lineage2Connection;
import com.l2jserver.game.net.packet.ClientPacket;
import com.l2jserver.game.net.packet.server.SM_HTML;
import com.l2jserver.service.game.world.WorldService;
import com.l2jserver.service.network.NettyNetworkService;
import com.l2jserver.util.html.markup.HtmlTemplate;
import com.l2jserver.util.html.markup.MarkupTag;

/**
 * This handler dispatches the {@link ClientPacket#process(Lineage2Connection)}
 * method and creates a new {@link Lineage2Connection} once a new channel is
 * open.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2PacketHandler extends SimpleChannelHandler {
	/**
	 * The {@link NettyNetworkService}
	 */
	private final NettyNetworkService nettyNetworkService;
	/**
	 * The {@link WorldService} instance
	 */
	private final WorldService worldService;
	/**
	 * The Lineage 2 connection
	 */
	private Lineage2Connection connection;

	public Lineage2PacketHandler(NettyNetworkService nettyNetworkService,
			WorldService worldService) {
		this.nettyNetworkService = nettyNetworkService;
		this.worldService = worldService;
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		connection = new Lineage2Connection(worldService, nettyNetworkService,
				e.getChannel());
		connection.getPacketWriter().setConnection(connection);

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
			connection.write(new SM_HTML(null, template));
			connection.sendActionFailed(); // order client not to wait any packet
	
			final String[] lines = Throwables.getStackTraceAsString(e).split("\n");
			for(final String line : lines) {
				connection.sendMessage(line);
			}
		} finally {
			e.printStackTrace();
		}
	}
}
