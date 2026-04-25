package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.*;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.*;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByPaginationQuery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final CommandBus commandBus;
	private final QueryBus queryBus;

	@GetMapping
	public Result<?> get(
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "100") int limit
	) {
		var result = queryBus.send(new FindTicketByPaginationQuery(
			   page,
			   limit)
		);

		return result;
	}

	@GetMapping("/{id}")
	public Result<?> getById(@PathVariable UUID id) {
		var result = queryBus.send(new FindTicketByIdQuery(
			   id
		));

		return result;
	}

	@PostMapping
	public Result<?> create(@RequestBody CreateTicketRequest request) {
		var result = commandBus.send(new CreateTicketCommand(
			   request.title(),
			   request.queueId(),
			   request.deadline())
		);

		return result;
	}

	@PostMapping("/delegate")
	public Result<?> delegateToQueue(@RequestBody DelegateTicketRequest request) {
		var result = commandBus.send(new DelegateTicketCommand(
			   request.ticketId(),
			   request.queueId()
		));

		return result;
	}

	@PostMapping("/mention")
	public Result<?> addMention(@RequestBody AddTicketMentionRequest request) {
		var result = commandBus.send(new AddTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));

		return result;
	}

	@DeleteMapping("/mention")
	public Result<?> removeMention(@RequestBody RemoveTicketMentionRequest request) {
		var result = commandBus.send(new RemoveTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));
		return result;
	}


	@PostMapping("/interact")
	public Result<?> interact(@RequestBody AddInteractionRequest request) {
		var result = commandBus.send(new AddInteractionCommand(
			   request.interaction(),
			   request.ticketId()
		));
		return result;
	}

	@PatchMapping("/status/{id}")
	public Result<?> closeTicket(@PathVariable UUID id) {
		var result = commandBus.send(new CloseTicketCommand(
			   id
		));
		return result;
	}
}
