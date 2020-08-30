package web.chat.backend.controller.request;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequest {

	@Size(min = 1)
	private String contents;
}
