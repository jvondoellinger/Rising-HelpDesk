package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;

import java.util.UUID;

public record CreateQueueRequest(String area,
                                 String subarea,
                                 UUID createdBy) {
}
