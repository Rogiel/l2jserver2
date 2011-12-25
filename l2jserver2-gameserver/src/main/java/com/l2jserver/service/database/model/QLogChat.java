package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import java.util.Date;

import com.l2jserver.service.database.sql.ddl.annotation.ColumnAutoIncrement;
import com.l2jserver.service.database.sql.ddl.annotation.ColumnNullable;
import com.l2jserver.service.database.sql.ddl.annotation.ColumnSize;
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
public class QLogChat extends com.mysema.query.sql.RelationalPathBase<Integer> {
	private static final long serialVersionUID = -76124357;

	public static final QLogChat logChat = new QLogChat("log_chat");

	@ColumnSize(10)
	@ColumnAutoIncrement
	public final NumberPath<Integer> messageId = createNumber("message_id",
			Integer.class);

	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> channelId = createNumber("channel_id",
			Integer.class);
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> sender = createNumber("sender",
			Integer.class);

	public final DateTimePath<Date> date = createDateTime("date", Date.class);

	@ColumnSize(255)
	public final StringPath message = createString("message");

	public final EnumPath<ChatMessageType> type = createEnum("type",
			ChatMessageType.class);

	public final PrimaryKey<Integer> primary = createPrimaryKey(messageId);

	public QLogChat(String variable) {
		super(Integer.class, forVariable(variable), "null", "log_chat");
	}

	public QLogChat(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "log_chat");
	}

	public QLogChat(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "log_chat");
	}

}
