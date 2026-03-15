package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.ChangeQueueAreaCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChangeQueueAreaCommandService implements ChangeQueueAreaCommandHandler {
    private final QueueRepository repository;

    @Override
    public Result<Void> handle(ChangeQueueAreaCommand cmd) {
        var persisted = repository.queryById(QueueId.of(cmd.id().toString()));

        if (persisted == null) {
            return new Result.Failure<>(new KernelException("No queue found with this ID."));
        }

        var area = cmd.area();

        if (persisted.getArea().equals(area)) {
            return new Result.Failure<>(new KernelException("The queue already has this area."));
        }

        var updated = new Queue(
                persisted.getId(),
                area,
                persisted.getSubarea(),
                persisted.getCreatedBy(),
                persisted.getUpdatedAt(),
                LocalDateTime.now(),
                UserProfileId.of(cmd.updatedBy().toString())
        );

        repository.save(updated);

        return new Result.Success<>(null);
    }

    @Override
    public Class<ChangeQueueAreaCommand> getCommandType() {
        return ChangeQueueAreaCommand.class;
    }
}
