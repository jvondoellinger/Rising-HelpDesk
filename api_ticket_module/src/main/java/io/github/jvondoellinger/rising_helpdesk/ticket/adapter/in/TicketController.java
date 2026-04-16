package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.TicketResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.*;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.*;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByPaginationQuery;

import static io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.HandleCommandFailure.handleFailure;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final CommandBus commandBus;
	private final QueryBus queryBus;
	private final TicketResponseMapper responseMapper;

	@GetMapping
	public ResponseEntity<?> get(	
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "100") int limit
	) {
		var result = queryBus.send(new FindTicketByPaginationQuery(
			   page,
			   limit)
		);

		return handleFailure(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable UUID id) {
		var result = queryBus.send(new FindTicketByIdQuery(
			   id
		));

		return handleFailure(result);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateTicketRequest request) {
		var result = commandBus.send(new CreateTicketCommand(
			   request.title(),
			   request.queueId(),
			   request.deadline())
		);

		return handleFailure(result);
	}

	@PostMapping("/delegate")
	public ResponseEntity<?> delegateToQueue(@RequestBody DelegateTicketRequest request) {
		var result = commandBus.send(new DelegateTicketCommand(
			   request.ticketId(),
			   request.queueId()
		));

		return handleFailure(result);
	}

	@PostMapping("/mention")
	public ResponseEntity<?> addMention(@RequestBody AddTicketMentionRequest request) {
		var result = commandBus.send(new AddTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));

		return handleFailure(result);
	}

	@DeleteMapping("/mention")
	public ResponseEntity<?> removeMention(@RequestBody RemoveTicketMentionRequest request) {
		var result = commandBus.send(new RemoveTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));

		return handleFailure(result);
	}


	@PostMapping("/interact")
	public ResponseEntity<?> interact(@RequestBody AddInteractionRequest request) {
		var result = commandBus.send(new AddInteractionCommand(
			   request.interaction(),
			   request.ticketId()
		));

		return handleFailure(result);
	}

	@PatchMapping("/status/{id}")
	public ResponseEntity<?> closeTicket(@PathVariable UUID id) {
		var result = commandBus.send(new CloseTicketCommand(
			   id
		));
		return handleFailure(result);
	}
}
