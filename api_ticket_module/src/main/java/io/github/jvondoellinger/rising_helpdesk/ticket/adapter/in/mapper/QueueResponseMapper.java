package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.PageResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.QueueResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import org.springframework.stereotype.Service;

@Service
public final class QueueResponseMapper {

    public QueueResponse from(QueueDetails details) {
        return new QueueResponse(
                details.id(),
                details.area(),
                details.subarea(),
                details.createdAt(),
                details.createdBy(),
                details.updatedAt(),
                details.lastUpdatedBy()
        );
    }

    public PageResponse<QueueResponse> from(Pagination<QueueDetails> details) {
        var items = details.items();
        var responses = items.stream().map(this::from).toList();

        return new PageResponse<>(responses, details.page(), details.size(), details.totalPages());
    }
}
