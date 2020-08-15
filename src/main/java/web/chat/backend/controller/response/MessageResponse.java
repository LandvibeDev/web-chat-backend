package web.chat.backend.controller.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import web.chat.backend.entity.MessageType;

@Getter
@Setter
public class MessageResponse {
	private Long id;

	private String contents;

	private LocalDateTime createdAt;

	private MessageType messageType;
}
