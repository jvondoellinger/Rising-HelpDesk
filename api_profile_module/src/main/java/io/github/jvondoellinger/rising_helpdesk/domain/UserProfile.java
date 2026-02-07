package io.github.jvondoellinger.rising_helpdesk.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.time.LocalDateTime;

public class UserProfile {
	private final UserProfileId userId;
	private final AccessProfileId accessProfile;

	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public UserProfile(UserProfileId userId, AccessProfileId accessProfile, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.userId = userId;
		this.accessProfile = accessProfile;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserProfile(AccessProfileId accessProfile) {
		this.userId = new UserProfileId();
		this.accessProfile = accessProfile;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}

	public UserProfileId getUserId() {
		return userId;
	}

	public AccessProfileId getAccessProfile() {
		return accessProfile;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
