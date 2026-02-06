package com.github.jvondoellinger.agp_protocol.api_ticket.application.queries;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;

import java.time.LocalDateTime;

public record QueueDetails(
	   DomainId domainId,
	   String area,
	   String subarea,
	   UserProfileId createdBy,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt,
	   UserProfileId lastUpdatedBy
) {
}
