package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.response;

import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;

import java.time.LocalDateTime;

public record AccessProfileResponse(String accessProfile,
							 LocalDateTime createdAt,
							 LocalDateTime updatedAt) {
	public AccessProfileResponse(AccessProfileDetails details) {
		this.accessProfile = details.accessProfileId().toString();
		this.createdAt = details.createdAt();
		this.updatedAt = details.updatedAt();
	}
}
