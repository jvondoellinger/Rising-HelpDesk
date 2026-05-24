package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultA;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.TicketResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.*;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.*;
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
	private final TicketResponseMapper responseMapper;

	@GetMapping
	public ResultB<?> get(
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "100") int limit
	) {
		System.out.println("ROTA GETB");
		var result = queryBus.send(new FindTicketByPaginationQuery(
			   page,
			   limit)
		);
		var response = responseMapper.from(result.getOrDefault(null));
		return ResultB.of(response);
	}

	@GetMapping("/{id}")
	public ResultB<?> getById(@PathVariable UUID id) {
		var result = queryBus.send(new FindTicketByIdQuery(
			   id
		));

		return result;
	}

	@PostMapping
	public ResultB<?> create(@RequestBody CreateTicketRequest request) {
		var result = commandBus.send(new CreateTicketCommand(
			   request.title(),
			   request.queueId(),
			   request.deadline())
		);

		return result;
	}

	@PostMapping("/delegate")
	public ResultB<?> delegateToQueue(@RequestBody DelegateTicketRequest request) {
		var result = commandBus.send(new DelegateTicketCommand(
			   request.ticketId(),
			   request.queueId()
		));

		return result;
	}

	@PostMapping("/mention")
	public ResultB<?> addMention(@RequestBody AddTicketMentionRequest request) {
		var result = commandBus.send(new AddTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));

		return result;
	}

	@DeleteMapping("/mention")
	public ResultB<?> removeMention(@RequestBody RemoveTicketMentionRequest request) {
		var result = commandBus.send(new RemoveTicketMentionCommand(
			   request.ticketId(),
			   request.userId()
		));
		return result;
	}


	@PostMapping("/interact")
	public ResultB<?> interact(@RequestBody AddInteractionRequest request) {
		var result = commandBus.send(new AddInteractionCommand(
			   request.interaction(),
			   request.ticketId()
		));
		return result;
	}

	@PatchMapping("/status/{id}")
	public ResultB<?> closeTicket(@PathVariable UUID id) {
		return commandBus.send(new CloseTicketCommand(
			   id
		));
	}
}
