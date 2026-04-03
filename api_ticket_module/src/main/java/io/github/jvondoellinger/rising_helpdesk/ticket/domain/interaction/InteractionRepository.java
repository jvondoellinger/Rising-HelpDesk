package io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;

import java.util.UUID;

public interface InteractionRepository extends CrudRepository<Interaction, UUID> {
}
