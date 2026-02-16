package io.github.jvondoellinger.rising_helpdesk.profile.infrastructure;

import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.converter.AccessProfileIdFieldConverter;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.UserProfile;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_user_profile")
@Getter
@Setter
@FixAfter
public class UserProfileDbEntity {
	@Id
	public String userId;

	@Convert(converter = AccessProfileIdFieldConverter.class)
	public AccessProfileId accessProfile;

	@CreationTimestamp
	public LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	public LocalDateTime updatedAt;

	public UserProfileDbEntity(UserProfile user) {
		this.userId = user.getUserId().toString();
		this.accessProfile = user.getAccessProfile();
		this.createdAt = user.getCreatedAt();
		this.updatedAt = user.getUpdatedAt();
	}

	@PersistenceCreator
	public UserProfileDbEntity(String userId, AccessProfileId accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	protected UserProfileDbEntity() {
	}

	public static UserProfileDbEntity foreignKey(String id) {
		var userProfileDbEntity = new UserProfileDbEntity();
		userProfileDbEntity.setUserId(id);
		return userProfileDbEntity;
	}
}
