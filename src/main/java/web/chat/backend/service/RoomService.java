package web.chat.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import web.chat.backend.entity.Room;
import web.chat.backend.repository.RoomRepository;

@RequiredArgsConstructor
@Service
public class RoomService {

	private final RoomRepository roomRepository;

	public List<Room> getRooms(){
		return roomRepository.findAll();
	}
}
