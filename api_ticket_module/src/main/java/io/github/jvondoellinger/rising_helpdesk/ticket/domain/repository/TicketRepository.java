package io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends CrudsRepository<Ticket, UUID> {
	Optional<Ticket> findByNumber(TicketNumber number);
	Pagination<Ticket> findByAuthor(String tenantId, PaginationFilter filter);
}
