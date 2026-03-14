package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.TicketCommandMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.TicketResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateTicketCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByPaginationQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final TicketCommandMapper commandMapper;
    private final TicketResponseMapper responseMapper;

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int limit) {
        var query = new FindTicketByPaginationQuery(page, limit);

        return queryBus
                .send(query)
                .fold(onSuccess -> {
                            var responsePagination = responseMapper.from(onSuccess.value());
                            return ResponseEntity.ok(responsePagination);
                        },
                        onFailure -> ResponseEntity.badRequest().body(onFailure.error().getMessage()));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateTicketCommand requestDTO) {
        return null;
    }

    @PostMapping("/delegate")
    public ResponseEntity<?> delegateToQueue(@RequestBody CreateTicketCommand requestDTO) {
        return null;
    }

    @DeleteMapping("/mention")
    public ResponseEntity<?> addMention(@RequestBody CreateTicketCommand requestDTO) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> removeMention(@RequestBody CreateTicketCommand requestDTO) {
        return null;
    }
}
