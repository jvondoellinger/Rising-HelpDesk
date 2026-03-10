package io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.Queue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueMapper {
    public QueueDetails details(Queue queue) {
        return new QueueDetails(
                queue.getId(),
                queue.getArea(),
                queue.getSubarea(),
                queue.getCreatedBy(),
                queue.getCreatedAt(),
                queue.getUpdatedAt(),
                queue.getLastUpdatedBy()
        );
    }

    public List<QueueDetails> details(List<Queue> queues) {
        return queues.stream().map(this::details).toList();
    }
}
