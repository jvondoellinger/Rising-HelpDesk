package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper.QueueResponseMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeAreaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.ChangeSubareaRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.queue.CreateQueueRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.ErrorResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueAreaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.RemoveQueueCommand;
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
	public Result<?> createQueue(@RequestBody CreateQueueRequest request) {
		return commandBus.send(new CreateQueueCommand(
			   request.area(),
			   request.subarea()
		));
	}

	@DeleteMapping("/{queueId}")
	public Result<?> deleteQueue(@PathVariable UUID queueId) {
		return commandBus.send(new RemoveQueueCommand(
			   queueId
		));
	}

	@PatchMapping("/area")
	public Result<?> changeQueueArea(@RequestBody ChangeAreaRequest request) {
		return commandBus.send(new ChangeQueueAreaCommand(
			   request.id(),
			   request.area()
		));

	}

	@PatchMapping("/subarea")
	public Result<?> changeQueueSubarea(@RequestBody ChangeSubareaRequest request) {
		return commandBus.send(new ChangeQueueSubareaCommand(
			   request.id(),
			   request.subarea()
		));
	}


	// ! Get routes
	@GetMapping
	public Result<?> findByPagination(@RequestParam(value = "page", defaultValue = "0") int page,
									  @RequestParam(value = "size", defaultValue = "100") int size) {
		var result = queryBus.send(new FindQueueByPaginationQuery(
			   page,
			   size
		));

		if (result.isError()) {
			return result;
		}

		var response = responseMapper.from(result.getValue());
		return Result.success(response);
	}

	@GetMapping("/{id}")
	public Result<?> getById(@PathVariable UUID id) {
		var result = queryBus.send(new FindQueueByIdQuery(
			   id
		));

		if (result.isError()) {
			return result;
		}

		var response = responseMapper.from(result.getValue());

		return Result.success(response);
	}
}
