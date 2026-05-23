package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultA;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.QueueResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.RemoveQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.CommandBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class QueueController {
	private final QueueResponseMapper responseMapper;
	private final QueryBus queryBus;
	private final CommandBus commandBus;

	@PostMapping
	public ResultB<?> createQueue(@RequestBody CreateQueueRequest request) {
		return commandBus.send(new CreateQueueCommand(
			   request.area(),
			   request.subarea()
		));
	}

	@DeleteMapping("/{queueId}")
	public ResultB<?> deleteQueue(@PathVariable UUID queueId) {
		return commandBus.send(new RemoveQueueCommand(
			   queueId
		));
	}

	@PatchMapping("/area")
	public ResultB<?> changeQueueArea(@RequestBody ChangeAreaRequest request) {
		return commandBus.send(new ChangeQueueAreaCommand(
			   request.id(),
			   request.area()
		));

	}

	@PatchMapping("/subarea")
	public ResultB<?> changeQueueSubarea(@RequestBody ChangeSubareaRequest request) {
		return commandBus.send(new ChangeQueueSubareaCommand(
			   request.id(),
			   request.subarea()
		));
	}


	// ! Get routes
	@GetMapping
	public ResultB<?> findByPagination(@RequestParam(value = "page", defaultValue = "0") int page,
								@RequestParam(value = "size", defaultValue = "100") int size) {
		var result = queryBus.send(new FindQueueByPaginationQuery(
			   page,
			   size
		));

		if (result.hasErrors()) {
			return result;
		}

		var response = result
			   .map(responseMapper::from)
			   .getOrDefault(null);

		return ResultB.of(response);
	}

	@GetMapping("/{id}")
	public ResultB<?> getById(@PathVariable UUID id) {
		var result = queryBus.send(new FindQueueByIdQuery(
			   id
		));

		if (result.hasErrors()) {
			return result;
		}

		var response = result
			   .map(responseMapper::from)
			   .getOrDefault(null);

		return ResultB.of(response);
	}
}
