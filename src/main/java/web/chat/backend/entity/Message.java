package web.chat.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contents", nullable = false, columnDefinition = "TEXT")
	private String contents;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "message_type", nullable = false)
	private MessageType messageType = MessageType.TEXT;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}
