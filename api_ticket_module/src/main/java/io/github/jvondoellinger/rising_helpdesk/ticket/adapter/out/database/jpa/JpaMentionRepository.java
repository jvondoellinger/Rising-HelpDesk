package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.MentionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaMentionRepository extends JpaRepository<MentionDbEntity, UUID> {
}
