package web.chat.backend.controller.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomsResponse {
	List<RoomResponse> rooms;
}
