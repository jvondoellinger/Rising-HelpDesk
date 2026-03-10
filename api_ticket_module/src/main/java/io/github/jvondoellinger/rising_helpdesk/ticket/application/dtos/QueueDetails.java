package io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;

import java.time.LocalDateTime;

public record QueueDetails(
	   QueueId domainId,
	   String area,
	   String subarea,
	   UserProfileId createdBy,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt,
	   UserProfileId lastUpdatedBy
) {
}
