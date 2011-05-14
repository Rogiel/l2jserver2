package com.l2jserver.game.net;

/**
 * Lineage 2 session with the username and loginserver keys
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class Lineage2Session {
	/**
	 * The username
	 */
	private final String username;

	/**
	 * The play key, part 1
	 */
	private final int playKey1;
	/**
	 * The play key, part 2
	 */
	private final int playKey2;

	/**
	 * The login key, part 1
	 */
	private final int loginKey1;
	/**
	 * The login key, part 2
	 */
	private final int loginKey2;

	/**
	 * Creates a new instance
	 * 
	 * @param username
	 *            the username
	 * @param playOK1
	 *            the play key, part 1
	 * @param playOK2
	 *            the play key, part 2
	 * @param loginOK1
	 *            the login key, part 1
	 * @param loginOK2
	 *            the login key, part 2
	 */
	public Lineage2Session(String username, int playOK1, int playOK2,
			int loginOK1, int loginOK2) {
		this.username = username;
		this.playKey1 = playOK1;
		this.playKey2 = playOK2;
		this.loginKey1 = loginOK1;
		this.loginKey2 = loginOK2;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the playKey1
	 */
	public int getPlayKey1() {
		return playKey1;
	}

	/**
	 * @return the playKey2
	 */
	public int getPlayKey2() {
		return playKey2;
	}

	/**
	 * @return the loginKey1
	 */
	public int getLoginKey1() {
		return loginKey1;
	}

	/**
	 * @return the loginKey2
	 */
	public int getLoginKey2() {
		return loginKey2;
	}
}
