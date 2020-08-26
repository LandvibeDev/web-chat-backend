package web.chat.backend.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import web.chat.backend.service.MessageService;
import web.chat.backend.service.RoomService;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@WebMvcTest(RoomController.class)
class RoomControllerTest {

	final MockMvc mockMvc;
	final ObjectMapper objectMapper;

	@MockBean
	RoomService roomService;

	@MockBean
	MessageService messageService;

	@Test
	void getRooms() throws Exception {

		// given

		// when

		// then
	}

	@Test
	void createRoom() throws Exception {

		// given

		// when

		// then
	}

	@DisplayName("Room의 title 길이 2미만 불가능")
	@Test
	void createRoom_titleLengthLessThan_2() throws Exception {

		// given

		// when

		// then
	}

	@DisplayName("Room의 title 길이 20초과 불가능")
	@Test
	void createRoom_titleLengthExceed_20() throws Exception {

		// given

		// when

		// then
	}
}
