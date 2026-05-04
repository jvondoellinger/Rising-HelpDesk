package io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.infrastructure.CrudRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
	Optional<Ticket> findByNumber(TicketNumber number);
	Pagination<Ticket> findByAuthorId(UUID tenantId, PaginationFilter filter);
}
