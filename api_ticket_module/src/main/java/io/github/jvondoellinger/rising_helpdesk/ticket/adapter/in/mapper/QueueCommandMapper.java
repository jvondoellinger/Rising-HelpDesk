package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.DeleteQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.DeleteQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import org.springframework.stereotype.Service;

@Service
public class QueueCommandMapper {
    public CreateQueueCommand from(CreateQueueRequest request) {
        return new CreateQueueCommand(request.area(), request.subarea(), UserProfileId.of(request.createdBy().toString()));
    }

    public DeleteQueueCommand from(DeleteQueueRequest request) {
        return new DeleteQueueCommand(QueueId.of(request.id().toString()), UserProfileId.of(request.userProfileId().toString()));
    }

    public ChangeQueueAreaCommand from(ChangeAreaRequest request) {
        return new ChangeQueueAreaCommand(request.id(), request.area(), request.tenantId());
    }

    public ChangeQueueSubareaCommand from(ChangeSubareaRequest request) {
        return new ChangeQueueSubareaCommand(request.id(), request.area(), request.tenantId());
    }
}
