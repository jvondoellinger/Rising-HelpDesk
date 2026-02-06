package com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.api_ticket.infrastructure.InteractionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInteractionRepository extends JpaRepository<InteractionDbEntity, String> {
}
