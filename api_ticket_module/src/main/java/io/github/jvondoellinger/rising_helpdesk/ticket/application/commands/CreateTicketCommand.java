package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.mention.Mentions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

import java.time.LocalDateTime;

public record CreateTicketCommand(
	   String title,
	   QueueId queueId,
	   Mentions mentions,
	   LocalDateTime deadline,
	   UserProfileId openedBy
) implements Command {
}
