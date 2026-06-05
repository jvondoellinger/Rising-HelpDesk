package io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.jpa;

import io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.entities.InteractionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaInteractionRepository extends JpaRepository<InteractionDbEntity, UUID> {
}