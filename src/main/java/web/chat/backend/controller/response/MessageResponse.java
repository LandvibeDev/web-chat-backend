package web.chat.backend.controller.response;

import java.time.LocalDateTime;

import lombok.Getter;
import web.chat.backend.entity.Message;
import web.chat.backend.entity.MessageType;

@Getter
public class MessageResponse {
	private Long id;

	private String contents;

	private LocalDateTime createdAt;

	private MessageType messageType;

	public MessageResponse(Message message) {
		this.id = message.getId();
		this.contents = message.getContents();
		this.createdAt = message.getCreatedAt();
		this.messageType = message.getMessageType();
	}
}
