package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.change_queue_area;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Queue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChangeQueueAreaService implements ChangeQueueAreaHandler {
    private final QueueRepository repository;
    private final AuthenticatedUser authenticatedUser;

    @Override
    public ResultB<Void> handle(ChangeQueueArea cmd) {
        return ResultB.create()
                .flatMap(aVOid -> {
                    var optional = repository.findById(cmd.id());

                    if (optional.isEmpty())
                        return ResultB.error(new DomainError("NO_QUEUE_FOUND_WITH_THIS_ID", "No queue found with this ID."));

                    var queue = optional.get();
                    var area = cmd.area();
                    if (queue.getArea().equals(area))
                        return ResultB.error(new DomainError("THE_QUEUE_ALREADY_HAS_THIS_SUBAREA", "The queue already has this subarea."));

                    var updated = new Queue(
                            queue.getId(),
                            area,
                            queue.getSubarea(),
                            queue.getCreatedBy(),
                            queue.getUpdatedAt(),
                            LocalDateTime.now(),
                            authenticatedUser.getCurrentUserId()
                    );

                    repository.save(updated);
                    return ResultB.create();
                });
    }

    @Override
    public Class<ChangeQueueArea> getCommandType() {
        return ChangeQueueArea.class;
    }
}
