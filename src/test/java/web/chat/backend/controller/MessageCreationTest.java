package web.chat.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import web.chat.backend.controller.request.MessageRequest;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest
public class MessageCreationTest {

	final MockMvc mockMvc;
	final ObjectMapper objectMapper;

	final RoomController roomController;

	@Test
	@Sql("/test-sql/messages.sql")
	void createMessage() throws Exception {

		// given
		MessageRequest req = new MessageRequest();
		req.setContents("hi hello");

		final String body = objectMapper.writeValueAsString(req);

		// when
		ResultActions action = mockMvc.perform(
			post("/api/rooms/{roomId}/messages", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body));

		// then
		action.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.contents").value(req.getContents()));
	}
}
