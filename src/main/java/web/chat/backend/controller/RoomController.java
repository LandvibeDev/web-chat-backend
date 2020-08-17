package web.chat.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import web.chat.backend.controller.response.RoomResponse;
import web.chat.backend.controller.response.RoomsResponse;
import web.chat.backend.entity.Room;
import web.chat.backend.service.RoomService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	private final RoomService roomService;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public RoomsResponse getRooms() {
		List<Room> rooms = roomService.getRooms();
		List<RoomResponse> roomResponseList = new ArrayList<>();
		for (int i = 0; i < rooms.size(); i++) {
			RoomResponse roomResponse = new RoomResponse();
			roomResponse.setId(rooms.get(i).getId());
			roomResponse.setTitle((rooms.get(i).getTitle()));
			roomResponseList.add(roomResponse);
		}
		RoomsResponse roomsResponse = new RoomsResponse();
		roomsResponse.setRooms(roomResponseList);
		return roomsResponse;
	}

}
