package com.l2jserver.game.net.codec;

import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class Lineage2LSFrameEncoder extends LengthFieldPrepender {
	public Lineage2LSFrameEncoder() {
		super(2);
	}
}
