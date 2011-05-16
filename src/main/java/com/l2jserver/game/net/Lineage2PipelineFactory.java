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

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.logging.LoggingHandler;
import org.jboss.netty.logging.InternalLogLevel;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.l2jserver.game.net.codec.Lineage2Decrypter;
import com.l2jserver.game.net.codec.Lineage2Encrypter;
import com.l2jserver.game.net.codec.Lineage2FrameDecoder;
import com.l2jserver.game.net.codec.Lineage2FrameEncoder;
import com.l2jserver.game.net.codec.Lineage2PacketReader;
import com.l2jserver.game.net.codec.Lineage2PacketWriter;
import com.l2jserver.game.net.handler.Lineage2PacketHandler;
import com.l2jserver.service.network.NettyNetworkService;

/**
 * This class creates a new instance of {@link ChannelPipeline} and attaches all
 * handlers and codecs.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2PipelineFactory implements ChannelPipelineFactory {
	/**
	 * The Google Guice {@link Injector}.
	 */
	private final Injector injector;
	/**
	 * The {@link NettyNetworkService}
	 */
	private final NettyNetworkService nettyNetworkService;

	@Inject
	public Lineage2PipelineFactory(Injector injector,
			NettyNetworkService nettyNetworkService) {
		this.injector = injector;
		this.nettyNetworkService = nettyNetworkService;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		final ChannelPipeline pipeline = pipeline();

		pipeline.addLast("frame.encoder", new Lineage2FrameEncoder());
		pipeline.addLast("frame.decoder", new Lineage2FrameDecoder());

		pipeline.addLast(Lineage2Encrypter.HANDLER_NAME,
				new Lineage2Encrypter());
		pipeline.addLast(Lineage2Decrypter.HANDLER_NAME,
				new Lineage2Decrypter());

		pipeline.addLast("logger-hex", new LoggingHandler(
				InternalLogLevel.DEBUG, true));

		pipeline.addLast(Lineage2PacketWriter.HANDLER_NAME,
				new Lineage2PacketWriter());
		pipeline.addLast(Lineage2PacketReader.HANDLER_NAME,
				new Lineage2PacketReader(injector));

		pipeline.addLast("logger", new LoggingHandler(InternalLogLevel.DEBUG,
				true));

		pipeline.addLast("packet.handler", new Lineage2PacketHandler(nettyNetworkService));

		return pipeline;
	}
}
