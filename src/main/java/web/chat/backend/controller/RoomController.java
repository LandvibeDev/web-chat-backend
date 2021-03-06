package web.chat.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import web.chat.backend.controller.request.MessageRequest;
import web.chat.backend.controller.request.RoomRequest;
import web.chat.backend.controller.response.MessageResponse;
import web.chat.backend.controller.response.MessagesResponse;
import web.chat.backend.controller.response.RoomResponse;
import web.chat.backend.controller.response.RoomsResponse;
import web.chat.backend.controller.response.collector.MessageCollector;
import web.chat.backend.entity.Message;
import web.chat.backend.entity.Room;
import web.chat.backend.service.MessageService;
import web.chat.backend.service.RoomService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	private final RoomService roomService;
	private final MessageService messageService;

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public RoomsResponse getRooms() {
		List<Room> rooms = roomService.getRooms();
		List<RoomResponse> roomResponseList = rooms.stream()
			.map(RoomResponse::create)
			.collect(Collectors.toList());
		RoomsResponse roomsResponse = new RoomsResponse();
		roomsResponse.setRooms(roomResponseList);
		return roomsResponse;
	}

	@GetMapping(value = "/{id}/messages")
	@ResponseStatus(HttpStatus.OK)
	public MessagesResponse getMessages(@PathVariable Long id) {
		List<Message> messages = messageService.getMessagesBy(id);
		return messages.stream().collect(new MessageCollector());
	}

	@PostMapping("/{roomId}/messages")
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponse createMessage(@PathVariable Long roomId, @RequestBody @Valid MessageRequest messageRequest) {
		Message message = new Message();
		message.setContents(messageRequest.getContents());

		Room room = roomService.getOrThrow(roomId);

		Message savedMessage = messageService.createMessage(room, message);

		return new MessageResponse(savedMessage);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public RoomResponse createRoom(@RequestBody @Valid RoomRequest roomRequest) {
		Room room = new Room();
		room.setTitle((roomRequest.getTitle()));
		Room savedRoom = roomService.createRoom(room);
		RoomResponse roomResponse = new RoomResponse();
		roomResponse.setId(savedRoom.getId());
		roomResponse.setTitle(savedRoom.getTitle());
		return roomResponse;
	}

}
