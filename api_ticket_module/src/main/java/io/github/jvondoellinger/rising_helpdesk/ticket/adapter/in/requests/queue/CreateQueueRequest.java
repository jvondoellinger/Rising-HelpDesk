package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue;

import java.util.UUID;

public record CreateQueueRequest(String area,
                                 String subarea,
                                 UUID createdBy) {
}
