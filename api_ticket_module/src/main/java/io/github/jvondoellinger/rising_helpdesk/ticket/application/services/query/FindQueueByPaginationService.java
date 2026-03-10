package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByPaginationQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class FindQueueByPaginationService implements FindQueueByPaginationQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public Result<Pagination<QueueDetails>> handle(FindQueueByPaginationQuery query) {
        var size = query.size();
        var page = query.page();

        // Paralelizando as chamadas, tornando a requisição mais rápida
        var callQueue = CompletableFuture.supplyAsync(() -> repository.query(QueryFilter.of(size, page)));
        var callTotal = CompletableFuture.supplyAsync(repository::total);
        CompletableFuture.allOf(callQueue, callTotal);

        List<Queue> queues = null;
        Long total = null;

        try {
            queues = callQueue.get();
            total = callTotal.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (queues == null || total == null) {
            return new Result.Failure<>(new KernelException("An error occurred while searching the page."));
        }

        var details = mapper.details(queues);
        var pagination = new Pagination<>(details, page, size, total);

        return new Result.Success<>(pagination);
    }

    @Override
    public Class<FindQueueByPaginationQuery> getQueryType() {
        return FindQueueByPaginationQuery.class;
    }
}
