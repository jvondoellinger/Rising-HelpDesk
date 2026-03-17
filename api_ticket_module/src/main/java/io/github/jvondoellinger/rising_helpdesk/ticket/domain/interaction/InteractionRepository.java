package io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

import java.util.UUID;

public interface InteractionRepository extends CrudsRepository<Interaction, UUID> {
}
