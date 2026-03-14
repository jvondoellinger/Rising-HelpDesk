package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa;

import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.TicketDbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTicketRepository extends JpaRepository<TicketDbEntity, String> {
	Optional<TicketDbEntity> findByNumber(String protocolNumber);
	Page<TicketDbEntity> findByOpenedById(String openedById, Pageable pageable);
}
