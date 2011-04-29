package com.l2jserver.game.net.codec;

import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class Lineage2Encoder extends LengthFieldPrepender {
	public Lineage2Encoder() {
		super(2);
	}
}
