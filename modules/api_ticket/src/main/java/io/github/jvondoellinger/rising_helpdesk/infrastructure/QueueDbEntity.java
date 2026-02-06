package com.github.jvondoellinger.agp_protocol.api_ticket.infrastructure;

import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.anotationTest.FixAfter;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.Queue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_queue")
@Getter
@Setter
@FixAfter
public class QueueDbEntity{
	@Id
	private String domainId;

	private String area;
	private String subarea;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserProfileId createdBy;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserProfileId lastUpdatedBy;

	protected QueueDbEntity() {}
	public QueueDbEntity(Queue queue) {
		this.domainId = queue.getDomainId().toString();
		this.area = queue.getArea();
		this.subarea = queue.getSubarea();
		this.createdBy = queue.getCreatedBy();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = queue.getUpdatedAt();
		this.lastUpdatedBy = queue.getLastUpdatedBy() == null ?
			   null : queue.getLastUpdatedBy();

	}

	@PersistenceCreator
	public QueueDbEntity(String domainId, String area, String subarea, UserProfileId createdBy, LocalDateTime createdAt, LocalDateTime updatedAt, UserProfileId lastUpdatedBy) {
		this.domainId = domainId;
		this.area = area;
		this.subarea = subarea;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public static QueueDbEntity foreignKey(String id) {
		var queueDbEntity = new QueueDbEntity();
		queueDbEntity.setDomainId(id);
		return queueDbEntity;
	}
}
