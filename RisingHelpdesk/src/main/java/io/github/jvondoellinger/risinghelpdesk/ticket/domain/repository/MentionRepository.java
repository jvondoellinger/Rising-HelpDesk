package io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.CrudRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Mention;

import java.util.UUID;

public interface MentionRepository extends CrudRepository<Mention, UUID> {
}
