package web.chat.backend.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.TestConstructor;

import lombok.RequiredArgsConstructor;
import web.chat.backend.entity.Message;
import web.chat.backend.entity.MessageType;
import web.chat.backend.entity.Room;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@EnableJpaAuditing
@DataJpaTest
class MessageRepositoryTest {

	final TestEntityManager entityManager;

	final MessageRepository messageRepository;

	@Test
	void save() {

		// given
		Room room = new Room();
		room.setTitle("room");

		entityManager.persist(room);

		Message message = new Message();
		message.setContents("hi hello");
		message.setRoom(room);

		// when
		Message savedMessage = messageRepository.save(message);

		// then
		assertThat(savedMessage.getId()).isNotNull();
		assertThat(savedMessage.getContents()).isEqualTo(message.getContents());
		assertThat(savedMessage.getCreatedAt()).isNotNull();
		assertThat(savedMessage.getRoom()).isNotNull();
		assertThat(savedMessage.getRoom().getTitle()).isEqualTo(room.getTitle());
	}

	@DisplayName("Room ID = 1 인 채팅방에 존재하는 모든 메시지 조회")
	@Test
	void shouldFindMessageEntities_WithRoomId_1() {
		// given
		Room room = new Room();
		room.setTitle("spring study");

		Room givenRoom = entityManager.persist(room);

		List<Message> givenMessages = Stream.of(1, 2, 3)
			.map((id) -> {
				Message message = new Message();
				message.setContents("contents_" + id);
				message.setMessageType(MessageType.TEXT);
				message.setRoom(givenRoom);
				return entityManager.persist(message);
			})
			.collect(Collectors.toList());

		// when
		List<Message> messages = messageRepository.findAllByRoomId(givenRoom.getId());

		// then
		assertThat(messages)
			.hasSameSizeAs(givenMessages)
			.usingRecursiveComparison()
			.isEqualTo(givenMessages);
	}
}
