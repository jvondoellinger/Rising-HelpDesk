package io.github.jvondoellinger.rising_helpdesk.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.adapter.out.database.converter.UserProfileIdFieldConverter;
import io.github.jvondoellinger.rising_helpdesk.domain.Queue;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
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

	@Column(name = "created_by")
	@Convert(converter = UserProfileIdFieldConverter.class)
	private UserProfileId createdBy;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime updatedAt;

	@Column(name = "last_updated_by")
	@Convert(converter = UserProfileIdFieldConverter.class)
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
