package com.l2jserver;

import com.l2jserver.routines.GameServerInitializationRoutine;

public class L2JGameServerMain {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final L2JGameServer server = new L2JGameServer();
		try {
			server.getInjector()
					.getInstance(GameServerInitializationRoutine.class).call();
		} catch (Exception e) {
			System.out.println("GameServer could not be started!");
			e.printStackTrace();
		}
		
		Thread.sleep(60 * 60 * 1000);
	}

}
