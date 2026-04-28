package io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.kernel.infrastructure.CrudRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;

import java.util.UUID;

public interface InteractionRepository extends CrudRepository<Interaction, UUID> {
}
