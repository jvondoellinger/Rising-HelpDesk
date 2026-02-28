package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.TicketDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTicketRepository extends JpaRepository<TicketDbEntity, String> {
	Optional<TicketDbEntity> findByNumber(String protocolNumber);
}
