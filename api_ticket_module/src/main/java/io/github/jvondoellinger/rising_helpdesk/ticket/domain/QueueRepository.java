package io.github.jvondoellinger.rising_helpdesk.ticket.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.infrastructure.CrudsRepository;

public interface QueueRepository extends CrudsRepository<Queue, QueueId> {
    boolean existsByArea(String area);
}
