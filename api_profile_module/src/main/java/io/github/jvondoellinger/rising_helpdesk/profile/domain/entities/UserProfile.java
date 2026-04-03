package io.github.jvondoellinger.rising_helpdesk.profile.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserProfile {
	private final UUID userId;
	private final UUID accessProfile;

	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public UserProfile(UUID userId, UUID accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserProfile(UUID userId, UUID accessProfile) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}

	// !Getters
	public UUID getUserId() {
		return userId;
	}
	public UUID getAccessProfile() {
		return accessProfile;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
