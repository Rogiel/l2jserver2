package com.l2jserver.game.net;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

import com.l2jserver.game.net.codec.Lineage2Decoder;
import com.l2jserver.game.net.codec.Lineage2Decrypter;
import com.l2jserver.game.net.codec.Lineage2Encoder;
import com.l2jserver.game.net.codec.Lineage2Encrypter;
import com.l2jserver.game.net.codec.Lineage2PacketReader;
import com.l2jserver.game.net.codec.Lineage2PacketWriter;
import com.l2jserver.game.net.handler.Lineage2PacketHandler;

public class Lineage2PipelineFactory implements ChannelPipelineFactory {
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		final ChannelPipeline pipeline = pipeline();

		pipeline.addLast(Lineage2Encrypter.HANDLER_NAME,
				new Lineage2Encrypter());
		pipeline.addLast(Lineage2Decrypter.HANDLER_NAME,
				new Lineage2Decrypter());

		pipeline.addLast("header.encoder", new Lineage2Encoder());
		pipeline.addLast("header.decoder", new Lineage2Decoder());

		pipeline.addLast("packet.writer", new Lineage2PacketWriter());
		pipeline.addLast("packet.reader", new Lineage2PacketReader());

		pipeline.addLast("packet.handler", new Lineage2PacketHandler());

		return pipeline;
	}
}
