package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.out.entities;

import io.github.jvondoellinger.rising_helpdesk.kernel.anotationTest.FixAfter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_user_profile")
@Getter
@Setter
@FixAfter
public class UserProfileDbEntity {
	@Id
	public UUID userId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "accessprofile_id", nullable = false)
	public AccessProfileDbEntity accessProfile;

	@CreationTimestamp
	public LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	public LocalDateTime updatedAt;

	@PersistenceCreator
	public UserProfileDbEntity(UUID userId, AccessProfileDbEntity accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserProfileDbEntity() {
	}
}
