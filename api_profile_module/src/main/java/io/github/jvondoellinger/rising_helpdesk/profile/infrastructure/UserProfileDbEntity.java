package io.github.jvondoellinger.rising_helpdesk.profile.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
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
@Table(name = "tb_user_profile")
@Getter
@Setter
@FixAfter
public class UserProfileDbEntity {
	@Id
	public UUID userId;

	public UUID accessProfile;

	@CreationTimestamp
	public LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	public LocalDateTime updatedAt;

	public UserProfileDbEntity(UserProfile user) {
		this.userId = user.getUserId();
		this.accessProfile = user.getAccessProfile();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
	}

	@PersistenceCreator
	public UserProfileDbEntity(UUID userId, UUID accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserProfileDbEntity() {
	}
}
