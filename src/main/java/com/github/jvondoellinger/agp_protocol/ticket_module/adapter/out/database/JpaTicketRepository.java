package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.TicketDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTicketRepository extends JpaRepository<TicketDbEntity, String> {
}
