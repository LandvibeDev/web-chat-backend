package web.chat.backend.controller.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by koseungbin on 2020-08-30
 */

@Getter
@RequiredArgsConstructor
public class MessagesResponse {
	final private List<MessageResponse> messages;
}
