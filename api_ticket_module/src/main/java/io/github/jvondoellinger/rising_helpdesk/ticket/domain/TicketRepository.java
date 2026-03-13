package io.github.jvondoellinger.rising_helpdesk.ticket.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends CrudsRepository<Ticket, TicketId> {
	Optional<Ticket> findByNumber(TicketNumber number);
	Pagination<Ticket> findByAuthor(String tenantId, QueryFilter filter);
}
