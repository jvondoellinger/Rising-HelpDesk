package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa;

import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.QueueDbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaQueueRepository extends JpaRepository<QueueDbEntity, UUID> {
    boolean existsByArea(String area);
    Optional<QueueDbEntity> findByArea(String area);
    Optional<QueueDbEntity> findBySubarea(String subarea);
    Page<QueueDbEntity> findByAuthor(UUID author, Pageable pageable);
}
