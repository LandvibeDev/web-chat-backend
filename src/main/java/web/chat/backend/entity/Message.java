package web.chat.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contents", nullable = false, columnDefinition = "TEXT")
	private String contents;

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "message_type", nullable = false)
	private MessageType messageType = MessageType.TEXT;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}
