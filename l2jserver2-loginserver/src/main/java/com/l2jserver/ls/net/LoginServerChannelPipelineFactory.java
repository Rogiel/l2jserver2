package com.l2jserver.ls.net;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

import com.l2jserver.service.gameserver.GameServerNetworkService;

public class LoginServerChannelPipelineFactory implements
		ChannelPipelineFactory {
	@SuppressWarnings("unused")
	private final GameServerNetworkService networkService;

	public LoginServerChannelPipelineFactory(
			GameServerNetworkService networkService) {
		this.networkService = networkService;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		final ChannelPipeline pipeline = pipeline();

		// pipeline.addLast("ssl", new SslHandler());
		pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(
				1048576, 0, 4, 0, 4));

		// pipeline.addLast("encoder", new ObjectEncoder());
		// pipeline.addLast("decoder", new ObjectDecoder());

		return null;
	}
}
