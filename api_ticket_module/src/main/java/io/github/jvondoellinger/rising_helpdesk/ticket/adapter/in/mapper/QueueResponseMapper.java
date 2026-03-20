package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.QueueResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import org.springframework.stereotype.Service;

@Service
public final class QueueResponseMapper {

    public QueueResponse from(QueueDetails details) {
        return new QueueResponse(
                details.domainId().toString(),
                details.area(),
                details.subarea(),
                details.createdAt(),
                details.createdBy().toString(),
                details.updatedAt(),
                details.lastUpdatedBy().toString()
        );
    }

    public Pagination<QueueResponse> from(Pagination<QueueDetails> details) {
        var items = details.items();
        var responses = items.stream().map(this::from).toList();

        return Pagination.of(responses, details.page(), details.totalPages());
    }
}
