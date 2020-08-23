package web.chat.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.chat.backend.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
