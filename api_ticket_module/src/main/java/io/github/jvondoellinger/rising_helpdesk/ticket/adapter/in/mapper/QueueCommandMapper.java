package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.DeleteQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.DeleteQueueCommand;
import org.springframework.stereotype.Service;

@Service
public class QueueCommandMapper {
    public CreateQueueCommand from(CreateQueueRequest request) {
        return new CreateQueueCommand(request.area(), request.subarea(), request.createdBy());
    }

    public DeleteQueueCommand from(DeleteQueueRequest request) {
        return new DeleteQueueCommand(request.id(), request.userProfileId());
    }

    public ChangeQueueAreaCommand from(ChangeAreaRequest request) {
        return new ChangeQueueAreaCommand(request.id(), request.area(), request.tenantId());
    }

    public ChangeQueueSubareaCommand from(ChangeSubareaRequest request) {
        return new ChangeQueueSubareaCommand(request.id(), request.area(), request.tenantId());
    }
}
