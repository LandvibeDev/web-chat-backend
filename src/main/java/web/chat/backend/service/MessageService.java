package web.chat.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.chat.backend.entity.Message;
import web.chat.backend.entity.Room;
import web.chat.backend.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;

	public List<Message> getMessagesBy(Long roomId) {
		return messageRepository.findAllByRoomId(roomId);
	}

	public Message createMessage(Room room, Message message) {
		message.setRoom(room);

		return messageRepository.save(message);
	}
}
