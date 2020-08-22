package web.chat.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.chat.backend.controller.response.RoomResponse;
import web.chat.backend.entity.Room;
import web.chat.backend.repository.RoomRepository;

@RequiredArgsConstructor
@Service
public class RoomService {

	private final RoomRepository roomRepository;

	public RoomResponse createRoom(Room room) {
		Room room1 = new Room();
		room1 = roomRepository.save(room);
		RoomResponse roomResponse = new RoomResponse();
		roomResponse.setId(room1.getId());
		roomResponse.setTitle(room1.getTitle());
		return roomResponse;
	}

	public List<Room> getRooms() {
		return roomRepository.findAll();
	}
}
