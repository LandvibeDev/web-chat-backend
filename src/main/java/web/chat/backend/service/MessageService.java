package web.chat.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.chat.backend.controller.response.MessageResponse;
import web.chat.backend.controller.response.collector.MessageCollector;
import web.chat.backend.entity.Message;
import web.chat.backend.exception.NotFoundException;
import web.chat.backend.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;

	public List<MessageResponse> getMessagesBy(Long roomId) {
		if (!messageRepository.existsByRoomId(roomId)) {
			throw new NotFoundException(roomId);
		}

		List<Message> messages = messageRepository.findAllByRoomId(roomId);
		return messages.stream().collect(new MessageCollector());
	}
}
