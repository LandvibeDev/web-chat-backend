package web.chat.backend.controller.response;

import lombok.Getter;
import lombok.Setter;
import web.chat.backend.entity.Room;

@Getter
@Setter
public class RoomResponse {
	private Long id;
	private String title;

	public static RoomResponse create(Room room) {
		RoomResponse roomResponse = new RoomResponse();
		roomResponse.setId(room.getId());
		roomResponse.setTitle(room.getTitle());
		return roomResponse;
	}
}
