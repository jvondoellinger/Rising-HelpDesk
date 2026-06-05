package io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.CrudRepository;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.PaginationFilter;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.Ticket;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.valueObjects.TicketNumber;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
	Optional<Ticket> findByNumber(TicketNumber number);
	Pagination<Ticket> findByAuthorId(UUID tenantId, PaginationFilter filter);
}
