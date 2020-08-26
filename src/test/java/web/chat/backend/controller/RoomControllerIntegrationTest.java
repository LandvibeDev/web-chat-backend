package web.chat.backend.controller;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import web.chat.backend.exception.NotFoundException;

/**
 * Created by koseungbin on 2020-08-15
 */

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@SpringBootTest
class RoomControllerIntegrationTest {
	MockMvc mockMvc;
	final ObjectMapper objectMapper;

	final RoomController roomController;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(roomController)
			.setControllerAdvice(new ExceptionController())
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	@Test
	@Sql("/test-sql/messages.sql")
	@DisplayName("ID가 1인 채팅방의 메시지 리스트가 조회되야만 한다")
	void shouldGetMessageList_inRoomIdWith1() throws Exception {
		mockMvc.perform(get("/api/rooms/{id}/messages", 1))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*", not(empty())))
			.andExpect(jsonPath("$.*", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].contents", is("foo")))
			.andExpect(jsonPath("$[0].messageType", is("TEXT")))
			.andDo(print());
	}

	@Test
	@DisplayName("존재하지 않은 채팅방을 요청한 경우에는 400 응답코드를 반환해야만 한다")
	void shouldRespond400StatusCode_ifNotFoundRoom() throws Exception {
		mockMvc.perform(get("/api/rooms/{id}/messages", 100))
			.andExpect(status().isBadRequest())
			.andExpect(result -> assertThat(result.getResolvedException() instanceof NotFoundException).isTrue())
			.andExpect(result -> assertEquals(Objects.requireNonNull(result.getResolvedException()).getMessage(),
				"100 does not exist."))
			.andDo(print());
	}

	@Test
	void getRooms() throws Exception {

		// when

		// then
	}

	@Test
	void createRoom() throws Exception {

		// when

		// then
	}
}
