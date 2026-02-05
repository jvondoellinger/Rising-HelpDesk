package com.github.jvondoellinger.agp_protocol.profile_module.domain;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;

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
