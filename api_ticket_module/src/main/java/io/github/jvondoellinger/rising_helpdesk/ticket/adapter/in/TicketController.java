package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.TicketResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.AddTicketMentionRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.CreateTicketRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.DelegateTicketRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.RemoveTicketMentionRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddTicketMentionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.DelegateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.RemoveTicketMentionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByPaginationQuery;

import static io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.HandleCommandFailure.handleFailure;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final CommandBus commandBus;
	private final QueryBus queryBus;
	private final TicketResponseMapper responseMapper;

	@GetMapping
	public ResponseEntity<String> get(
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "100") int limit
	) {
		var result = queryBus.send(new FindTicketByPaginationQuery(
			   page,
			   limit)
		);

		return handleFailure(result);
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody CreateTicketRequest request) {
		var result = commandBus.send(new CreateTicketCommand(
			   request.title(),
			   request.queueId(),
			   request.deadline())
		);

		return handleFailure(result);
	}

	@PostMapping("/delegate")
	public ResponseEntity<String> delegateToQueue(@RequestBody DelegateTicketRequest request) {
		var result = commandBus.send(new DelegateTicketCommand(
			   request.ticketId(),
			   request.queueId()
		));

		return handleFailure(result);
	}

	@PostMapping("/mention")
	public ResponseEntity<String> addMention(@RequestBody AddTicketMentionRequest request) {
		var result = commandBus.send(new AddTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));

		return handleFailure(result);
	}

	@DeleteMapping("/mention")
	public ResponseEntity<String> removeMention(@RequestBody RemoveTicketMentionRequest request) {
		var result = commandBus.send(new RemoveTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));

		return handleFailure(result);
	}
}
