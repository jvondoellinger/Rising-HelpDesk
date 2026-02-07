package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.domain.mention.Mentions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.time.LocalDateTime;

public record CreateTicketCommand(
	   String title,
	   QueueId queueId,
	   Mentions mentions,
	   LocalDateTime deadline,
	   UserProfileId openedBy
) {
}
