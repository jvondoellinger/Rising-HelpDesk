package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.Command;

import java.util.UUID;

public record ChangeQueueSubareaCommand(UUID id, String subarea) implements Command {
}
