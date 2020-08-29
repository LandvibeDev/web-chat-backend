package web.chat.backend.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

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
		Room r1 = Room.builder()
			.id(1L)
			.title("test1")
			.build();
		Room r2 = Room.builder()
			.id(2L)
			.title("test2")
			.build();
		Room r3 = Room.builder()
			.id(3L)
			.title("test3")
			.build();
		List<Room> roomList = Arrays.asList(r1, r2, r3);

		given(roomService.getRooms()).willReturn(roomList);

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/rooms"));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.rooms", not(empty())))
			.andExpect(jsonPath("$.rooms", hasSize(3)))
			.andExpect(jsonPath("$.rooms[0].id", is(1)))
			.andExpect(jsonPath("$.rooms[1].id", is(2)))
			.andExpect(jsonPath("$.rooms[2].id", is(3)))
			.andDo(print());
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
		RoomRequest req = new RoomRequest();
		req.setTitle("title length exceeds 20");

		final String body = objectMapper.writeValueAsString(req);

		// when
		ResultActions resultActions = mockMvc.perform(
			post("/api/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// then
		resultActions.andExpect(status().is4xxClientError())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException))
			.andDo(print());

	}
}
