package io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.CrudRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Interaction;

import java.util.UUID;

public interface InteractionRepository extends CrudRepository<Interaction, UUID> {
}
