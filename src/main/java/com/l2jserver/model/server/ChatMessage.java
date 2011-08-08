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
package com.l2jserver.model.server;

import java.util.Date;

import com.l2jserver.model.AbstractModel;
import com.l2jserver.model.Model;
import com.l2jserver.model.id.ChatMessageID;
import com.l2jserver.model.id.object.CharacterID;
import com.l2jserver.service.game.chat.ChatMessageType;

/**
 * This is an chat message stored in the database for logging purposes. It can,
 * however, be used as a form of flood-checking.
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class ChatMessage extends AbstractModel<ChatMessageID> implements
		Model<ChatMessageID> {
	/**
	 * The chat message type.
	 * <p>
	 * If {@link ChatMessageType#SHOUT} <tt>target</tt> will be set and
	 * <tt>channelID</tt> will be <tt>null</tt>.<br>
	 * Otherwise, <tt>target</tt> is <tt>null</tt> and <tt>channelID</tt> will
	 * be set.
	 */
	private ChatMessageType type;
	/**
	 * The channel numeric ID
	 */
	private int channelID;
	/**
	 * The message target ID, if any.
	 */
	private CharacterID target;

	/**
	 * The sender ID, if any.
	 */
	private CharacterID sender;
	/**
	 * The message log date
	 */
	private Date date;
	/**
	 * The message content
	 */
	private String message;

	/**
	 * @return the type
	 */
	public ChatMessageType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ChatMessageType type) {
		this.type = type;
	}

	/**
	 * @return the channelID
	 */
	public int getChannelID() {
		return channelID;
	}

	/**
	 * @param channelID
	 *            the channelID to set
	 */
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	/**
	 * @return the target
	 */
	public CharacterID getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(CharacterID target) {
		this.target = target;
	}

	/**
	 * @return the sender
	 */
	public CharacterID getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(CharacterID sender) {
		this.sender = sender;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ChatMessage [" + sender + "@" + date + ": " + message + "]";
	}
}
