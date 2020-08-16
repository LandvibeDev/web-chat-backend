package web.chat.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import web.chat.backend.controller.response.MessageResponse;
import web.chat.backend.controller.response.collector.MessageCollector;
import web.chat.backend.entity.Message;
import web.chat.backend.service.MessageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {
	private final MessageService messageService;

	@GetMapping(value = "/{id}/messages")
	@ResponseStatus(HttpStatus.OK)
	public List<MessageResponse> getMessages(@PathVariable Long id) {
		List<Message> messages = messageService.getMessagesBy(id);
		return messages.stream().collect(new MessageCollector());
	}
}
