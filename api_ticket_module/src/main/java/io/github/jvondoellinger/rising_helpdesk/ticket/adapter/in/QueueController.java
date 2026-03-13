package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.QueueCommandMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.DeleteQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class QueueController {
    private final QueueCommandMapper mapper;
    private final QueryBus queryBus;
    private final CommandBus commandBus;

    @PostMapping
    public ResponseEntity<?> createQueue(@RequestBody CreateQueueRequest request) {
        var cmd = mapper.from(request);

        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }
    @DeleteMapping
    public ResponseEntity<?> deleteQueue(@RequestBody DeleteQueueRequest request) {
        var cmd = mapper.from(request);
        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }
    @PatchMapping("/area")
    public ResponseEntity<?> changeQueueArea(@RequestBody ChangeAreaRequest request) {
        var cmd = mapper.from(request);
        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }
    @PatchMapping("/subarea")
    public ResponseEntity<?> changeQueueSubarea(@RequestBody ChangeSubareaRequest request) {
        var cmd = mapper.from(request);
        return commandBus
                .send(cmd)
                .fold(
                        success -> ResponseEntity.accepted().build(),
                        failure -> ResponseEntity.badRequest().body(failure.error().getMessage())
                );
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return queryBus
                .send(new FindQueueByPaginationQuery(offset,limit))
                .fold(
                success -> ResponseEntity.ok(success.value()),
                faillure -> ResponseEntity.badRequest().body(faillure.error().getMessage())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return queryBus
                .send(new FindQueueByIdQuery(QueueId.of(id.toString())))
                .fold(
                        success -> ResponseEntity.ok(success.value()),
                        faillure -> ResponseEntity.badRequest().body(faillure.error().getMessage())
                );
    }
}
