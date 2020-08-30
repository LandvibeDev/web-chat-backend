package web.chat.backend.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import web.chat.backend.controller.request.RoomRequest;

/**
 * Created by koseungbin on 2020-08-15
 */

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerIntegrationTest {
	final ObjectMapper objectMapper;
	final RoomController roomController;
	final MockMvc mockMvc;

	@Test
	@Sql("/test-sql/messages.sql")
	@DisplayName("ID가 1인 채팅방의 메시지 리스트가 조회되야만 한다")
	void shouldGetMessageList_inRoomIdWith1() throws Exception {
		mockMvc.perform(get("/api/rooms/{id}/messages", 1))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*", not(empty())))
			.andExpect(jsonPath("$.*", hasSize(1)))
			.andExpect(jsonPath("$.messages[0].id", is(1)))
			.andExpect(jsonPath("$.messages[0].contents", is("foo")))
			.andExpect(jsonPath("$.messages[0].messageType", is("TEXT")));
	}

	@Test
	@DisplayName("채팅방에 메시지가 하나도 없는 경우 빈 메시지 리스트를 반환해야만 한다")
	void shouldRespond400StatusCode_ifNotFoundRoom() throws Exception {
		mockMvc.perform(get("/api/rooms/{id}/messages", 100))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.messages", empty()));
	}

	@Test
	void getRooms() throws Exception {

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/rooms"));

		// then
		resultActions
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	void createRoom() throws Exception {

		RoomRequest req = new RoomRequest();
		req.setTitle("title");

		final String body = objectMapper.writeValueAsString(req);

		// when
		ResultActions action = mockMvc.perform(
			post("/api/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// then
		action
			.andDo(print())
			.andExpect(status().isCreated());
	}
}
