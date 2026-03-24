package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.entities.MentionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaMentionRepository extends JpaRepository<MentionDbEntity, UUID> {
}
