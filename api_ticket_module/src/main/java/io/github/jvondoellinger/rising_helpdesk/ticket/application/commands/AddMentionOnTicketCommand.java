package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketId;

public record AddMentionOnTicketCommand(TicketId id, UserProfileId userProfileId) implements Command {
}
