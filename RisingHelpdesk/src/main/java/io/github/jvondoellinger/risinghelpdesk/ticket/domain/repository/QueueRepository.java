package io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.CrudRepository;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.PaginationFilter;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Queue;

import java.util.Optional;
import java.util.UUID;

public interface QueueRepository extends CrudRepository<Queue, UUID> {
    boolean existsByArea(String area);
    Optional<Queue> findByArea(String area);
    Optional<Queue> findBySubarea(String area);
    Pagination<Queue> findByAuthor(UUID authorId, PaginationFilter filter);
}
