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

public class Lineage2PipelineFactory implements ChannelPipelineFactory {
	private final Injector injector;

	@Inject
	public Lineage2PipelineFactory(Injector injector) {
		this.injector = injector;
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

		pipeline.addLast("packet.writer", new Lineage2PacketWriter());
		pipeline.addLast("packet.reader", new Lineage2PacketReader(injector));

		pipeline.addLast("logger", new LoggingHandler(InternalLogLevel.DEBUG,
				true));

		pipeline.addLast("packet.handler", new Lineage2PacketHandler());

		return pipeline;
	}
}
