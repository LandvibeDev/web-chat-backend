package web.chat.backend.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.RequiredArgsConstructor;
import web.chat.backend.entity.Message;
import web.chat.backend.entity.MessageType;
import web.chat.backend.exception.NotFoundException;
import web.chat.backend.repository.MessageRepository;

/**
 * Created by koseungbin on 2020-08-29
 */

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class MessageServiceTest {

	@InjectMocks
	MessageService messageService;

	@Mock
	MessageRepository messageRepository;

	@DisplayName("Room 존재하지 않는 경우 NotFoundException 에러 발생")
	@Test
	void shouldThrowNotFoundException_ifNotExist_roomId() {
		// given
		Long roomId = 1L;
		given(messageRepository.existsByRoomId(roomId)).willReturn(false);

		// when, then
		assertThatExceptionOfType(NotFoundException.class)
			.isThrownBy(() -> messageService.getMessagesBy(roomId))
			.withMessage("%d does not exist.", roomId)
			.withNoCause();
	}

	@DisplayName("Room 존재하는 경우 메시지 리스트 반환")
	@Test
	void shouldMessages_ifExist_roomId() {
		// given
		Long roomId = 1L;
		given(messageRepository.existsByRoomId(roomId)).willReturn(true);

		List<Message> givenMessages = Stream.of(1, 2, 3).map((id) -> {
			Message message = new Message();
			message.setId((long)id);
			message.setContents("contents_" + id);
			message.setMessageType(MessageType.TEXT);
			return message;
		}).collect(Collectors.toList());

		given(messageRepository.findAllByRoomId(roomId)).willReturn(givenMessages);

		// when
		List<Message> messages = messageService.getMessagesBy(roomId);

		// then
		assertThat(messages)
			.hasSameSizeAs(givenMessages)
			.usingRecursiveComparison()
			.isEqualTo(givenMessages);
	}

	@DisplayName("Room 존재하는 경우 Room ID = 1 으로 메시드를 한 번씩 호출")
	@Test
	void verifyMessages_ifExist_roomId() {
		// given
		Long roomId = 1L;
		given(messageRepository.existsByRoomId(roomId)).willReturn(true);

		// when
		messageService.getMessagesBy(roomId);

		// then
		then(messageRepository).should(times(1)).existsByRoomId(roomId);
		then(messageRepository).should(times(1)).findAllByRoomId(roomId);
	}
}
