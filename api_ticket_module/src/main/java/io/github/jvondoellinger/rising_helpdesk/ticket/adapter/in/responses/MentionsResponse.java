package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses;

import java.util.List;
import java.util.UUID;

public record MentionsResponse(List<UUID> userIds) {
}
