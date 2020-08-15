package web.chat.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.chat.backend.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findAllByRoomId(Long roomId);

	boolean existsByRoomId(Long roomId);
}
