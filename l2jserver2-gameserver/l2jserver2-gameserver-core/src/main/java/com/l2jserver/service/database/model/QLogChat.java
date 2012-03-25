package com.l2jserver.service.database.model;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import java.util.Date;

import com.l2jserver.service.database.ddl.annotation.ColumnAutoIncrement;
import com.l2jserver.service.database.ddl.annotation.ColumnNullable;
import com.l2jserver.service.database.ddl.annotation.ColumnSize;
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
	/**
	 * The Java Serialization UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default entity for {@link QLogChat}
	 */
	public static final QLogChat logChat = new QLogChat("log_chat");

	/**
	 * Column: <code>message_id</code>
	 */
	@ColumnSize(10)
	@ColumnAutoIncrement
	public final NumberPath<Integer> messageId = createNumber("message_id",
			Integer.class);

	/**
	 * Column: <code>channel_id</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> channelId = createNumber("channel_id",
			Integer.class);
	/**
	 * Column: <code>sender</code>
	 */
	@ColumnSize(10)
	@ColumnNullable
	public final NumberPath<Integer> sender = createNumber("sender",
			Integer.class);

	/**
	 * Column: <code>date</code>
	 */
	public final DateTimePath<Date> date = createDateTime("date", Date.class);

	/**
	 * Column: <code>message</code>
	 */
	@ColumnSize(255)
	public final StringPath message = createString("message");

	/**
	 * Column: <code>type</code>
	 */
	public final EnumPath<ChatMessageType> type = createEnum("type",
			ChatMessageType.class);
	/**
	 * The entity primary key
	 */
	public final PrimaryKey<Integer> primary = createPrimaryKey(messageId);

	/**
	 * Creates a new instance
	 * 
	 * @param variable
	 *            the query variable
	 */
	public QLogChat(String variable) {
		super(Integer.class, forVariable(variable), "null", "log_chat");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param entity
	 *            the parent entity
	 */
	public QLogChat(Path<? extends Integer> entity) {
		super(entity.getType(), entity.getMetadata(), "null", "log_chat");
	}

	/**
	 * Creates a new instance
	 * 
	 * @param metadata
	 *            the entity metadata
	 */
	public QLogChat(PathMetadata<?> metadata) {
		super(Integer.class, metadata, "null", "log_chat");
	}

}
