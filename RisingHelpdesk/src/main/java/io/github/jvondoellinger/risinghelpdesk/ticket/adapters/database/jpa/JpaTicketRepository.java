package io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.jpa;

import io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.entities.TicketDbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaTicketRepository extends JpaRepository<TicketDbEntity, UUID> {
	Optional<TicketDbEntity> findByNumber(String protocolNumber);
	Page<TicketDbEntity> findByOpenedBy(UUID openedBy, Pageable pageable);
}
