package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.ChangeQueueAreaCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class ChangeQueueAreaCommandService implements ChangeQueueAreaCommandHandler {
    private final QueueRepository repository;
    private final CurrentUserService currentUserService;

    @Override
    public ResultTransformerStep<Void> handle(ChangeQueueAreaCommand cmd) {
        return ResultTransformerStep.create()
                .flatMap(aVOid -> {
                    var optional = repository.findById(cmd.id());

                    if (optional.isEmpty())
                        return Result.error(new DomainError("NO_QUEUE_FOUND_WITH_THIS_ID", "No queue found with this ID."));

                    var queue = optional.get();
                    var area = cmd.area();
                    if (queue.getArea().equals(area))
                        return Result.error(new DomainError("THE_QUEUE_ALREADY_HAS_THIS_SUBAREA", "The queue already has this subarea."));

                    var updated = new Queue(
                            queue.getId(),
                            area,
                            queue.getSubarea(),
                            queue.getCreatedBy(),
                            queue.getUpdatedAt(),
                            LocalDateTime.now(),
                            currentUserService.getUserId()
                    );

                    repository.save(updated);
                    return Result.success();
                });
    }

    @Override
    public Class<ChangeQueueAreaCommand> getCommandType() {
        return ChangeQueueAreaCommand.class;
    }
}
