package web.chat.backend.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by koseungbin on 2020-08-15
 */

@SpringBootTest
@ActiveProfiles("test")
public class RoomControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private RoomController roomController;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(roomController)
			.setControllerAdvice(new ExceptionController())
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.build();
	}

	@Test
	@Sql({"/test-sql/messages.sql"})
	void 채팅방_ID가_1인_메시지_목록을_조회한다() throws Exception {
		mockMvc.perform(get("/api/rooms/{id}/messages", 1))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*", not(empty())))
			.andExpect(jsonPath("$.*", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].contents", is("foo")))
			.andExpect(jsonPath("$[0].messageType", is("TEXT")));
	}

	@Test
	@Sql({"/test-sql/messages.sql"})
	void 존재하지_채팅방_요청시_400_응답_코드_반환해야한다() throws Exception {
		mockMvc.perform(get("/api/rooms/{id}/messages", 100))
			.andExpect(status().isBadRequest());
	}
}
