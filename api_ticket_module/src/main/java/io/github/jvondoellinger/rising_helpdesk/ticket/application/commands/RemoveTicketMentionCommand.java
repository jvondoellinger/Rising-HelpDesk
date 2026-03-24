package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

public record RemoveMentionOnTicketCommand(UUID id, UUID userProfileId) implements Command {

}