package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import java.util.Date;

import com.l2jserver.model.server.ChatMessage;
import com.l2jserver.service.game.chat.ChatMessageType;
import com.mysema.query.sql.PrimaryKey;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.EnumPath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;

/**
 * Maps <code>log_chat</code> table into type-safe java objects
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class QLogChat extends com.mysema.query.sql.RelationalPathBase<ChatMessage> {
	private static final long serialVersionUID = -76124357;

	public static final QLogChat logChat = new QLogChat("log_chat");

	public final NumberPath<Integer> channelId = createNumber("channel_id",
			Integer.class);

	public final DateTimePath<Date> date = createDateTime("date", Date.class);

	public final StringPath message = createString("message");

	public final NumberPath<Integer> messageId = createNumber("message_id",
			Integer.class);

	public final NumberPath<Integer> sender = createNumber("sender",
			Integer.class);

	public final EnumPath<ChatMessageType> type = createEnum("type",
			ChatMessageType.class);

	public final PrimaryKey<ChatMessage> primary = createPrimaryKey(messageId);

	public QLogChat(String variable) {
		super(ChatMessage.class, forVariable(variable), "null", "log_chat");
	}

	public QLogChat(Path<? extends ChatMessage> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "log_chat");
	}

	public QLogChat(PathMetadata<?> metadata) {
		super(ChatMessage.class, metadata, "null", "log_chat");
	}

}
