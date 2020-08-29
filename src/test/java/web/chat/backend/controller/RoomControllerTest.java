package web.chat.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import web.chat.backend.controller.request.RoomRequest;
import web.chat.backend.controller.response.RoomResponse;
import web.chat.backend.entity.Room;
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
		RoomRequest req = new RoomRequest();
		req.setTitle("title1");

		final String body = objectMapper.writeValueAsString(req);

		Room room = Room.builder()
			.title(req.getTitle())
			.build();

		String expected = objectMapper.writeValueAsString(RoomResponse.create(room));

		given(roomService.createRoom(any())).willReturn(room);

		// when
		ResultActions action = mockMvc.perform(
			post("/api/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// then
		action.andExpect(status().isCreated())
			.andExpect(content().string(expected))
			.andDo(print());
	}

	@DisplayName("Room의 title 길이 2미만 불가능")
	@Test
	void createRoom_titleLengthLessThan_2() throws Exception {

		// given
		RoomRequest req = new RoomRequest();
		req.setTitle("1");

		final String body = objectMapper.writeValueAsString(req);

		// when
		ResultActions action = mockMvc.perform(
			post("/api/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));
		// then
		action.andExpect(status().is4xxClientError())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
			.andDo(print());
	}

	@DisplayName("Room의 title 길이 20초과 불가능")
	@Test
	void createRoom_titleLengthExceed_20() throws Exception {

		// given

		// when

		// then
	}
}
