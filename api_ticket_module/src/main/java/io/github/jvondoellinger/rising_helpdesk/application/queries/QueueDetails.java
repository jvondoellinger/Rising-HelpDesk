package io.github.jvondoellinger.rising_helpdesk.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

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
