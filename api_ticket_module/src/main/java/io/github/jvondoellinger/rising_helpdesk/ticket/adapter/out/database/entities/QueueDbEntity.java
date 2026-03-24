package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_queue")
@Getter
@Setter
@FixAfter
public class QueueDbEntity{
	@Id
	private UUID id;

	private String area;
	private String subarea;

	@Column(name = "author")
	private UUID author;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime updatedAt;

	@Column(name = "last_updated_by")
	private UUID lastUpdatedBy;

	public QueueDbEntity() {}
	public QueueDbEntity(Queue queue) {
		this.id = queue.getId();
		this.area = queue.getArea();
		this.subarea = queue.getSubarea();
		this.author = queue.getCreatedBy();

		this.createdAt = LocalDateTime.now();
		this.updatedAt = queue.getUpdatedAt();
		this.lastUpdatedBy = queue.getLastUpdatedBy() == null ?
			   null : queue.getLastUpdatedBy();
	}

	@PersistenceCreator
	public QueueDbEntity(UUID id, String area, String subarea, UUID author, LocalDateTime createdAt, LocalDateTime updatedAt, UUID lastUpdatedBy) {
		this.id = id;
		this.area = area;
		this.subarea = subarea;
		this.author = author;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
