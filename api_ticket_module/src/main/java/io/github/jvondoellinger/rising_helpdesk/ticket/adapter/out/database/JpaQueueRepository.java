package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.QueueDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaQueueRepository extends JpaRepository<QueueDbEntity, String> {
    boolean existsByArea(String area);
}
