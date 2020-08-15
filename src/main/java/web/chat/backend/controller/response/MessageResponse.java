package web.chat.backend.controller.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import web.chat.backend.entity.MessageType;

@Getter
public class MessageResponse {
	private Long id;

	private String contents;

	private LocalDateTime createdAt;

	private MessageType messageType;

	@Builder
	protected MessageResponse(Long id, String contents, LocalDateTime createdAt, MessageType messageType) {
		this.id = id;
		this.contents = contents;
		this.createdAt = createdAt;
		this.messageType = messageType;
	}
}
