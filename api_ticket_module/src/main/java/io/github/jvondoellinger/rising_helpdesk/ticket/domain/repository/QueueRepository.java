package io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;

import java.util.Optional;
import java.util.UUID;

public interface QueueRepository extends CrudsRepository<Queue, UUID> {
    boolean existsByArea(String area);
    Optional<Queue> findByArea(String area);
    Optional<Queue> findBySubarea(String area);
    Pagination<Queue> findByAuthor(UUID authorId, PaginationFilter filter);
}
