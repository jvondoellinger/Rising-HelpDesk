package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.QueueCommandMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.QueueResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.DeleteQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class QueueController {
    private final QueueCommandMapper commandMapper;
    private final QueueResponseMapper responseMapper;
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    @PostMapping
    public ResponseEntity<?> createQueue(@RequestBody CreateQueueRequest request) {
        var cmd = commandMapper.from(request);

        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }
    @DeleteMapping
    public ResponseEntity<?> deleteQueue(@RequestBody DeleteQueueRequest request) {
        var cmd = commandMapper.from(request);
        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }
    @PatchMapping("/area")
    public ResponseEntity<?> changeQueueArea(@RequestBody ChangeAreaRequest request) {
        var cmd = commandMapper.from(request);
        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }
    @PatchMapping("/subarea")
    public ResponseEntity<?> changeQueueSubarea(@RequestBody ChangeSubareaRequest request) {
        var cmd = commandMapper.from(request);
        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        return queryBus
                .send(new FindQueueByPaginationQuery(page,limit))
                .fold(
                success -> {
                    var responsePagination = responseMapper.from(success.value());
                    return ResponseEntity.ok(responsePagination);
                },
                faillure -> ResponseEntity.badRequest().body(faillure.error().getMessage())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return queryBus
                .send(new FindQueueByIdQuery(id))
                .fold(
                        success -> {
                            var response = responseMapper.from(success.value());
                            return ResponseEntity.ok(response);
                        },
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }
}
