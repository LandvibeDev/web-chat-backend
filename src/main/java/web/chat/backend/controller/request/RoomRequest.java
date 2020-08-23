package web.chat.backend.controller.request;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {

	@Size(min = 2, max = 20)
	private String title;

}
