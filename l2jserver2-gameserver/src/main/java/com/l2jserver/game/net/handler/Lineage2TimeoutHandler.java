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

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import com.l2jserver.game.net.Lineage2Client;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class Lineage2TimeoutHandler extends IdleStateAwareChannelHandler {
	/**
	 * The Lineage 2 connection
	 */
	private Lineage2Client connection;

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e)
			throws Exception {
		if (connection != null) {
			connection.close();
		}
		super.channelIdle(ctx, e);
	}

	/**
	 * @return the connection
	 */
	protected Lineage2Client getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	protected void setConnection(Lineage2Client connection) {
		this.connection = connection;
	}
}
