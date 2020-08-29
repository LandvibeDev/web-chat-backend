package web.chat.backend.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestConstructor;

import lombok.RequiredArgsConstructor;
import web.chat.backend.entity.Message;
import web.chat.backend.entity.MessageType;
import web.chat.backend.entity.Room;

/**
 * Created by koseungbin on 2020-08-30
 */

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@DataJpaTest
class MessageRepositoryTest {

	final TestEntityManager entityManager;

	final MessageRepository messageRepository;

	@DisplayName("Room ID = 1 인 채팅방에 존재하는 모든 메시지 조회")
	@Test
	void shouldFindMessageEntities_WithRoomId_1() {
		// given
		Room room = new Room();
		room.setTitle("spring study");

		Room savedRoom = entityManager.persist(room);

		List<Message> savedMessages = Stream.of(1, 2, 3)
			.map((id) -> {
				Message message = new Message();
				message.setContents("contents_" + id);
				message.setMessageType(MessageType.TEXT);
				message.setRoom(savedRoom);
				return entityManager.persist(message);
			}).collect(Collectors.toList());

		// when
		List<Message> messages = messageRepository.findAllByRoomId(savedRoom.getId());

		// then
		assertEquals(savedMessages.size(), messages.size());
		assertIterableEquals(savedMessages, messages);
	}
}
