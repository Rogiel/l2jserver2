package com.l2jserver.game.net.codec;

import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

public class Lineage2LSFrameDecoder extends LengthFieldBasedFrameDecoder {
	public Lineage2LSFrameDecoder() {
		super(16 * 1024, 0, 2);
	}
}
