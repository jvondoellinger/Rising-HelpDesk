package com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.application.queries;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;

import java.time.LocalDateTime;

public record AccessProfileDetails(
	   UserProfileId userId,
	   AccessProfileId accessProfile,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) { }
